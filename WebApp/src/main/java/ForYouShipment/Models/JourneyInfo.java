package ForYouShipment.Models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ForYouShipment.Storage.JourneyStorage;
import ForYouShipment.Workers.IDGenerator;

public class JourneyInfo extends Journey{
    private  Map<String, String> Parameters;
    private  List<String> AvailableParameters = Arrays.asList("Username", "ID");

    public JourneyInfo() {
        this.setStatus("Waiting for aproval");
        this.setId(IDGenerator.GenerateID());
        JourneyStorage.GetInstance().getJourneys().add(this);
        Parameters = new HashMap<>();
    }

    public JourneyInfo(String s) {
        Parameters = new HashMap<>();
    }

    /**
     * This returns all of the available parameters of the user's profile.
     * Ex: FirstName, LastName, email, etc. 
     */
    public List<String> getAllParameters() {
        return AvailableParameters;
    }

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