package ForYouShipment.ClientSearch;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ForYouShipment.Models.ClientProfileModel;
import ForYouShipment.Models.ClientUserModel;
import ForYouShipment.Models.LogisticsProfileModel;
import ForYouShipment.Models.LogisticsUserModel;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Models.UserProfileModel;
import ForYouShipment.Search.Criteria;

public class CriteriaLast_NameTest {

    @Test
    public void TestMeetCriteria() {
        List<UserModel> users = new ArrayList<>();
        UserProfileModel up1 = new ClientProfileModel();
        UserProfileModel up2 = new ClientProfileModel();
        UserProfileModel up3 = new LogisticsProfileModel();
        UserModel u1 = new ClientUserModel();
        UserModel u2 = new ClientUserModel();
        UserModel u3 = new LogisticsUserModel();
        up1.setParameter("Last Name", "Test");
        u1.setProfile(up1);
        up2.setParameter("Last Name", "mmm");
        u2.setProfile(up2);
        up3.setParameter("Last Name", "Test");
        u3.setProfile(up3);
        users.add(u1);
        users.add(u2);
        users.add(u3);
        Criteria<UserModel> c = new CriteriaLast_Name();
        users = c.meetCriteria(users, "Test");
        assertTrue(users.size() == 1);
    }
    
}
