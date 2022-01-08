package ForYouShipment.Workers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ForYouShipment.Models.ClientUserModel;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Storage.UserStorage;

public class AuthenticateUserWorkerTest {
    @BeforeEach
    public void SetUpUsers() {
        UserModel a = new ClientUserModel();
        a.setID("1.2.3.4");
        a.setUsername("1234");
        a.setPassword("1234");
        UserStorage.GetInstance().getUsers().clear();
        UserStorage.GetInstance().getUsers().add(a);
    }
    @Test
    public void TestAuthenticate() {
        assertTrue(
            AuthenticateUserWorker
            .Authenticate("1234", "1234")
            .equals("1.2.3.4")
        );
        
        assertTrue(
            AuthenticateUserWorker
            .Authenticate("1234", "12345") == null
        );

        assertTrue(
            AuthenticateUserWorker
            .Authenticate("1235", "1234") == null
        );
    }


    

    @Test
    public void TestGetUserByID() {
        assertTrue(
            AuthenticateUserWorker
            .GetUserByID("1.2.3.4")
            .getUsername()
            .equals("1234")
        );
        
        assertTrue(
            AuthenticateUserWorker
            .GetUserByID("10.2.38.5") == null
        );
    }

    @AfterEach
    public void ClearGarbage() {
        UserStorage.GetInstance().getUsers().clear();
    }
}
