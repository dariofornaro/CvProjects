package ForYouShipment.Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class UserProfileModel {
    protected Map<String, String> Parameters;

    public UserProfileModel() {
        Parameters = new HashMap<>();
    }

    /**
     * This returns all of the available parameters of the user's profile.
     * Ex: FirstName, LastName, email, etc. 
     */
    public abstract List<String> getAllParameters();

    /**
     * This function gets a value of a certain parameter of the user.
     * Ex: getParameter("FirstName")
     */
    public String getParameter(String Param) {
        return Parameters.get(Param);
    }

    /**
     * This function sets a value of a certain parameter of the user.
     * Ex: setParameter("FirstName", "Alex")
     */
    public void setParameter(String Param, String Value) {
        Parameters.put(Param, Value);
    }
    
}
