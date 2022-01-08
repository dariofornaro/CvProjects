package ForYouShipment.Storage;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import ForYouShipment.Constants.Port;
import ForYouShipment.Models.ClientProfileModel;
import ForYouShipment.Models.ClientUserModel;
import ForYouShipment.Models.ContainerMeasurements;
import ForYouShipment.Models.JourneyInfo;
import ForYouShipment.Models.LogisticsProfileModel;
import ForYouShipment.Models.LogisticsUserModel;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Persistance.StoragePersistance;


public class StorageSerializationTest {
    
    @BeforeEach 
    public void AddData() throws Exception {
        UserModel user = new ClientUserModel();
        user.setProfile(new ClientProfileModel());
        user.getProfile().setParameter("Email", "haha");
        user.setUsername("hello");
        user.setPassword("1234");
        user.setID("1.2.3.4");

        UserStorage.GetInstance().getUsers().add(user);

        user = new LogisticsUserModel();
        user.setProfile(new LogisticsProfileModel());
        user.getProfile().setParameter("Email", "haha");
        user.setUsername("hihi");
        user.setPassword("1234");
        user.setID("1.1.1.1");

        UserStorage.GetInstance().getUsers().add(user);

        ContainerMeasurements container = new ContainerMeasurements();
        container.setParameter("Longitude", "25");
        JourneyInfo journey = new JourneyInfo();
        journey.setParameter("Username", "haha");
        journey.setParameter("ID", "1.2.3.0");
        container.setId("0.1.1.0");
        container.setJourney(journey);
        Map<String,String> m = new HashMap<String,String>();
        m.put("Latitude", "12");
        container.saveMeasurements(m);
        container.addToJourneyHistory(journey);
        container.setLocation(Port.ofString("Lisbon"));

        ContainerStorage.GetInstance().getContainers().add(container);
        JourneyStorage.GetInstance().getJourneys().add(journey);
    }

    @AfterEach
    public void ClearStorages() {
        UserStorage.GetInstance().getUsers().clear();
        ContainerStorage.GetInstance().getContainers().clear();
        JourneyStorage.GetInstance().getJourneys().clear();

    }

    @Test
    public void AbleToWriteAndRead() {
        StoragePersistance.SaveStoragesToDisk();
        StoragePersistance.LoadStoragesFromDisk();
    }
}
