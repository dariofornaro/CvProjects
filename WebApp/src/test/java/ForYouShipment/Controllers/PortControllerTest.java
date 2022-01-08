package ForYouShipment.Controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import ForYouShipment.Constants.Port;
import ForYouShipment.Models.ContainerMeasurements;
import ForYouShipment.Models.LogisticsProfileModel;
import ForYouShipment.Models.LogisticsUserModel;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Storage.ContainerStorage;
import ForYouShipment.Storage.UserStorage;
import ForYouShipment.WebApp.WebAppApplication;

@SpringBootTest(classes = WebAppApplication.class)
@AutoConfigureMockMvc
@SuppressWarnings("unchecked")
public class PortControllerTest {

    @Autowired
	private MockMvc mockMvc;
    
    @BeforeEach
    public void SetUpUsers() {
        UserModel admin = new LogisticsUserModel();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setID("0.0.0.0");
		admin.setProfile(new LogisticsProfileModel());
		admin.getProfile().setParameter("First Name", "Administrator");
		admin.getProfile().setParameter("Last Name", "Administrator");
		admin.getProfile().setParameter("Email", "admin@dtu.dk");
		admin.getProfile().setParameter("Role", "Admin");
		UserStorage.GetInstance().getUsers().add(admin);
    }

    @AfterEach
    public void ClearGarbage() {
        UserStorage.GetInstance().getUsers().clear();
    }



    @Test 
    public void ViewValid() throws Exception{
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "0.0.0.0");
        ContainerMeasurements c = new ContainerMeasurements();
        c.setLocation(Port.NEKO);
        ContainerStorage.GetInstance().getContainers().add(c);

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Port/View")
                .param("Port", "Neko")
                .session(session))
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();       
        assertTrue(view_name.equals("Port/View")); 
    }

    
    @Test 
    public void ViewInvalid() throws Exception{
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "0.0.0.0");
        ContainerMeasurements c = new ContainerMeasurements();
        c.setLocation(Port.NEKO);
        ContainerStorage.GetInstance().getContainers().add(c);

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Port/View")
                .param("Port", "asd")
                .session(session))
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();       
        assertTrue(view_name.equals("redirect:/error/display")); 
    }

    @Test
    public void ViewNoAccount() throws Exception{
        MockHttpSession session = new MockHttpSession();

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Port/View")
                .param("Port", "Lisbon")
                .session(session))
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();       
        assertTrue(view_name.equals("redirect:/Login/")); 
    }

    @Test
    public void NewContainterValid() throws Exception{
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "0.0.0.0");
		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Port/NewContainer")
                .param("Port", "Lisbon")
                .param("Count", "2")
                .session(session))
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();       
        assertTrue(view_name.equals("redirect:/Port/View?Port=Lisbon")); 
    }

    @Test
    public void NewContainterNoAccount() throws Exception{
        MockHttpSession session = new MockHttpSession();
		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Port/NewContainer")
                .param("Port", "Lisbon")
                .param("Count", "2")
                .session(session))
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();       
        assertTrue(view_name.equals("redirect:/Login/")); 
    }

    @Test
    public void NewContainterLargeNr() throws Exception{
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "0.0.0.0");
        

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Port/NewContainer")
                .param("Port", "Lisbon")
                .param("Count", "100000000")
                .session(session))
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();  
  
        assertTrue(view_name.equals("redirect:/Port/View?Port=Lisbon")); 
    }
    
    @Test
    public void NewContainterSmallNr() throws Exception{
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "0.0.0.0");
        

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Port/NewContainer")
                .param("Port", "Lisbon")
                .param("Count", "0")
                .session(session))
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();  
  
        assertTrue(view_name.equals("redirect:/Port/View?Port=Lisbon")); 
    }

    @Test
    public void NewContainterException() throws Exception{
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "0.0.0.0");
        

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Port/NewContainer")
                .param("Port", "asdasda")
                .param("Count", "100000000")
                .session(session))
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();  
  
        assertTrue(view_name.equals("redirect:/error/view")); 
    }
}
