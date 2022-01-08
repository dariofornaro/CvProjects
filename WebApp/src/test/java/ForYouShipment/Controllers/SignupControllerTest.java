package ForYouShipment.Controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import ForYouShipment.Models.ClientUserModel;
import ForYouShipment.Models.LogisticsUserModel;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Storage.UserStorage;
import ForYouShipment.WebApp.WebAppApplication;

// https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
// https://spring.io/guides/gs/testing-web/

@SpringBootTest(classes = WebAppApplication.class)
@AutoConfigureMockMvc
@SuppressWarnings("unchecked")
public class SignupControllerTest {

    @Autowired
	private MockMvc mockMvc;
    
    @BeforeEach
    public void SetUpUsers() {
        UserModel a = new LogisticsUserModel();
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
	public void TestIndexNotLoggedIn() throws Exception {

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Signup/Client")
            )
            .andExpect(status().is(302))
            .andReturn();
    }

    @Test
	public void TestIndexLogisticsNotLoggedIn() throws Exception {

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Signup/Logistics")
            )
            .andExpect(status().is(302))
            .andReturn();
    }


    
	@Test
	public void TestIndexLoggedIn() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Signup/Client")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();
        
        assertTrue(
            view_name.equals("Signup/Index")
        );

        assertTrue(
            model
            .get("SignUpUser") != null
        );

        assertTrue(
            model
            .get("SignedUser") != null
        );
    }

    @Test
	public void TestIndexLoggedInLogistics() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Signup/Logistics")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();
        
        assertTrue(
            view_name.equals("Signup/Index")
        );

        assertTrue(
            model
            .get("SignUpUser") != null
        );

        assertTrue(
            model
            .get("SignedUser") != null
        );
    }


    @Test
	public void TestUnsuccessfullCreateUser() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.1");

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Signup/CreateUser")
                .param("Username", "abcd")
                .param("Password", "1234")
                .param("PasswordRetype", "1234")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();
        

        assertTrue(
            view_name.equals("redirect:/Login")
        );
    }

    @Test
	public void TestUnsuccessfullCreateUser4() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Signup/CreateUser")
                .param("Username", "")
                .param("Password", "1234")
                .param("PasswordRetype", "12345")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();
        

        assertTrue(
            view_name.equals("Signup/Index")
        );
    }


    @Test
	public void TestUnsuccessfullCreateUser2() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Signup/CreateUser")
                .param("Username", "abcd")
                .param("Password", "1234")
                .param("PasswordRetype", "12345")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();
        
        assertTrue(
            model.get("warning") != null 
            && model.get("SignedUser") != null
        );

        assertTrue(
            view_name.equals("Signup/Index")
        );
    }
    
    @Test
	public void TestSuccessfullCreateUser() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Signup/CreateUser")
                .param("Username", "abcd")
                .param("Password", "1234")
                .param("PasswordRetype", "1234")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
        
        assertTrue(
            UserStorage
            .GetInstance()
            .getUsers()
            .size() == 2
        );

    }
    
    @Test
	public void TestUnsuccessfullCreateManager() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.1");

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Signup/CreateManager")
                .param("Username", "abcd")
                .param("Password", "1234")
                .param("PasswordRetype", "1234")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();
        

        assertTrue(
            view_name.equals("redirect:/Login")
        );
    }

    @Test
	public void TestUnsuccessfullCreateManager4() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Signup/CreateManager")
                .param("Username", "")
                .param("Password", "1234")
                .param("PasswordRetype", "12345")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();
        

        assertTrue(
            view_name.equals("Signup/Index")
        );
    }


    @Test
	public void TestUnsuccessfullCreateManager2() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Signup/CreateManager")
                .param("Username", "abcd")
                .param("Password", "1234")
                .param("PasswordRetype", "12345")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();
        
        assertTrue(
            model.get("warning") != null 
            && model.get("SignedUser") != null
        );

        assertTrue(
            view_name.equals("Signup/Index")
        );
    }
    
    @Test
	public void TestSuccessfullCreateManager() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Signup/CreateManager")
                .param("Username", "abcd")
                .param("Password", "1234")
                .param("PasswordRetype", "1234")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
        
        assertTrue(
            UserStorage
            .GetInstance()
            .getUsers()
            .size() == 2
        );

    }
    
}
