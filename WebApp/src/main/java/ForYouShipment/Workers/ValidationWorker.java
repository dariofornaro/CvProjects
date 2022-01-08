package ForYouShipment.Workers;

import ForYouShipment.Models.*;
import ForYouShipment.Storage.UserStorage;

public class ValidationWorker {

    private ValidationWorker() {}

    public static String UsernameIsValid(String Username) {
        /**
         * This function verifies if the username is valid is not already taken.
         * Returns null if the username is ok, otherwise returns why it is not ok.
         */
        if (Username == null) 
            return "No username!";
        if (Username.length() < 4)
            return "Username is too short!";
        if (Username.length() > 21)
            return "Username is too long!";
        if (!Username.matches("[A-Za-z0-9]+"))
            return "Please only use letters and numbers!";
        for (UserModel i : UserStorage.GetInstance().getUsers()) 
            if (Username.equals(i.getUsername()))
                return "Username is already taken!";
        return null;
    }

    public static String PasswordIsValid(String Password, String PasswordRetype) {
        /**
         * This function verifies if the password and the retyped passowrd are valid.
         * Returns null if the password is ok, otherwise returns why it is not ok.
         */
        if (Password == null) 
            return "No password!";
        if (Password.length() < 4)
            return "Password is too short!";
        if (!Password.equals(PasswordRetype))
            return "The passwords do not match!";
        return null;
    }
}
