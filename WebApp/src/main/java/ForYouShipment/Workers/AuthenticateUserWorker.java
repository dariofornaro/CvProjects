package ForYouShipment.Workers;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Storage.UserStorage;

/** This is  */
public class AuthenticateUserWorker {

    
    private AuthenticateUserWorker() {}

    /**  */
    public static String Authenticate(String Username, String Password) {
        /**
         * If the username and password matches client, then return the ID,
         * otherwise, return null.
         */
        for (UserModel i : UserStorage.GetInstance().getUsers())
            if (i.getUsername().equals(Username) && i.getPassword().equals(Password)) 
                return i.getID();
        return null;
    }

    public static UserModel GetUserByID(String ID) {
        for (UserModel i : UserStorage.GetInstance().getUsers())
            if (i.getID().equals(ID))
                return i;
        return null;
    }
}
