package ForYouShipment.Persistance;
import org.json.JSONException;
import org.json.JSONObject;

import ForYouShipment.Models.ClientProfileModel;
import ForYouShipment.Models.ClientUserModel;
import ForYouShipment.Models.LogisticsProfileModel;
import ForYouShipment.Models.LogisticsUserModel;
import ForYouShipment.Models.UserModel;

public class UserModelFactory {
    public static JSONObject UserModelToJSON(UserModel user) {
        JSONObject obj = new JSONObject();
        obj.put("Username", user.getUsername());
        obj.put("Password", user.getPassword());
        obj.put("ID", user.getID());
        
        if (user.IsLogisticUser())
            obj.put("IsLogisticsUser", "true");
        else
            obj.put("IsLogisticsUser", "false");
        
        for (String item : user.getProfile().getAllParameters())
            obj.put(item, user.getProfile().getParameter(item));

        return obj;
    }

    public static UserModel UserModelFromJSON(JSONObject obj) throws JSONException {
        UserModel user = null;

        if ("false".equals(obj.getString("IsLogisticsUser"))) {
            user = new ClientUserModel();
            user.setProfile(new ClientProfileModel());
        }
        else if ("true".equals(obj.getString("IsLogisticsUser"))) {
            user = new LogisticsUserModel();
            user.setProfile(new LogisticsProfileModel());
        } 
        else
            throw new JSONException("Unable to get user profile");
        
        user.setID(obj.getString("ID"));
        user.setUsername(obj.getString("Username"));
        user.setPassword(obj.getString("Password"));
        
        for (String item : user.getProfile().getAllParameters())
            user.getProfile().setParameter(item, obj.getString(item));
           
        return user;
    }
}
