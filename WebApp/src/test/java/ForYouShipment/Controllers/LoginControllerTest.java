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
import ForYouShipment.Models.UserModel;
import ForYouShipment.Storage.UserStorage;
import ForYouShipment.WebApp.WebAppApplication;

// https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
// https://spring.io/guides/gs/testing-web/

@SpringBootTest(classes = WebAppApplication.class)
@AutoConfigureMockMvc
@SuppressWarnings("unchecked")
public class LoginControllerTest {

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

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Login/Index")
            )
            .andExpect(status().isOk())
            .andReturn();
        
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();


        assertTrue(view_name.equals("Login/Index"));
    }

    
	@Test
	public void TestSuccessfulLogin() throws Exception {

        MockHttpSession session = new MockHttpSession();

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Login/Login")
                .param("Username", "1234")
                .param("Password", "1234")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();
        
        assertTrue(
            session
            .getAttribute("SignedUser")
            .equals("1.2.3.4")
        );
    }

    @Test
	public void TestUnsuccessfulLogin() throws Exception {

        MockHttpSession session = new MockHttpSession();

		MvcResult resultActions = 
            this.mockMvc.perform(
                post("/Login/Login")
                .param("Username", "1234")
                .param("Password", "11111111")
                .session(session)
            )
            .andExpect(status().isOk())
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();
        
        assertTrue(
            session
            .getAttribute("SignedUser") == null
        );

        assertTrue(
            view_name.equals("Login/Index")
        );
    }

    @Test
	public void TestLogout() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SignedUser", "1.2.3.4");

		MvcResult resultActions = 
            this.mockMvc.perform(
                get("/Login/Logout")
                .session(session)
            )
            .andExpect(status().is(302))
            .andReturn();
        
        String view_name = resultActions.getModelAndView().getViewName();
        Map<String, Object> model = resultActions.getModelAndView().getModel();
        
        assertTrue(
            session
            .getAttribute("SignedUser") == null
        );
    }
}
