package ForYouShipment.Models;

import java.util.Arrays;
import java.util.List;

public class LogisticsProfileModel extends UserProfileModel {
    private static List<String> AvailableParameters = Arrays.asList("First Name", "Last Name", "Email", "Role");
    
    @Override
    public List<String> getAllParameters() {
        return AvailableParameters;
    }

}