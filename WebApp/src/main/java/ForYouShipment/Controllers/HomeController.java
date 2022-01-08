package ForYouShipment.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ForYouShipment.Constants.Port;
import ForYouShipment.Storage.ContainerStorage;


@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

    @RequestMapping({"/", ""})
    public String Index(HttpServletRequest req, Model m, HttpSession session) {
        m.addAttribute("SignedUser", GetUser(session));
        return "Home/Index";
    }
    @RequestMapping({"/UserManual"})
    public String UserManual(HttpServletRequest req, Model m, HttpSession session) {
        m.addAttribute("SignedUser", GetUser(session));
        return "Home/UserManual";
    }
    @RequestMapping({"/AboutUs"})
    public String AboutUs(HttpServletRequest req, Model m, HttpSession session) {
        m.addAttribute("SignedUser", GetUser(session));
        return "Home/AboutUs";
    }
    
    @RequestMapping({"/WorldMap"})
    public String WorldMap(HttpServletRequest req, Model m, HttpSession session) {
        m.addAttribute("Containers", ContainerStorage.getUsedContainers());
        m.addAttribute("ports", Port.class.getEnumConstants());               
        m.addAttribute("SignedUser", GetUser(session));
        return "Home/WorldMap";
    }

}
