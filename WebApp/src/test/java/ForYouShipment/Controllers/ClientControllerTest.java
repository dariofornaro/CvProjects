package ForYouShipment.Controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;
import java.util.Set;

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
import ForYouShipment.Models.ClientUserModel;
import ForYouShipment.Models.LogisticsProfileModel;
import ForYouShipment.Models.LogisticsUserModel;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Models.UserProfileModel;
import ForYouShipment.Storage.ContainerStorage;
import ForYouShipment.Storage.JourneyStorage;
import ForYouShipment.Storage.UserStorage;
import ForYouShipment.WebApp.WebAppApplication;

// https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
// https://spring.io/guides/gs/testing-web/

@SpringBootTest(classes = WebAppApplication.class)
@AutoConfigureMockMvc
@SuppressWarnings("unchecked")
public class ClientControllerTest {

    @Autowired
	private MockMvc mockMvc;
    
    @BeforeEach
    public void SetUpUsers() {
        UserModel a = new LogisticsUserModel();
        a.setID("1.2.3.4");
        a.setUsername("test");
        a.setPassword("test");
        UserProfileModel pa = new LogisticsProfileModel();
        for (String s : pa.getAllParameters()) {
            pa.setParameter(s, "test");
        }
        a.setProfile(pa);
        UserStorage.GetInstance().getUsers().add(a);
        
        a = new ClientUserModel();
        a.setID("1.1.1.1");
        a.setUsername("test");
        a.setPassword("test");
        a.setProfile(pa);
        UserStorage.GetInstance().getUsers().add(a);
    }

    @AfterEach
    public void ClearGarbage() {
        UserStorage.GetInstance().getUsers().clear();
        ContainerStorage.GetInstance().getContainers().clear();
        JourneyStorage.GetInstance().getJourneys().clear();
    }

	@Test
	public void TestUnSuccessfulClientsPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.0");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Client/Index")
            )
            .andExpect(status().is(302))
            .andReturn();

        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(view_name.equals("redirect:/Login/"));
    }

    @Test
	public void TestSuccessfulClientPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.1.1.1");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Client/Index")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();

        UserModel SignedUser = (UserModel)model.get("SignedUser");

        assertTrue(
            SignedUser
            .getUsername()
            .equals("test")
        );  
        assertTrue(view_name.equals("Client/Index"));
    }

    @Test
	public void TestSuccessfulClientPagewContainer() throws Exception {
        ContainerStorage.addContainers(1, Port.PORTO);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.1.1.1");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Client/Index")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();

        UserModel SignedUser = (UserModel)model.get("SignedUser");

        assertTrue(
            SignedUser
            .getUsername()
            .equals("test")
        );  
        assertTrue(view_name.equals("Client/Index"));
    }

    @Test
	public void TestSuccessfulClientPage2() throws Exception {
        ContainerStorage.addContainers(1, Port.TRANSIT);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.1.1.1");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Client/Index")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();

        UserModel SignedUser = (UserModel)model.get("SignedUser");

        assertTrue(
            SignedUser
            .getUsername()
            .equals("test")
        );  
        assertTrue(view_name.equals("Client/Index"));
    }

    @Test
	public void TestUnSuccessfulViewPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Client/View")
                .param("ID", "1.2.3.5")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
    
        String view_name = resultActions.getModelAndView().getViewName();       
        assertTrue(view_name.equals("Client/View")); 
    }

    @Test
    public void TestUnSuccessfulViewPage2() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.1.1.1");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Client/View")
                .param("ID", "1.1.1.2")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
    
        String view_name = resultActions.getModelAndView().getViewName();       
        assertTrue(view_name.equals("redirect:/Login/")); 
    }




    @Test
	public void TestSuccessfulViewPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.1.1.1");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Client/View")
                .param("ID", "1.1.1.1")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();

        UserModel SignedUser = (UserModel)model.get("SignedUser");
        UserModel ProfileUser = (UserModel)model.get("ProfileUser");

        assertTrue(
            SignedUser
            .getUsername()
            .equals("test")
        );  

        assertTrue(
            ProfileUser
            .getID()
            .equals("1.1.1.1")
        );  

        assertTrue(view_name.equals("Client/View"));
    }



    @Test
	public void TestSuccessfulSearchPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Client/Search")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();

        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(view_name.equals("Client/Search"));
    }

    @Test
	public void TestUnSuccessfulSearchPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.1.1.1");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Client/Search")
                .param("ID", "1.1.1.2")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();

            String view_name = resultActions.getModelAndView().getViewName();
            assertTrue(view_name.equals("redirect:/Login/"));
    
    }

    @Test
	public void TestSuccessfulDeletePage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Client/Delete")
                .session(session)
                .param("ID", "test")
            )
            .andExpect(status().is(302))
            .andReturn();

        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(view_name.equals("redirect:/Logistics"));
    }

    @Test
	public void TestUnSuccessfulDeletePage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.1.1.1");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Client/Delete")
                .session(session)
                .param("ID", "test")
            )
            .andExpect(status().is(302))
            .andReturn();

            String view_name = resultActions.getModelAndView().getViewName();
            assertTrue(view_name.equals("redirect:/Login/"));
    
    }

    @Test
	public void TestSuccessfulEditPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Client/Edit")
                .session(session)
                .param("ID", "test")
            )
            .andExpect(status().is(302))
            .andReturn();

        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(view_name.equals("redirect:/Login/"));
    }

    @Test
	public void TestUnSuccessfulEditPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.1.1.1");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Client/Edit")
                .session(session)
                .param("ID", "test")
                .param("Password","test")
            )
            .andExpect(status().isOk())
            .andReturn();

    
    }
    
    @Test
	public void TestUnSuccessfulEditPagePost() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Client/Edit")
                .param("Password","test")
                .param("PasswordRetype","test")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
 
    }

    @Test
    public void TestSuccessfulEditPagePost() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.1.1.1");
		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Client/Edit")
                .param("Password","test")
                .param("PasswordRetype","test")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();
        UserModel SignedUser = (UserModel)model.get("SignedUser");
        UserModel ProfileUser = (UserModel)model.get("ProfileUser");


        assertTrue(view_name.equals("redirect:/Client/View?ID=1.1.1.1"));
    }
    
    @Test
    public void TestSuccessfulEditPagePost2() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.1.1.1");
		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Client/Edit")
                .param("Password","test1")
                .param("PasswordRetype","test")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();
        UserModel SignedUser = (UserModel)model.get("SignedUser");
        UserModel ProfileUser = (UserModel)model.get("ProfileUser");


        assertTrue(view_name.equals("Client/Edit"));
    }
    
    @Test
    public void TestSuccessfulEditPagePost3() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.1.1.1");
		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Client/Edit")
                .param("Password","")
                .param("PasswordRetype","")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();
        UserModel SignedUser = (UserModel)model.get("SignedUser");
        UserModel ProfileUser = (UserModel)model.get("ProfileUser");


        assertTrue(view_name.equals("redirect:/Client/View?ID=1.1.1.1"));
    }
    
}