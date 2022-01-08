package ForYouShipment.Controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

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
import ForYouShipment.Models.ContainerMeasurements;
import ForYouShipment.Models.JourneyInfo;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Storage.ContainerStorage;
import ForYouShipment.Storage.UserStorage;
import ForYouShipment.WebApp.WebAppApplication;

// https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
// https://spring.io/guides/gs/testing-web/

@SpringBootTest(classes = WebAppApplication.class)
@AutoConfigureMockMvc
@SuppressWarnings("unchecked")
public class HomeControllerTest {

    @Autowired
	private MockMvc mockMvc;
    
    @BeforeEach
    public void SetUpUsers() {
        UserModel a = new ClientUserModel();
        a.setID("1.2.3.4");
        a.setUsername("1234");
        a.setPassword("1234");

        UserStorage.GetInstance().getUsers().add(a);
    }

    @AfterEach
    public void ClearGarbage() {
        UserStorage.GetInstance().getUsers().clear();
    }

	@Test
	public void TestIndex() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();

        UserModel SignedUser = (UserModel)model.get("SignedUser");

        assertTrue(
            SignedUser
            .getID()
            .equals("1.2.3.4")
        );  
        assertTrue(view_name.equals("Home/Index"));
    }

    @Test
    public void testUserManual() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/UserManual")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        
        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(view_name.equals("Home/UserManual"));
    }

    @Test
    public void testAboutUs() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/AboutUs")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        
        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(view_name.equals("Home/AboutUs"));
    }

    @Test
    public void testWorldMap() throws Exception {
        MockHttpSession session = new MockHttpSession();
        ContainerMeasurements c = new ContainerMeasurements();
        c.setJourney(new JourneyInfo());
        ContainerStorage.GetInstance().getContainers().add(c);
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/WorldMap")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        
        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(view_name.equals("Home/WorldMap"));
    }
}
