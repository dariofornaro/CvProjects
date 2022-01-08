module Security

let rec createTuple tuple variablesList variablesListCopy = 
    match variablesList with
    |(variable,sec)::xs when sec=fst(tuple) -> createTupleHelper tuple variable xs variablesListCopy variablesListCopy
    |(variable,sec)::xs -> createTuple tuple xs variablesListCopy
    |[] -> []
and createTupleHelper tuple x xs variablesList variablesListCopy=
    match variablesList with 
    |(variable,sec)::tail when sec=snd(tuple) -> (x,variable)::createTupleHelper tuple x xs tail variablesListCopy
    |(variable,sec):: tail -> createTupleHelper tuple x xs tail variablesListCopy
    |[] -> createTuple tuple xs variablesListCopy




let rec allowed latticeList variablesList =
    match latticeList with
    |(a,b)::xs -> (createTuple (a,b) variablesList variablesList) @ allowed xs variablesList
    |[] -> [] 

let rec updateLattice latticeList latticeListCopy =
    match latticeList with 
    |(sec,sec1)::xs -> addDependencies (sec,sec1) xs latticeListCopy latticeListCopy
    |[] -> []
and addDependencies lattice_tuple xs latticeList latticeListCopy=
    match latticeList with 
    |(sec,sec1)::tail when sec=snd(lattice_tuple) -> (fst(lattice_tuple),sec1)::addDependencies lattice_tuple xs tail latticeListCopy
    |(sec,sec1)::tail -> addDependencies lattice_tuple xs tail latticeListCopy
    |[] -> updateLattice xs latticeListCopy

let rec while_noChange latticeList = 
    let update = Set.union (Set.ofList (updateLattice latticeList latticeList)) (Set.ofList (latticeList))
    if not(Set.isSubset (update) (Set.ofList latticeList))
    then while_noChange (Set.toList update)
    else latticeList


let rec eval_exprS expr = 
    match expr with 
    | Num(a)  -> [] 
    | Name(a)    -> [a]
    | NAMEArray(s,i)   -> [s] @ eval_exprS i 
    | TimesExpr(a,b)   -> (eval_exprS a) @ (eval_exprS b)
    | DivExpr(a,b)     -> (eval_exprS a) @ (eval_exprS b)
    | PlusExpr(a,b)     -> (eval_exprS a) @ (eval_exprS b)
    | MinusExpr(a,b)   -> (eval_exprS a) @ (eval_exprS b)
    | PowExpr(a,b)     -> (eval_exprS a) @ (eval_exprS b)
    | UPlusExpr(a)  -> eval_exprS a
    | UMinusExpr(a)  -> eval_exprS a

let rec securityTuple var list = 
    match list with
    |x::xs -> (x,var) :: securityTuple var xs 
    |[] -> []

let rec eval_boolS c =
    match c with
        |Boolean(a) -> []
        |And(a,b)   ->  (eval_boolS a) @ (eval_boolS b)
        |Andand(a,b)   -> (eval_boolS a) @ (eval_boolS b)
        |Or(a,b)   -> (eval_boolS a) @ (eval_boolS b)
        |Oror(a,b)   -> (eval_boolS a) @ (eval_boolS b)
        |Equal(a,b)  -> (eval_exprS a) @ (eval_exprS b)
        |NOTEqual(a,b)  -> (eval_exprS a) @ (eval_exprS b)
        |Not(a)         -> eval_boolS a
        |GREATER(a,b)  -> (eval_exprS a) @ (eval_exprS b)
        |LESS(a,b)  -> (eval_exprS a) @ (eval_exprS b)
        |GREATEREqual(a,b)  ->(eval_exprS a) @ (eval_exprS b)
        |LESSEqual(a,b)  -> (eval_exprS a) @ (eval_exprS b)

let rec edgesCDS c X =
    match c with
        |INIT(Name(a),b) -> Set.ofList (securityTuple a  ((eval_exprS b) @ X))  
        |INIT(NAMEArray(s,i),b) -> Set.ofList (securityTuple s  ((eval_exprS b) @ X @ (eval_exprS i)))   
        |TWOcommand(a,b) -> Set.union (edgesCDS a X) (edgesCDS b X)
        |IFFI(a)         -> fst(edgesGDS a X (Boolean(false)))
        |DOOD(a)         -> fst(edgesGDS a X (Boolean(false)))
        |Skip            -> Set.empty

and edgesGDS g X gBoolean  =
    match g with        
        |Follows(a,b)   -> (edgesCDS b (X  @ (eval_boolS a) @ (eval_boolS gBoolean)) , (Or(a,gBoolean)))
        |TWOguardedC(a,b) -> let (w1,d1) = edgesGDS a X gBoolean 
                             let (w2,d2) = edgesGDS b X d1 
                             ((Set.union w1 w2), d2)


let rec computeViolatedFlows actual allowed =
    match actual with 
    |x :: xs when (Set.contains x allowed) -> computeViolatedFlows xs allowed
    |x :: xs ->  x::computeViolatedFlows xs allowed
    |[] -> []
