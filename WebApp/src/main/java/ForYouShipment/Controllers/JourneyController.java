package ForYouShipment.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import ForYouShipment.JourneySearch.CriteriaCID;
import ForYouShipment.JourneySearch.CriteriaCJID;
import ForYouShipment.JourneySearch.CriteriaCargo;
import ForYouShipment.JourneySearch.CriteriaContent_Type;
import ForYouShipment.JourneySearch.CriteriaDestination;
import ForYouShipment.JourneySearch.CriteriaJID;
import ForYouShipment.JourneySearch.CriteriaOrigin;
import ForYouShipment.JourneySearch.CriteriaUser;
import ForYouShipment.Models.ContainerMeasurements;
import ForYouShipment.Models.JourneyInfo;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Search.Criteria;
import ForYouShipment.Search.OrCriteria;
import ForYouShipment.Storage.ContainerStorage;
import ForYouShipment.Storage.JourneyStorage;
import ForYouShipment.Workers.ContainerRegister;




@Controller
@RequestMapping("/Journey")
public class JourneyController extends BaseController {
    
    @RequestMapping(value={ "/Index", "/" , ""})
    public String Index(HttpServletRequest req, Model m, HttpSession session) {
        if (!HasAccess(AccessActionNounEnum.JOURNEY_PAGE, AccessActionVerbEnum.INDEX, session, req))
            return "redirect:/Login/";
        
        List<JourneyInfo> journey_list = new ArrayList<>(); 
        
        UserModel user = GetUser(session);
        Criteria<JourneyInfo> user_journeys = new CriteriaUser();
        journey_list = user_journeys.meetCriteria(new ArrayList<JourneyInfo>(JourneyStorage.GetInstance().getJourneys()),
                                                    user.getUsername());


        m.addAttribute("Ownjourneys", journey_list);
        m.addAttribute("SignedUser", GetUser(session));
        return "Journey/Index";
    }


    @RequestMapping(value={ "/New", "/New/" })
    public String New(HttpServletRequest req, Model m, HttpSession session) {
        if (!HasAccess(AccessActionNounEnum.JOURNEY_PAGE, AccessActionVerbEnum.CREATE, session, req))
            return "redirect:/Login/";
        

        m.addAttribute("Ports", Port.class.getEnumConstants());
        m.addAttribute("SignedUser", GetUser(session));
        return "Journey/New";
    }


    @RequestMapping(value = {"/New", "/New/"}, method = RequestMethod.POST)
    public String registerContainer(HttpServletRequest req, Model m, HttpSession session,
                        @RequestParam("Origin") String origin, 
                        @RequestParam("Destination") String destination,
                        @RequestParam("Content type") String content_type,
                        @RequestParam("Cargo") String cargo) throws Exception {
        
        try {
            Port.ofString(origin);
            Port.ofString(destination);
            
        } catch (Exception e) {
            m.addAttribute("warning", "Invalid origin or destination");
            m.addAttribute("SignedUser", GetUser(session));    
            return "Journey/New";
        }
        UserModel user = GetUser(session);

        ContainerMeasurements container = ContainerRegister.setJourney(origin, destination, content_type, cargo, user);

        if (container == null) {
            m.addAttribute("warning", "There aren't any free containers at the source port. Please try again later!");
            m.addAttribute("SignedUser", GetUser(session));    
            return "Journey/New";
        }

        m.addAttribute("Ports", Port.class.getEnumConstants());
        m.addAttribute("SignedUser", GetUser(session));                    
        return "redirect:/Journey/Index";
    }
    


    @RequestMapping(value={ "/Search" })
    public String Search(HttpServletRequest req, Model m, HttpSession session) {
        String Query = req.getParameter("Query");
        if (Query == null)
            Query = "";

        List<JourneyInfo> answer = new ArrayList<>();
        
        Criteria<JourneyInfo> origin = new CriteriaOrigin();
        Criteria<JourneyInfo> destination = new CriteriaDestination();
        Criteria<JourneyInfo> content = new CriteriaContent_Type();
        Criteria<JourneyInfo> cargo = new CriteriaCargo();
        Criteria<JourneyInfo> originOrDestination = new OrCriteria<JourneyInfo>(origin, destination);
        Criteria<JourneyInfo> contentOrCargo = new OrCriteria<JourneyInfo>(content, cargo);
        Criteria<JourneyInfo> allCriteria = new OrCriteria<JourneyInfo>(originOrDestination, contentOrCargo);
        if (GetUser(session).IsLogisticUser()) {
            Criteria<JourneyInfo> user = new CriteriaUser();
            allCriteria = new OrCriteria<JourneyInfo>(allCriteria, user);
            answer = allCriteria.meetCriteria(new ArrayList<JourneyInfo>(JourneyStorage.GetInstance().getJourneys()),
                                                    Query);
        }
        else { 
            List<JourneyInfo> journey_list = new ArrayList<>(); 
        
            UserModel user = GetUser(session);
            Criteria<JourneyInfo> user_journeys = new CriteriaUser();
            journey_list = user_journeys.meetCriteria(new ArrayList<JourneyInfo>(JourneyStorage.GetInstance().getJourneys()),
                                                        user.getUsername());

            answer = allCriteria.meetCriteria(journey_list, Query);

        }
        /* We are matching our query with all the fields set up by the user for a Journey*/
        

        m.addAttribute("Query", Query);
        m.addAttribute("answer", answer);
        m.addAttribute("SignedUser", GetUser(session));
        return "Journey/Search";
    }

    @RequestMapping(value={ "/View" })
    public String View(HttpServletRequest req, Model m, HttpSession session,
                    @RequestParam("ID") String JourneyId) {
                        
        Criteria<JourneyInfo> criteria = new CriteriaJID();
        Criteria<ContainerMeasurements> container = new CriteriaCJID();
        JourneyInfo j = criteria.meetCriteria(new ArrayList<JourneyInfo>(JourneyStorage.GetInstance().getJourneys()), JourneyId).get(0);
        ContainerMeasurements c = container.meetCriteria(new ArrayList<ContainerMeasurements>(ContainerStorage.GetInstance().getContainers()), JourneyId).get(0);
        m.addAttribute("ContainerID", c.getId());
        m.addAttribute("Journey", j); 
        m.addAttribute("ID", JourneyId);
        m.addAttribute("Latitude", Double.parseDouble(c.getParameter("Latitude")));
        m.addAttribute("Longitude", Double.parseDouble(c.getParameter("Longitude")));
        m.addAttribute("SignedUser", GetUser(session)); 
        m.addAttribute("ports", Port.class.getEnumConstants());    
        m.addAttribute("Container", c);           
        return "Journey/View";
    }


    @RequestMapping(value = {"/Measurements", "/Measurements/"})
    public String SetMeasurement(HttpServletRequest req, Model m, HttpSession session,
                                 @RequestParam("ID") String ID) {

        if (!HasAccess(AccessActionNounEnum.CONTAINER_PAGE, AccessActionVerbEnum.CREATE, session, req))
        return "redirect:/Login/";
        
        
        Criteria<ContainerMeasurements> container = new CriteriaCID();
        ContainerMeasurements c = container.meetCriteria(new ArrayList<ContainerMeasurements>(ContainerStorage.GetInstance().getContainers()), ID).get(0);
        m.addAttribute("SignedUser", GetUser(session));
        m.addAttribute("Container", c);
        m.addAttribute("ContainerID", ID);
        return "Journey/Measurements" ;
    }

    
    @RequestMapping(value = {"/Measurements", "/Measurements/"}, method = RequestMethod.POST)
    public String SetMeasurement2(HttpServletRequest req, Model m, HttpSession session) throws Exception {
    
        String ID = req.getParameter("ContainerID");                   
        Criteria<ContainerMeasurements> container = new CriteriaCID();
        ContainerMeasurements c = container.meetCriteria(new ArrayList<ContainerMeasurements>(ContainerStorage.GetInstance().getContainers()), ID).get(0);
        
        
        c.setLocation(Port.ofString("In Transit"));
        

        for (String Param : c.getAllParameters()) {
            String value = req.getParameter(Param);
            c.setParameter(Param, value);
        }

        Map<String, String> measurements = c.getParameters();
        c.saveMeasurements(measurements);

        String reached_destination = req.getParameter("ReachedDestination");
        if (reached_destination.equals("Yes"))
            ContainerRegister.returnContainer(c);

        return "redirect:/Journey/Search/";
    }
}
