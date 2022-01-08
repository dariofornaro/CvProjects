package ForYouShipment.ClientSearch;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ForYouShipment.Models.ClientUserModel;
import ForYouShipment.Models.LogisticsUserModel;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Search.Criteria;
public class CriteriaIDTest {

    @Test
    public void TestMeetCriteria() {
        List<UserModel> users = new ArrayList<>();
        UserModel u1 = new ClientUserModel();
        UserModel u2 = new ClientUserModel();
        UserModel u3 = new LogisticsUserModel();
        u1.setID("Test");
        u2.setID("mmm");
        u3.setID("Test");
        users.add(u1);
        users.add(u2);
        users.add(u3);
        Criteria<UserModel> c = new CriteriaID();
        users = c.meetCriteria(users, "Test");
        assertTrue(users.size() == 1);
    }
    
}
