package ForYouShipment.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ForYouShipment.Constants.Port;
import ForYouShipment.Models.ClientProfileModel;
import ForYouShipment.Models.ClientUserModel;
import ForYouShipment.Models.Container;
import ForYouShipment.Models.JourneyInfo;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Models.UserProfileModel;

import org.json.JSONArray;
    
public class JourneyStorageTest {
    
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

    @AfterEach
    public void clearGarbage() {
        UserStorage.GetInstance().getUsers().clear();
        JourneyStorage.GetInstance().getJourneys().clear();
        ContainerStorage.GetInstance().getContainers().clear();
    }

    

    @Test
    public void CountJourneysToApproveTest() {
        new JourneyInfo();
        assertEquals(1, JourneyStorage.GetInstance().countJourneysToApprove());
    }

    @Test
    public void CountJourneysToApproved() {
        JourneyInfo j = new JourneyInfo();
        j.setStatus("Active");
        assertEquals(1, JourneyStorage.GetInstance().countJourneysApproved());
    }


    @Test
    public void StorageNameTest() {
        assertTrue(("JourneyStorage").equals(JourneyStorage.GetInstance().StorageName()));
    }

    @Test
    public void TestSaveContentToJSON() {
        JourneyInfo j = new JourneyInfo();
        j.setOrigin(Port.NEKO);
        j.setDestination(Port.CAPETOWN);
        j.setCargo("testCargo");
        j.setContent_type("Fragile");
        j.setId("test");
        j.setParameter("Username", "test");
        j.setParameter("ID", "test");
        Storage storage =  JourneyStorage.GetInstance();
        JSONArray array = storage.SaveContentToJSON();
        assertEquals("[{\"Status\":\"Waiting for aproval\",\"Origin\":\"Neko\",\"Cargo\":\"testCargo\",\"Destination\":\"Capetown\",\"ContentType\":\"Fragile\",\"jID\":\"test\",\"Username\":\"test\",\"ID\":\"test\"}]"
        , array.toString());
    }

    @Test
    public void TestReadContentFromJSON() {
        JourneyInfo j = new JourneyInfo();
        j.setOrigin(Port.NEKO);
        j.setDestination(Port.CAPETOWN);
        j.setCargo("testCargo");
        j.setContent_type("Fragile");
        j.setParameter("Username", "test");
        j.setParameter("ID", "test");
        Storage storage =  JourneyStorage.GetInstance();
        JSONArray array = storage.SaveContentToJSON();
        JourneyStorage.GetInstance().getJourneys().clear();
        storage.ReadContentFromJSON(array);
        assertEquals(1, JourneyStorage.GetInstance().getJourneys().size());
    }
}
