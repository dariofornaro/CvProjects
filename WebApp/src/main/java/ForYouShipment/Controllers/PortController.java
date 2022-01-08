package ForYouShipment.Controllers;

import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ForYouShipment.Constants.AccessActionNounEnum;
import ForYouShipment.Constants.AccessActionVerbEnum;
import ForYouShipment.Constants.Port;
import ForYouShipment.Models.ContainerMeasurements;
import ForYouShipment.Storage.ContainerStorage;
import ForYouShipment.Workers.LoggingWorker;

@Controller
@RequestMapping("/Port")
public class PortController extends BaseController {
    
    @RequestMapping("View")
    public String View(HttpServletRequest req, Model m, HttpSession session,
                @RequestParam("Port") String port) throws Exception{
        if (!HasAccess(AccessActionNounEnum.PORT_MANAGMENT, AccessActionVerbEnum.GENERAL, session, req))
            return "redirect:/Login/";
        
        try {
            Port p = Port.ofString(port);
            Set <ContainerMeasurements> s =
                ContainerStorage.GetInstance()
                .getContainers()
                .stream()
                .filter(c -> c.getLocation().name().equals(p.name()))
                .collect(Collectors.toSet());
            m.addAttribute("Containers", s);

            m.addAttribute("Port", p);
            m.addAttribute("SignedUser", GetUser(session));
            return "Port/View";
        }
        catch (Exception e) {
            LoggingWorker.GetInstance().Log("Unable to get port: " + e.getMessage());
            return "redirect:/error/display";
        }
    }

    @RequestMapping(value={ "/NewContainer" }, method = RequestMethod.POST) 
    public String AddContainerToPort(HttpServletRequest req, Model m, HttpSession session,
                @RequestParam("Port") String port,
                @RequestParam("Count") int Number) throws Exception{
        if (!HasAccess(AccessActionNounEnum.PORT_MANAGMENT, AccessActionVerbEnum.GENERAL, session, req))
            return "redirect:/Login/";
        
        try {
            Port p = Port.ofString(port);
            if (Number > 0 && Number <= 1000)
                ContainerStorage.addContainers(Number, p);
                
            return "redirect:/Port/View?Port=" + p.toString();
        }
        catch (Exception e) {
            LoggingWorker.GetInstance().Log(e.getMessage());
            return "redirect:/error/view";
        }
    }
}
