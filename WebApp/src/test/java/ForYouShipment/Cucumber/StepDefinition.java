package ForYouShipment.Cucumber;
 
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import ForYouShipment.ClientSearch.CriteriaUsername;
import ForYouShipment.Controllers.ClientController;
import ForYouShipment.Controllers.LoginController;
import ForYouShipment.Controllers.SignupController;
import ForYouShipment.Models.ClientProfileModel;
import ForYouShipment.Models.ClientUserModel;
import ForYouShipment.Models.LogisticsProfileModel;
import ForYouShipment.Models.LogisticsUserModel;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Models.UserProfileModel;
import ForYouShipment.Search.Criteria;
import ForYouShipment.Storage.UserStorage;
import ForYouShipment.WebApp.WebAppApplication;
import ForYouShipment.Workers.AuthenticateUserWorker;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
 
@SpringBootTest(classes = WebAppApplication.class)
@AutoConfigureMockMvc
public class StepDefinition {
    // @Autowired
    private MockMvc signupMockMvc;
    private MockMvc loginMockMvc;
    private MockMvc clientMockMvc;
 
    private String first_name;
    private String password, password_retype;
    private String username;
    MockHttpSession session;
 
    private String search_id;
    private List<UserModel> search_result;
 
    // Tries to sign-in
    private String authenticate_username;
    private String authenticate_password;
 
    private UserModel the_user, the_other_user;
 
    @BeforeEach
    @Before
    public void SetUpUsers() {
        signupMockMvc = MockMvcBuilders.standaloneSetup(new SignupController()).build();
        loginMockMvc = MockMvcBuilders.standaloneSetup(new LoginController()).build();
        clientMockMvc = MockMvcBuilders.standaloneSetup(new ClientController()).build();
        first_name = "First Name";
        password = password_retype = "password";
        username = "username";
        session = new MockHttpSession();
        search_id = "";
        search_result = null;
 
        authenticate_username = "";
        authenticate_password = "";
 
        UserModel admin = new LogisticsUserModel();
        admin.setID("admin_ID");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setProfile(new LogisticsProfileModel());
        for (String par : admin.getProfile().getAllParameters())
            admin.getProfile().setParameter(par, "val");
        admin.getProfile().setParameter("First Name", "Admin First Name");
        UserStorage.GetInstance().getUsers().add(admin);
 
        UserModel client = new ClientUserModel();
        client.setID("existing_client_ID");
        client.setUsername("existing_client");
        client.setPassword("client");
        client.setProfile(new ClientProfileModel());
        for (String par : client.getProfile().getAllParameters())
            client.getProfile().setParameter(par, "val");
        client.getProfile()
            .setParameter("First Name", "Existing Client First Name");
        UserStorage.GetInstance().getUsers().add(client);
    }
 
    @After
    public void clearGarbage(){
        UserStorage.GetInstance().getUsers().clear();
    }
 
 
    @Given("a new name {string}")
    public void GivenANewName(String name) {
        first_name = name;
    }
 
    @Given("a new client named {string}")
    public void GivenANewClientNamedX(String name) {
        first_name = name;
    }
 
    @Given("a valid client ID {string}")
    public void GivenAValidID(String ID) {
        search_id = ID;
    }
 
    @Given("I am a logistic user")
    public void GivenIAmALogisticUser() {
        session.setAttribute("SignedUser", "admin_ID");
    }
 
    @Given("I am a client user")
    public void GivenIAmAClientUser() {
        session.setAttribute("SignedUser", "existing_client_ID");
    }
 
    @Given("a new client")
    public void GivenANewClient() {
        // do nothing.
    }
 
    @Given("a client user")
    public void GivenAClientUser() {
        the_user = AuthenticateUserWorker.GetUserByID("existing_client_ID");
    }
 
    @Given("a logistic user")
    public void GivenALogisticUser() {
        the_user = AuthenticateUserWorker.GetUserByID("admin_ID");
    }
 
    @Given("a client account")
    public void GivenAClientAccount() {
        the_other_user = AuthenticateUserWorker.GetUserByID("existing_client_ID");
    }
 
 
 
    @When("I delete the client")
    public void IDeleteTheClient() throws Exception {
        MvcResult resultActions = 
            this.clientMockMvc.perform(
                get("/Client/Delete")
                .param("ID", the_other_user.getID())
                .session(session)
            ).andReturn();
    }
 
    @When("the user uses the correct credencials")
    public void TheUSerCorrectCreditentials() {
        if (the_user.IsLogisticUser()) {
            authenticate_password = "admin";
            authenticate_username = "admin";
        }
        else {
            authenticate_username = "existing_client";
            authenticate_password = "client";
        }
    }
 
    @When("the user uses the incorrect credencials")
    public void TheUSerIncorrectCreditentials() {
        if (the_user.IsLogisticUser()) {
            authenticate_password = "admin";
            authenticate_username = "admin1";
        }
        else {
            authenticate_username = "existing_client";
            authenticate_password = "client1";
        }
    }
 
    @When("I create his profile")
    public void i_create_his_profile() throws Exception {
        MvcResult resultActions = 
            this.signupMockMvc.perform(
                post("/Signup/CreateUser")
                .param("Username", username)
                .param("Password", password)
                .param("PasswordRetype", password_retype)
                .param("First Name", first_name)
                .session(session)
            ).andReturn();
    }
 
    @When("the user tries to login")
    public void UserTriesToLogIn() throws Exception {
        MvcResult resultActions = 
            this.loginMockMvc.perform(
                post("/Login/Login")
                .param("Username", authenticate_username)
                .param("Password", authenticate_password)
                .session(session)
            )
            .andReturn();
    }
 
    @When("I search the given ID")
    public void ISearchTheGivenID() throws Exception {
        MvcResult resultActions = 
            this.clientMockMvc.perform(
                get("/Client/Search")
                .param("Query", search_id)
                .session(session)
            )
            .andReturn();
 
        Map<String, Object> model = resultActions.getModelAndView().getModel();
 
        search_result = (List<UserModel>)model.get("answer");
    }
 
    @When("I dont match the password")
    public void IDonTMathThePassword() {
        password_retype = "Invalid passsword";
    }
 
    @When("I use a already taken username")
    public void IUseAAlreadyTakenUsername() {
        username = "existing_client";
    }
 
    @When("I input a username too long")
    public void IUseUsernameTooLong() {
        username = "A very very very long username .......";
    }
 
    @When("I input a password too short")
    public void IInputAPasswordTooShort() {
        password = password_retype = "a";
    }
 
    @When("I change my first name")
    public void IChangeMyLastName() throws Exception {
        MvcResult resultActions = 
            this.clientMockMvc.perform(
                post("/Client/Edit")
                .param("Password", "")
                .param("PasswordRetype", "")
                .param("First Name", first_name)
                .session(session)
            )
            // .andExpect(status().is(302))
            .andReturn();
    }
 
 
 
 
 
 
 
    @Then("the storage has the client named {string}")
    public void storage_has_the_client_named(String name) {
        boolean b = false;
        for (UserModel u : UserStorage.GetInstance().getUsers())
            if (name.equals(u.getProfile().getParameter("First Name")))
                b = true;
        assertTrue(b);
    }
 
    @Then("the storage does not have the client named {string}")
    public void StorageDoesNotHaveClientNamedX(String name) {
        boolean b = false;
        for (UserModel u : UserStorage.GetInstance().getUsers())
            if (name.equals(u.getProfile().getParameter("First Name")))
                b = true;
        assertFalse(b);
    }
 
    @Then("the client gets a unique ID")
    public void TheClientGetsAUniqueID() {
        UserModel user = null;
        for (UserModel u : UserStorage.GetInstance().getUsers())
            if (u.getUsername() == username)
                user = u;
 
        assertTrue(user != null);
 
        for (UserModel u : UserStorage.GetInstance().getUsers())
            if (u.getUsername() != user.getUsername())
                assertTrue(u.getID() != user.getID());   
    }
 
    @Then("the user is logged in his account")
    public void TheUserIsLoggin() {
        assertTrue(session.getAttribute("SignedUser") != null);
    }
 
    @Then("the user is not logged in his account")
    public void TheUserIsNotLoggin() {
        assertTrue(session.getAttribute("SignedUser") == null);
    }
 
    @Then("the client is no longer in the storage")
    public void TheClientGotDeleted() {
        assertTrue(
            AuthenticateUserWorker.GetUserByID(the_other_user.getID()) == null
        );
    }
 
    @Then("my first name is {string}")
    public void TheFirstNameIsX(String name) {
        UserModel u =
            AuthenticateUserWorker
                .GetUserByID((String)session.getAttribute("SignedUser"));
        assertTrue(u != null);
        assertTrue(u.getProfile() != null);
        assertTrue(name.equals(u.getProfile().getParameter("First Name")));
    }
 
    @Then("I find the client")
    public void IFindTheClient() {
        assertTrue(search_result.size() == 1);
        assertTrue(search_result.get(0).getID().equals(search_id));
    }
 
 
}
 
 
 
 
 