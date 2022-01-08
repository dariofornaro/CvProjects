package ForYouShipment.Models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContainerMeasurements extends Container{
	private  Map<String, String> Parameters;

	public Map<String, String> getParameters() {
        return Parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        Parameters = parameters;
    }
    private  List<String> AvailableParameters = Arrays.asList("Latitude","Longitude","Temperature", "Humidity","Pressure","Time", "JourneyID");

    public ContainerMeasurements() {
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
    /**
	 * This is used to change the available measurements
	 * Ex: setAvailableParameters(Arrays.asList("Temperature","Pressure"))
	 */
	public void setAvailableParameters(List<String> availableParameters){
		AvailableParameters = availableParameters; 
	}
	
}
