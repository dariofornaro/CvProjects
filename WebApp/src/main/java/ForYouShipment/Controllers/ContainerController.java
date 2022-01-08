package ForYouShipment.Controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ForYouShipment.Constants.AccessActionNounEnum;
import ForYouShipment.Constants.AccessActionVerbEnum;
import ForYouShipment.Models.ContainerMeasurements;
import ForYouShipment.Workers.ContainerRegister;
import ForYouShipment.Workers.LoggingWorker;


@Controller
@RequestMapping("/Container")
public class ContainerController extends BaseController {
    
    @RequestMapping(value={ "/Measurements", "/Measurements/" })
    public String New(HttpServletRequest req, Model m, HttpSession session) {

        if (!HasAccess(AccessActionNounEnum.CONTAINER_PAGE, AccessActionVerbEnum.CREATE, session, req))
            return "redirect:/Login/"; 
        
        

        m.addAttribute("SignedUser", GetUser(session));
        m.addAttribute("Measurements", new HashMap<String, String>());

        return "Container/Measurements";
    }

    
    @RequestMapping(value = {"/Delete" }, method = RequestMethod.POST)
    public String DeleteContainer(HttpServletRequest req, Model m, HttpSession session,
                        @RequestParam("ID") String ID){
        if (!HasAccess(AccessActionNounEnum.CONTAINER_MANAGEMENT, AccessActionVerbEnum.GENERAL, session, req))
            return "redirect:/Login/";
        
        ContainerRegister.DeleteContainer(ID);
        return "redirect:/Logistics";
    }
    


    @RequestMapping(value={ "/View" }) 
    public String ViewContainer(HttpServletRequest req, Model m, HttpSession session,
                @RequestParam("ID") String ID) throws Exception{
        if (!HasAccess(AccessActionNounEnum.CONTAINER_MANAGEMENT, AccessActionVerbEnum.GENERAL, session, req))
            return "redirect:/Login/";
        
        try {
            ContainerMeasurements c = ContainerRegister.GetContainerByID(ID);
            if (c == null)
                throw new Exception("c is null");

            m.addAttribute("Container", c);
            m.addAttribute("SignedUser", GetUser(session));
            return "Container/View";
        }
        catch (Exception e) {
            LoggingWorker.GetInstance().Log(e.getMessage());
            return "redirect:/error/view";
        }
    }
}
