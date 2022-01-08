package ForYouShipment.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController extends BaseController implements ErrorController  {

    @RequestMapping("/error/display")
    public String DisplayError(HttpServletRequest req, Model m, HttpSession session) {
        m.addAttribute("SignedUser", GetUser(session));
        return "Shared/Error";
    }
    
    @RequestMapping("/error")
    public String getErrorPath() {
		return "redirect:/error/display";
	}
}
