package ForYouShipment.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ForYouShipment.ClientSearch.CriteriaFirst_Name;
import ForYouShipment.ClientSearch.CriteriaID;
import ForYouShipment.ClientSearch.CriteriaLast_Name;
import ForYouShipment.ClientSearch.CriteriaUsername;
import ForYouShipment.Constants.AccessActionNounEnum;
import ForYouShipment.Constants.AccessActionVerbEnum;
import ForYouShipment.Constants.Port;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Search.Criteria;
import ForYouShipment.Search.OrCriteria;
import ForYouShipment.Storage.ContainerStorage;
import ForYouShipment.Storage.UserStorage;
import ForYouShipment.Workers.AuthenticateUserWorker;
import ForYouShipment.Workers.ValidationWorker;


@Controller
@RequestMapping("/Client")
public class ClientController extends BaseController {
    
    @RequestMapping(value={ "/Index", "/", "" })
    public String Index(HttpServletRequest req, Model m, HttpSession session) {

    if (!HasAccess(AccessActionNounEnum.CLIENT_MANAGEMENT, AccessActionVerbEnum.INDEX, session, req))
        return "redirect:/Login/";
    
    UserModel user = GetUser(session);

    m.addAttribute("User", user);

    // Sending the available ports to the views,
    // so that the user can see where he/she can order.
    Port[] Ports = Port.class.getEnumConstants();
        m.addAttribute("Ports", Ports);

    HashMap<String, Integer> portMap = new HashMap<>();
    for (Port p : Ports) 
        if (ContainerStorage.GetNrContainers(p) > 0 && (!p.toString().equals("In Transit")))
        portMap.put(p.toString(), ContainerStorage.GetNrContainers(p));
    
    m.addAttribute("portMap", portMap);

    // Send Username to the view
    m.addAttribute("Username", user.getUsername());
    
    m.addAttribute("SignedUser", GetUser(session));
    return "Client/Index";
}

    // Should always have the ID param /Client/View?ID=1.2.3.4.
    @RequestMapping(value={ "/View" })
    public String View(HttpServletRequest req, Model m, HttpSession session,
                    @RequestParam("ID") String ProfileID) {

        //ID of the signed user
        UserModel signedUser = GetUser(session);
        UserModel profileUser = AuthenticateUserWorker.GetUserByID(ProfileID);

        if (!HasAccess(AccessActionNounEnum.CLIENT_MANAGEMENT, AccessActionVerbEnum.PERSONAL, session, req))
            return "redirect:/Login/";

        if (!signedUser.IsLogisticUser() && signedUser != profileUser )
            return "redirect:/Login/";


        m.addAttribute("ProfileUser", profileUser);

        m.addAttribute("SignedUser", GetUser(session));
        return "Client/View";
    }


    @RequestMapping(value={ "/Search" })
    public String Search(HttpServletRequest req, Model m, HttpSession session) {
    
        if (!HasAccess(AccessActionNounEnum.CLIENT_MANAGEMENT, AccessActionVerbEnum.SEARCH, session, req))
            return "redirect:/Login/";


        String Query = req.getParameter("Query");
        if (Query == null)
            Query = "";

        List<UserModel> answer = new ArrayList<>();

        Criteria<UserModel> username = new CriteriaUsername();
        Criteria<UserModel> firstname = new CriteriaFirst_Name();
        Criteria<UserModel> lastname = new CriteriaLast_Name();
        Criteria<UserModel> id = new CriteriaID();
        Criteria<UserModel> allCriteria = new OrCriteria<>(new OrCriteria<>(username, firstname), new OrCriteria<>(lastname, id));
        answer = allCriteria.meetCriteria(new ArrayList<UserModel>(UserStorage.GetInstance().getUsers()),
        Query);

        m.addAttribute("Query", Query);
        m.addAttribute("answer", answer);
        m.addAttribute("SignedUser", GetUser(session));
        return "Client/Search";
    }




    @RequestMapping(value={ "/Delete" })
    public String Delete(HttpServletRequest req, Model m, HttpSession session,
                        @RequestParam("ID") String ID) {

        if (!HasAccess(AccessActionNounEnum.CLIENT_MANAGEMENT, AccessActionVerbEnum.DELETE, session, req))
            return "redirect:/Login/";

        UserModel user = AuthenticateUserWorker.GetUserByID(ID);

        UserStorage.GetInstance().getUsers().remove(user);
        
        return "redirect:/Logistics";
    }


    @RequestMapping(value={ "/Edit" }) 
    public String Edit(HttpServletRequest req, Model m, HttpSession session) {

        if (!HasAccess(AccessActionNounEnum.CLIENT_MANAGEMENT, AccessActionVerbEnum.EDIT, session, req))
            return "redirect:/Login/";


        m.addAttribute("SignedUser", GetUser(session));
        return "Client/Edit";
    }


    @RequestMapping(value={ "/Edit" }, method = RequestMethod.POST) 
    public String Edit(HttpServletRequest req, Model m, HttpSession session,
                @RequestParam("Password") String Password,
                @RequestParam("PasswordRetype") String PasswordRetype) {

        if (!HasAccess(AccessActionNounEnum.CLIENT_MANAGEMENT, AccessActionVerbEnum.EDIT, session, req))
            return "redirect:/Login/";

    

        UserModel user = GetUser(session);

        for (String Param : user.getProfile().getAllParameters()) {
            String value = req.getParameter(Param);
            user.getProfile().setParameter(Param, value);
        }


        String PasswordCheckResult = ValidationWorker.PasswordIsValid(Password, PasswordRetype);
        if (PasswordCheckResult != null && Password != "") {
            m.addAttribute("warning", PasswordCheckResult);
            m.addAttribute("SignedUser", GetUser(session));
            return "Client/Edit";
        }

        if (Password != "") {
            user.setPassword(Password);
        }
        
        return "redirect:/Client/View?ID=" + user.getID();
    }

}
