package ForYouShipment.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ForYouShipment.Constants.AccessActionNounEnum;
import ForYouShipment.Constants.AccessActionVerbEnum;
import ForYouShipment.Models.ClientProfileModel;
import ForYouShipment.Models.ClientUserModel;
import ForYouShipment.Models.LogisticsProfileModel;
import ForYouShipment.Models.LogisticsUserModel;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Storage.UserStorage;
import ForYouShipment.Workers.IDGenerator;
import ForYouShipment.Workers.ValidationWorker;

@Controller
@RequestMapping("/Signup")
public class SignupController extends BaseController {

    @RequestMapping(value={ "/Client", "/", "" })
    public String  ReturnSignupFormClient(HttpServletRequest req, Model m, HttpSession session) {
        if (!HasAccess(AccessActionNounEnum.SIGNUP_PAGE, AccessActionVerbEnum.INDEX, session, req)) 
            return "redirect:/Login";
        
        UserModel user = new ClientUserModel();
        user.setProfile(new ClientProfileModel());

        m.addAttribute("SignUpUser", user);

        m.addAttribute("SignedUser", GetUser(session));
        m.addAttribute("SubmitLink", "CreateUser");
        return "Signup/Index";
    }

    @RequestMapping(value={ "/Logistics", "/", "" })
    public String  ReturnSignupFormLogistics(HttpServletRequest req, Model m, HttpSession session) {
        if (!HasAccess(AccessActionNounEnum.SIGNUP_PAGE, AccessActionVerbEnum.INDEX, session, req)) 
            return "redirect:/Login";
        
        UserModel user = new LogisticsUserModel();
        user.setProfile(new LogisticsProfileModel());

        m.addAttribute("SignUpUser", user);

        m.addAttribute("SignedUser", GetUser(session));
        m.addAttribute("SubmitLink", "CreateManager");
        return "Signup/Index";
    }

    @RequestMapping(value ={"/CreateUser"}, method = RequestMethod.POST)
    public String CreateUser(HttpServletRequest req, Model m, HttpSession session, 
                            @RequestParam("Username") String Username,
                            @RequestParam("Password") String Password,
                            @RequestParam("PasswordRetype") String PasswordRetype) {
        
        if (!HasAccess(AccessActionNounEnum.SIGNUP_PAGE, AccessActionVerbEnum.CREATE, session, req)) 
            return "redirect:/Login";

        UserModel user = new ClientUserModel();
        user.setProfile(new ClientProfileModel());

        user.setUsername(Username);
        user.setPassword(Password);
        user.setProfile(new ClientProfileModel());

        for (String Param : user.getProfile().getAllParameters()) {
            String value = req.getParameter(Param);
            user.getProfile().setParameter(Param, value);
        }

        m.addAttribute("SignUpUser", user);

        String UsernameCheckResult = ValidationWorker.UsernameIsValid(Username);
        if (UsernameCheckResult != null) {
            m.addAttribute("warning", UsernameCheckResult);
            m.addAttribute("SignedUser", GetUser(session));
            m.addAttribute("SubmitLink", "CreateUser");
            return "Signup/Index";
        }
        String PasswordCheckResult = ValidationWorker.PasswordIsValid(Password, PasswordRetype);
        if (PasswordCheckResult != null) {
            m.addAttribute("warning", PasswordCheckResult);
            m.addAttribute("SignedUser", GetUser(session));
            m.addAttribute("SubmitLink", "CreateUser");
            return "Signup/Index";
        }

        String ID = IDGenerator.GenerateID();

        user.setID(ID);
        
        UserStorage.GetInstance().getUsers().add(user);

        return "redirect:/Logistics";

    }

    @RequestMapping(value ={"/CreateManager"}, method = RequestMethod.POST)
    public String CreateManager(HttpServletRequest req, Model m, HttpSession session, 
                            @RequestParam("Username") String Username,
                            @RequestParam("Password") String Password,
                            @RequestParam("PasswordRetype") String PasswordRetype) {
        
        if (!HasAccess(AccessActionNounEnum.SIGNUP_PAGE, AccessActionVerbEnum.CREATE, session, req)) 
            return "redirect:/Login";

        UserModel user = new LogisticsUserModel();
        user.setProfile(new LogisticsProfileModel());

        user.setUsername(Username);
        user.setPassword(Password);

        for (String Param : user.getProfile().getAllParameters()) {
            String value = req.getParameter(Param);
            user.getProfile().setParameter(Param, value);
        }

        m.addAttribute("SignUpUser", user);

        String UsernameCheckResult = ValidationWorker.UsernameIsValid(Username);
        if (UsernameCheckResult != null) {
            m.addAttribute("warning", UsernameCheckResult);
            m.addAttribute("SignedUser", GetUser(session));
            m.addAttribute("SubmitLink", "CreateManager");
            return "Signup/Index";
        }
        String PasswordCheckResult = ValidationWorker.PasswordIsValid(Password, PasswordRetype);
        if (PasswordCheckResult != null) {
            m.addAttribute("warning", PasswordCheckResult);
            m.addAttribute("SignedUser", GetUser(session));
            m.addAttribute("SubmitLink", "CreateManager");
            return "Signup/Index";
        }

        String ID = IDGenerator.GenerateID();

        user.setID(ID);
        
        UserStorage.GetInstance().getUsers().add(user);

        return "redirect:/Logistics";
    }
}