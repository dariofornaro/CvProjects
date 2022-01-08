package ForYouShipment.Storage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import ForYouShipment.Models.UserModel;
import ForYouShipment.Persistance.UserModelFactory;

/**
 * Singleton class storing all informations.
 */
public class UserStorage implements Storage {

    // Items to save.
    private Set <UserModel> Users;

    private UserStorage() {
        Users = Collections.synchronizedSet(new HashSet<>());
    }

    public Set<UserModel> getUsers() {
        return Users;
    }

    public void setUsers(Set<UserModel> users) {
        Users = users;
    }

    public JSONArray SaveContentToJSON() {
        JSONArray array = new JSONArray();

        for (UserModel u : Users)
            array.put(UserModelFactory.UserModelToJSON(u));
        return array;
    }

    public void ReadContentFromJSON(JSONArray array) {
        Users = Collections.synchronizedSet(new HashSet<>());

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            UserModel u = UserModelFactory.UserModelFromJSON(obj);
            Users.add(u);
        }
    }

    public String StorageName() { 
        return "UserStorage"; 
    }

    /**
     * This method return the number of client users
     * @return number of clients as an int
     */
    public int countClients(){
        int nr_of_clients = 0;
        for (UserModel user: instance.Users){
            if (!user.IsLogisticUser())
                nr_of_clients++;
        }
        return nr_of_clients;
    }

    private static UserStorage instance = null;

    public static UserStorage GetInstance() {
        if (instance == null)
            instance = new UserStorage();
        return instance;
    }
}
