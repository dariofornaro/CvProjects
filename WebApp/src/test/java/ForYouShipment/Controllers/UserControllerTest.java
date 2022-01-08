package ForYouShipment.Controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import ForYouShipment.Models.UserModel;
import ForYouShipment.WebApp.WebAppApplication;

// https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
// https://spring.io/guides/gs/testing-web/

@SpringBootTest(classes = WebAppApplication.class)
@AutoConfigureMockMvc
@SuppressWarnings("unchecked")
public class UserControllerTest {

    // @Autowired
	// private MockMvc mockMvc;
    
	// @Test
	// public void IndexShouldReturnTheUsers() throws Exception {
    //     Storage.GetInstance().setUsers(new ArrayList<>());
    //     Storage.GetInstance().getUsers().add(new UserModel());
    //     Storage.GetInstance().getUsers().get(0).setFirstName("Hello");

	// 	MvcResult resultActions = 
    //         this.mockMvc.perform(
    //             get("/Users/Index")
    //             // .sessionAttr("username", "idk")
    //         )
    //         .andExpect(status().isOk())
    //         .andReturn();
        
        
    //     String view_name = resultActions.getModelAndView().getViewName();
    //     Map<String, Object> model = resultActions.getModelAndView().getModel();


    //     assertTrue(view_name.equals("Users/Index"));

    //     assertTrue(model.containsKey("users"));
    //     List<UserModel> users = (List<UserModel>)model.get("users");

    //     assertTrue(users.get(users.size() - 1).getFirstName().equals("Hello"));
    // }

    
	// @Test
	// public void ViewShouldReturnTheUser() throws Exception {
    //     Storage.GetInstance().setUsers(new ArrayList<>());
    //     Storage.GetInstance().getUsers().add(new UserModel());
    //     Storage.GetInstance().getUsers().get(0).setFirstName("Hello");
    //     Storage.GetInstance().getUsers().get(0).setID("1234");

	// 	MvcResult resultActions = 
    //         this.mockMvc.perform(
    //             get("/Users/View?ID=1234")
    //         )
    //         .andExpect(status().isOk())
    //         .andReturn();
        
    //     String view_name = resultActions.getModelAndView().getViewName();
    //     Map<String, Object> model = resultActions.getModelAndView().getModel();

        
    //     assertTrue(view_name.equals("Users/View"));

    //     assertTrue(model.containsKey("user"));
    //     UserModel user = (UserModel)model.get("user");

    //     assertTrue(user.getFirstName().equals("Hello"));
    // }

	// @Test
	// public void DeleteShouldDeleteTheUser() throws Exception {
    //     Storage.GetInstance().setUsers(new ArrayList<>());
    //     Storage.GetInstance().getUsers().add(new UserModel());
    //     Storage.GetInstance().getUsers().get(0).setFirstName("Hello");
    //     Storage.GetInstance().getUsers().get(0).setID("1234");

	// 	MvcResult resultActions = 
    //         this.mockMvc.perform(
    //             post("/Users/Delete")
    //                 .param("ID", "1234")
    //         )
    //         .andExpect(status().is(302))
    //         .andReturn();
        
    //     assertTrue(Storage.GetInstance().getUsers().size() == 0);
    // }

    // @Test
	// public void CreateNewUser() throws Exception {
    //     Storage.GetInstance().setUsers(new ArrayList<>());

	// 	MvcResult resultActions = 
    //         this.mockMvc.perform(
    //             post("/Users/New")
    //             .param("FirstName", "Alex")
    //             .param("LastName", "Doe")
    //         )
    //         .andExpect(status().is(302))
    //         .andReturn();
        
    //     assertTrue(Storage.GetInstance().getUsers().size() == 1);
    //     assertTrue(Storage.GetInstance()
    //                 .getUsers()
    //                 .get(0)
    //                 .getFirstName()
    //                 .equals("Alex"));
    // }

    // @Test
	// public void CanOpenEditForm() throws Exception {
    //     Storage.GetInstance().setUsers(new ArrayList<>());
    //     Storage.GetInstance().getUsers().add(new UserModel());
    //     Storage.GetInstance().getUsers().get(0).setFirstName("Hello");
    //     Storage.GetInstance().getUsers().get(0).setID("1234");

	// 	MvcResult resultActions = 
    //         this.mockMvc.perform(
    //             get("/Users/Edit")
    //             .param("ID", "1234")
    //         )
    //         .andExpect(status().isOk())
    //         .andReturn();
        
    //     String view_name = resultActions.getModelAndView().getViewName();
    //     Map<String, Object> model = resultActions.getModelAndView().getModel();

        
    //     assertTrue(view_name.equals("Users/Edit"));

    //     assertTrue(model.containsKey("user"));
    //     UserModel user = (UserModel)model.get("user");

    //     assertTrue(user.getID().equals("1234"));
    // }

    // @Test
	// public void CanEditUser() throws Exception {
    //     Storage.GetInstance().setUsers(new ArrayList<>());
    //     Storage.GetInstance().getUsers().add(new UserModel());
    //     Storage.GetInstance().getUsers().get(0).setFirstName("Hello");
    //     Storage.GetInstance().getUsers().get(0).setID("1234");

	// 	MvcResult resultActions = 
    //         this.mockMvc.perform(
    //             post("/Users/Edit")
    //             .param("ID", "1234")
    //             .param("FirstName", "Alex")
    //             .param("LastName", "Doe")
    //         )
    //         .andExpect(status().is(302))
    //         .andReturn();
        
    //     assertTrue(Storage
    //             .GetInstance()
    //             .getUsers()
    //             .get(0)
    //             .getFirstName()
    //             .equals("Alex"));
        
    // }
}
