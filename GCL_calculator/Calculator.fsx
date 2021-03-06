// This script implements our interactive calculator




// We need to import a couple of modules, including the generated lexer and parser
#r "C:/Users/Dario/.nuget/packages/fslexyacc/10.0.0/build/fslex/net46/FsLexYacc.Runtime.dll"

open FSharp.Text.Lexing
open System
#load "CalculatorTypesAST.fs"
open CalculatorTypesAST
#load "CalculatorParser.fs"
open CalculatorParser
#load "CalculatorLexer.fs"
open CalculatorLexer
#load "print.fs"
#load "Compiler.fs"
open Compiler
#load "CompilerD.fs"
#load "Interpreter.fs"
open Interpreter
open CompilerD
#load "sign.fs"
open sign
#load "Security.fs"
open Security



// We define the evaluation function recursively, by induction on the structure
// of arithmetic expressions (AST of type expr)
// let rec eval e =
//   match e with
//     | Num(x) -> x
//     | TimesExpr(x,y) -> eval(x) * eval (y)
//     | DivExpr(x,y) -> eval(x) / eval (y)
//     | PlusExpr(x,y) -> eval(x) + eval (y)
//     | MinusExpr(x,y) -> eval(x) - eval (y)
//     | PowExpr(x,y) -> eval(x) ** eval (y)
//     | UPlusExpr(x) -> eval(x)
//     | UMinusExpr(x) -> - eval(x)
//     | Name(x)  -> x
//     | NAMEArray(a,b) -> a[b]
    
let rec printC c =
    match c with
        |INIT(a,b) ->  "INIT ("+ printE(a)+" , "+printE(b) + ")" 
        |TWOcommand(a,b) ->  "TWOcommands ("+printC(a)+" ; "+printC(b) + ")"
        |IFFI(a)         ->  "IFFI ("+printG(a)+")"
        |DOOD(a)         ->   "DOOD ("+printG(a)+")"
        |Skip            ->   "SKIP "

and printG g =
    match g with        
        |Follows(a,b)   -> "FOLLOWS ("+printB(a)+" -> " + printC(b)+")"
        |TWOguardedC(a,b) -> "TWOguardedC ("+printG(a)+" [] "+printG(b)+")"



and printB b =
    match b with
        |Boolean(a) -> if a then "true" else "false"
        |And(a,b)   -> "AND ("+printB(a)+" and "+printB(b)+")"
        |Andand(a,b)   -> "ANDAND ("+printB(a)+" andand "+printB(b)+")"
        |Or(a,b)   -> "OR ("+printB(a)+" or "+printB(b)+")"
        |Oror(a,b)   -> "OROR ("+printB(a)+" oror "+printB(b)+")"
        |Equal(a,b)  -> "EQUAL ("+printE(a)+" = "+printE(b)+")"
        |NOTEqual(a,b)  -> "NOTEQUAL ("+printE(a)+" , "+printE(b)+")"
        |Not(a)         -> "NOT ("+printB(a)+")"
        |GREATER(a,b)  -> "GREATER ("+printE(a)+" , "+printE(b)+")"
        |LESS(a,b)  -> "LESS ("+printE(a)+" , "+printE(b)+")"
        |GREATEREqual(a,b)  -> "GREATEREQUAL ("+printE(a)+" , "+printE(b)+")"
        |LESSEqual(a,b)  -> "LESSEQUAL ("+printE(a)+" , "+printE(b)+")"

and printE e =
    match e with
         | Num(a)  -> string a
         | Name(a)    -> a
         | NAMEArray(s,i)   -> s+"["+printE(i)+"]"
         | TimesExpr(a,b)   -> "TIMES ("+printE(a)+" , "+printE(b)+")"
         | DivExpr(a,b)     -> "DIV ("+printE(a)+" , "+printE(b)+")"
         | PlusExpr(a,b)     -> "PLUS ("+printE(a)+" , "+printE(b)+")"
         | MinusExpr(a,b)   -> "MINUS ("+printE(a)+" , "+printE(b)+")"
         | PowExpr(a,b)     -> "POWER ("+printE(a)+" , "+printE(b)+")"
         | UPlusExpr(a)  -> "+"+printE(a)
         | UMinusExpr(a)  ->  "-"+printE(a)



// We
let parse input =
    // translate string into a buffer of characters
    let lexbuf = LexBuffer<char>.FromString input
    // translate the buffer into a stream of tokens and parse them
    let res = CalculatorParser.start CalculatorLexer.tokenize lexbuf
    // return the result of parsing (i.e. value of type "expr")
    res

let toSign i =
    if (i>0) then [Plus]
    else if (i=0) then [Zero]
          else [Minus]


let getElement a = 
    match a with
    |x::xs -> x
    |_ -> failwith "Element not found"

let rec toMemSign d map =
    match d with
    |TWOcommand(a,b) -> toMemSign a (toMemSign b map)
    |INIT(NAMEArray(a,c),b) -> if (Map.containsKey a (snd(map)) ) then (fst(map),(Map.add (a) (Set.union (Map.find a (snd(map))) ((Set.ofList (toSign(get_int(eval_expr b Map.empty))) ))) (snd(map))))
                               else (fst(map),(Map.add a (Set.ofList (toSign(get_int(eval_expr b Map.empty))) ) (snd(map))))
    |INIT(Name(a),b) -> ((Map.add a (getElement (toSign(get_int(eval_expr b Map.empty))) ) (fst(map))),(snd(map)))
    |_ -> failwith "invalid sign initialization"



let rec convertEdge e =
    match e with
    |Edge(Node(a),action,Node(b))::xs -> (a,action,b)::convertEdge xs
    |[] -> []
       
let rec getFirst n = 
    match n with
    |[] -> []
    |(a,b)::xs -> a::getFirst xs

let rec toMem d map =
    match d with
    |TWOcommand(a,b) -> toMem a (toMem b map)
    |INIT(NAMEArray(a,c),b) -> let st = a + "["+(string(get_int(eval_expr c map)))+"]"
                               Map.add (st) (get_int(eval_expr b map)) map
    |INIT(Name(a),b) -> Map.add a (get_int(eval_expr b map)) map 
    |_ -> failwith "invalid initialization"

let rec toSecurity d set =
    match d with
    |TWOcommand(a,b) -> toSecurity a (toSecurity b set)
    |INIT(Name(a),Name(b)) -> Set.union (Set.ofList [(a,b);(a,a);(b,b)]) set  
    |_ -> failwith "invalid initialization"
let rec toSecurityMap d map = 
    match d with
    |TWOcommand(a,b) -> toSecurityMap a (toSecurityMap b map)
    |INIT(Name(a),Name(b)) -> Map.add a b map  
    |_ -> failwith "invalid initialization"



// We implement here the function that interacts with the user
let rec compute n =
    printfn "You have %i tries left"(n)
    if n = 0 then

        printfn "Bye bye"
    
    else
       
        for arg in fsi.CommandLineArgs do
            if arg="ND" then
                printfn("Non-Deterministic MODE")
                printf "Enter guarded command code: "
                try
                  let e = parse (Console.ReadLine())
                  printfn "Result: %s" (toDot  (edgesC e (Node("qstart")) (Node("qend")) 1)  "digraph program_graph {rankdir=LR;node [shape = circle]; qstart; node [shape = doublecircle]; qend; node [shape = circle]")                                                            
                  compute n
                with err -> compute (n-1)
            else if arg="D" then
                     printfn("Deterministic MODE")
                     printf "Enter guarded command code: "
                     try
                         let e = parse (Console.ReadLine())
                         printfn "Result: %s" (toDotD  (edgesCD e (Node("qstart")) (Node("qend")) 1 (Boolean(false)))  "digraph program_graph {rankdir=LR;node [shape = circle]; qstart; node [shape = doublecircle]; qend; node [shape = circle]")                                                          
                         compute n
                     with err -> compute (n-1)
                 else if arg="Calculator.fsx" then printfn("\n")
                      else if arg="Interpreter" then
                              printfn("Interpreting")
                              
                              try
                                 printf "Initialize variables: "
                                 let init = parse (Console.ReadLine())
                                 printf "Enter guarded command code: "
                                 let e = parse (Console.ReadLine())
                                 let p = getFirst (edgesCD e (Node("qstart")) (Node("qend")) 1 (Boolean(false)))
                                 let r = step_exe (p) (p) (toMem init Map.empty) "qstart" 0
                                 printfn  "%s" (fst r)
                                
                                 printfn  "%A" (snd r)
                              with err -> compute (n-1)
                           else if arg="InterpreterND" then
                                   printfn("Non-Determinist Interpreting")
                              
                                   try
                                         printf "Initialize variables: "
                                         let init = parse (Console.ReadLine())
                                         printf "Enter guarded command code: "
                                         let e = parse (Console.ReadLine())
                                         let p = edgesC e (Node("qstart")) (Node("qend")) 1 
                                         let r = step_exeND (p) (p) (toMem init Map.empty) "qstart" 0 List.empty
                                         printfn  "%s" (fst r)
                                        
                                         printfn  "%A" (snd r)
                                   with err -> compute (n-1)
                                else if arg="Sign-Analysis" then
                                      printfn("Sign Analysis started")
                                      try
                                         printf "Initialize variables: "
                                         let init = parse (Console.ReadLine())
                                         printf "Enter guarded command code: "
                                         let e = parse (Console.ReadLine())
                                         let p = edgesC e (Node("qstart")) (Node("qend")) 1 
                                         sign_main "qstart" (convertEdge p) (Set.ofList [toMemSign init (Map.empty,Map.empty)] )
                                       with err ->  printfn "%s" err.Message
                                                    compute (n-1)
                                      else if arg="Security-Analysis" then
                                           printfn("Security Analysis started")
                                           try 
                                                printfn "Initialize Security Lattice: "
                                                printfn "Write them in increasing order of confidentiality"
                                                let text = parse (Console.ReadLine())
                                                printf "Initialize Security Classification: "
                                                let init = parse (Console.ReadLine())
                                                let lattice = while_noChange (Set.toList (toSecurity text Set.empty)) 
                                                let variables = toSecurityMap init Map.empty 
                                                let allowed_flows =allowed lattice (Map.toList(variables)) 
                                                printf "Enter guarded command code: "
                                                let e = parse (Console.ReadLine())
                                                let actual_flows = Set.toList(edgesCDS e [])
                                                let violated_flows = computeViolatedFlows actual_flows (Set.ofList allowed_flows )
                                                printf "Actual flows: "
                                                printfn "%A" (actual_flows)
                                                printf "Allowed flows: "
                                                printfn "%A" (allowed_flows)
                                                printf "Violated flows: "
                                                printfn "%A" (violated_flows)
                                                if (violated_flows.Length > 0) then printfn "Not secure" else printfn "Secure"

                                           with err ->  printfn "%s" err.Message
                                                        compute (n-1)
                                            else if arg="MC" then
                                                 printfn("Model Checker started")
                                                 try 
                                                      printf "Initialize variables: "
                                                      let init = parse (Console.ReadLine())
                                                      printf "Enter guarded command code: "
                                                      let e = parse (Console.ReadLine())
                                                      let p = edgesC e (Node("qstart")) (Node("qend")) 1 
                                                      let frontier = [("qstart",(toMem init Map.empty))]
                                                      Model_checker p Set.empty frontier 
                                                  with err -> printfn "%s" err.Message
                                                              compute (n-1)
                                                  else printfn "nono"
                                           
//add map input


// Start interacting with the user
compute 10
