package ForYouShipment.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ForYouShipment.Models.ClientProfileModel;
import ForYouShipment.Models.ClientUserModel;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Models.UserProfileModel;

import org.json.JSONArray;

public class UserStorageTest {
    @BeforeEach
    public void SetUpUsers() {
        UserModel a = new ClientUserModel();
        a.setID("1.2.3.4");
        a.setUsername("1234");
        a.setPassword("1234");
        UserProfileModel p = new ClientProfileModel();
        p.setParameter("First Name","aaaa");
        p.setParameter("Last Name", "aaaa");
        p.setParameter("Email", "aaa@aaa.aaa");
        p.setParameter("Company Name", "Aaaa");
        a.setProfile(p);
        UserStorage.GetInstance().getUsers().add(a);
    }
    @Test
    public void TestgetUsers() {
        assertTrue(
            UserStorage
            .GetInstance()
            .getUsers()
            .iterator()
            .next()
            .getID()
            .equals("1.2.3.4")
        );
    }

    @Test
    public void TestsetUsers() {
        UserStorage.GetInstance().setUsers(new HashSet<>());
        assertTrue(
            UserStorage
            .GetInstance()
            .getUsers()
            .size() == 0
        );
    }

    @Test
    public void TestSaveContentToJSON() {
        Storage storage =  UserStorage.GetInstance();
        JSONArray array = storage.SaveContentToJSON();
        assertEquals("[{\"IsLogisticsUser\":\"false\",\"Company Name\":\"Aaaa\",\"Email\":\"aaa@aaa.aaa\",\"Username\":\"1234\",\"First Name\":\"aaaa\",\"ID\":\"1.2.3.4\",\"Last Name\":\"aaaa\",\"Password\":\"1234\"}]"
        , array.toString());
    }


    @Test
    public void TestReadContentFromJSON() {
        Storage storage =  UserStorage.GetInstance();
        JSONArray array = storage.SaveContentToJSON();
        UserStorage.GetInstance().getUsers().clear();
        assertEquals(0, UserStorage.GetInstance().getUsers().size());
        storage.ReadContentFromJSON(array);
        UserStorage.GetInstance().getUsers().size();
        assertEquals(1, UserStorage.GetInstance().getUsers().size());
    }

    @Test
    public void StorageNameTest() {
        assertTrue(("UserStorage").equals(UserStorage.GetInstance().StorageName()));
    }


    @Test
    public void CountClientsTest() {
        assertEquals(1, UserStorage.GetInstance().countClients());
    }

    

    @AfterEach
    public void ClearGarbage() {
        UserStorage.GetInstance().getUsers().clear();
    }


}
