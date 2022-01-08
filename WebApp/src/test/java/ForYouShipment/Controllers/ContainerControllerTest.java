package ForYouShipment.Controllers;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
public class ContainerControllerTest {

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
        
    }



    @Test
    public void DoDelete() throws Exception{
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.1");
		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Container/Delete")
                .param("ID","1234")
                .session(session))
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();       
        assertTrue(view_name.equals("redirect:/Logistics")); 
    }

    @Test
    public void DoDeleteNoUser() throws Exception{
        MockHttpSession session = new MockHttpSession();
		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Container/Delete")
                .param("ID","1234")
                .session(session))
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();       
        assertTrue(view_name.equals("redirect:/Login/")); 
    }

    @Test
    public void ViewNoUser() throws Exception{
        MockHttpSession session = new MockHttpSession();
		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Container/View")
                .param("ID","1234")
                .session(session))
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();       
        assertTrue(view_name.equals("redirect:/Login/")); 
    }

    @Test
    public void ViewInvalid() throws Exception{
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.1");
		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Container/View")
                .param("ID","1234")
                .session(session))
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();       
        assertTrue(view_name.equals("redirect:/error/view")); 
    }

    @Test
    public void ViewValid() throws Exception{
        ContainerMeasurements c = new ContainerMeasurements();
        c.setId("1234");
        ContainerStorage.GetInstance().getContainers().add(c);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.1");
		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Container/View")
                .param("ID","1234")
                .session(session))
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();       
        assertTrue(view_name.equals("Container/View")); 
    }
}