package ForYouShipment.Controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ForYouShipment.Constants.AccessActionNounEnum;
import ForYouShipment.Constants.AccessActionVerbEnum;
import ForYouShipment.Constants.Port;
import ForYouShipment.Storage.ContainerStorage;
import ForYouShipment.Storage.JourneyStorage;
import ForYouShipment.Storage.UserStorage;



@Controller
@RequestMapping("/Logistics")
public class LogisticsController extends BaseController {
    
    @RequestMapping(value={ "/Index", "/", "" })
    public String Index(HttpServletRequest req, Model m, HttpSession session) throws Exception{
        if (!HasAccess(AccessActionNounEnum.LOGISTICS_MANAGEMENT, AccessActionVerbEnum.INDEX, session, req))
            return "redirect:/Login/";

        int numberClients = UserStorage.GetInstance().countClients();
        m.addAttribute("numberClients", numberClients);

        int numberAprovedJourneys = JourneyStorage.GetInstance().countJourneysApproved();
        m.addAttribute("numberAprovedJourneys", numberAprovedJourneys);

        int numberJourneysToApprove = JourneyStorage.GetInstance().countJourneysToApprove();
        m.addAttribute("numberJourneysToApprove", numberJourneysToApprove);

        // <p>${element.toString()}: ${ContainerStorage.GetNrContainers(element)}</p>

        Port[] Ports = Port.class.getEnumConstants();
        m.addAttribute("Ports", Ports);

        HashMap<String, Integer> portMap = new HashMap<>();
        for (Port p : Ports) 
            portMap.put(p.toString(), ContainerStorage.GetNrContainers(p));
        
        m.addAttribute("portMap", portMap);

        m.addAttribute("SignedUser", GetUser(session));
        return "Logistics/Index";
    }

}
