package ForYouShipment.Workers;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ForYouShipment.Models.ClientUserModel;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Storage.UserStorage;

public class ValidationWorkerTest {
    @Test
    public void UsernameIsValid() {
        assertTrue(
            ValidationWorker
            .UsernameIsValid(null)
            .equals("No username!")
        );
        assertTrue(
            ValidationWorker
            .UsernameIsValid("abc")
            .equals("Username is too short!")
        );
        assertTrue(
            ValidationWorker
            .UsernameIsValid("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
            .equals("Username is too long!")
        );
        assertTrue(
            ValidationWorker
            .UsernameIsValid("@@@@@")
            .equals("Please only use letters and numbers!")
        );
        
        UserModel a = new ClientUserModel();
        a.setUsername("john");

        UserStorage.GetInstance().getUsers().add(a);

        assertTrue(
            ValidationWorker
            .UsernameIsValid("john")
            .equals("Username is already taken!")
        );

        UserStorage.GetInstance().getUsers().remove(0);
        

        assertTrue(
            ValidationWorker
            .UsernameIsValid("Username1234") == null
        );
    }


    @Test
    public void PasswordIsValid() {
        assertTrue(
            ValidationWorker
            .PasswordIsValid(null, null)
            .equals("No password!")
        );
        assertTrue(
            ValidationWorker
            .PasswordIsValid("abc", "abc")
            .equals("Password is too short!")
        );
        assertTrue(
            ValidationWorker
            .PasswordIsValid("abcde", "abcdef")
            .equals("The passwords do not match!")
        );
        assertTrue(
            ValidationWorker
            .PasswordIsValid("abcdef", "abcdef") == null
        );
    }

}
