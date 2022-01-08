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
import ForYouShipment.Models.ClientUserModel;
import ForYouShipment.Models.ContainerMeasurements;
import ForYouShipment.Models.JourneyInfo;
import ForYouShipment.Models.LogisticsUserModel;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Storage.ContainerStorage;
import ForYouShipment.Storage.JourneyStorage;
import ForYouShipment.Storage.UserStorage;
import ForYouShipment.WebApp.WebAppApplication;

// https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
// https://spring.io/guides/gs/testing-web/

@SpringBootTest(classes = WebAppApplication.class)
@AutoConfigureMockMvc
@SuppressWarnings("unchecked")

public class JourneyControllerTest {

    @Autowired
	private MockMvc mockMvc;
    
    @BeforeEach
    public void SetUpUsers() {
        UserModel client = new ClientUserModel();
        client.setID("1.2.3.4");
        client.setUsername("1234");
        client.setPassword("1234");
        UserModel logisticUser = new LogisticsUserModel();
        logisticUser.setID("1.2.3.1");
        logisticUser.setUsername("1231");
        logisticUser.setPassword("1231");
        UserStorage.GetInstance().getUsers().add(client);
        UserStorage.GetInstance().getUsers().add(logisticUser);
        
     
    }

    @AfterEach
    public void ClearGarbage() {
        UserStorage.GetInstance().getUsers().clear();
        JourneyStorage.GetInstance().getJourneys().clear();
        ContainerStorage.GetInstance().getContainers().clear();
        
    }

	@Test
	public void TestNewAccess() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Journey/New")
                .session(session)
            )
            .andExpect(status().is(200))
            .andReturn();
        
            String view_name = resultActions.getModelAndView().getViewName();
       
            assertTrue(
                view_name.equals("Journey/New")
            );
    }

    @Test
	public void TestNewnoAccess() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.5");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Journey/New")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
        
            String view_name = resultActions.getModelAndView().getViewName();
            System.out.print(view_name);
            assertTrue(
                view_name.equals("redirect:/Login/")
            );
    }

    @Test
	public void TestNewPostInvalidOD() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Journey/New")
                .param("Origin", "test")
                .param("Destination", "test")
                .param("Content type", "test")
                .param("Cargo", "test")
                .session(session)
            )
            .andExpect(status().is(200))
            .andReturn();
        
            String view_name = resultActions.getModelAndView().getViewName();
       
            assertTrue(
                view_name.equals("Journey/New")
            );
    }

    @Test
	public void TestNewPostValidNoContainer() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Journey/New")
                .param("Origin", "Lisbon")
                .param("Destination", "Porto")
                .param("Content type", "test")
                .param("Cargo", "test")
                .session(session)
            )
            .andExpect(status().is(200))
            .andReturn();
        
            String view_name = resultActions.getModelAndView().getViewName();
       
            assertTrue(
                view_name.equals("Journey/New")
            );
    }

    @Test
	public void TestNewPostValid() throws Exception {
        ContainerStorage.addContainers(1, Port.LISBON);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Journey/New")
                .param("Origin", "Lisbon")
                .param("Destination", "Porto")
                .param("Content type", "test")
                .param("Cargo", "test")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
        
            String view_name = resultActions.getModelAndView().getViewName();
       
            assertTrue(
                view_name.equals("redirect:/Journey/Index")
            );
    }

   
    @Test
    public void TestSearchNoAccess() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

        MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Journey/Search")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(
            view_name.equals("Journey/Search")
        );      
    }
 

    @Test
    public void TestSearchClientAccess() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Journey/Search")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(
            view_name.equals("Journey/Search")
        );      
    }

    @Test
    public void TestSearchLogisticsAccess() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.1");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Journey/Search")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(
            view_name.equals("Journey/Search")
        );      
    }
  

    @Test
    public void TestIndexAccess() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Journey/Index")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(
            view_name.equals("Journey/Index")
        );
    }

    

    @Test
    public void TestIndexNoAccess() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.1");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Journey/Index")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(
            view_name.equals("redirect:/Login/")
        );
    }

    @Test
    public void TestView() throws Exception {
        ContainerMeasurements c = new ContainerMeasurements();
        JourneyInfo j = new JourneyInfo();
        j.setId("1234");
        c.setParameter("Latitude", "1.0");
        c.setParameter("Longitude", "1.0");
        c.setJourney(j);
        ContainerStorage.GetInstance().getContainers().add(c);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.1");
		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Journey/View")
                .param("ID","1234")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(
            view_name.equals("Journey/View")
        );
    }

    @Test
    public void TestMeasurementsNoAccess() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");
		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Journey/Measurements")
                .param("ID","1234")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(
            view_name.equals("redirect:/Login/")
        );
    }

    @Test
    public void TestMeasurementsValid() throws Exception {
        ContainerMeasurements c = new ContainerMeasurements();
        JourneyInfo j = new JourneyInfo();
        c.setId("1234");
        j.setId("1234");
        c.setParameter("Latitude", "1.0");
        c.setParameter("Longitude", "1.0");
        c.setJourney(j);
        ContainerStorage.GetInstance().getContainers().add(c);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.1");
		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Journey/Measurements")
                .param("ID","1234")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(
            view_name.equals("Journey/Measurements")
        );
    }

    @Test
    public void TestMeasurements2InTransit() throws Exception {
        ContainerMeasurements c = new ContainerMeasurements();
        JourneyInfo j = new JourneyInfo();
        c.setId("1234");
        j.setId("1234");
        c.setParameter("Latitude", "1.0");
        c.setParameter("Longitude", "1.0");
        c.setJourney(j);
        ContainerStorage.GetInstance().getContainers().add(c);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.1");
		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Journey/Measurements")
                .param("ContainerID","1234")
                .param("ReachedDestination", "No")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(
            view_name.equals("redirect:/Journey/Search/")
        );
    }

    @Test
    public void TestMeasurements2ReachedDestination() throws Exception {
        ContainerMeasurements c = new ContainerMeasurements();
        JourneyInfo j = new JourneyInfo();
        c.setId("1234");
        j.setId("1234");
        j.setDestination(Port.PORTO);
        c.setParameter("Latitude", "1.0");
        c.setParameter("Longitude", "1.0");
        c.setJourney(j);
        ContainerStorage.GetInstance().getContainers().add(c);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.1");
		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Journey/Measurements")
                .param("ContainerID","1234")
                .param("ReachedDestination", "Yes")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        assertTrue(
            view_name.equals("redirect:/Journey/Search/")
        );
    }
}