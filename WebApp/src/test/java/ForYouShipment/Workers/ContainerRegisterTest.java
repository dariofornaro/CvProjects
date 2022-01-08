package ForYouShipment.Workers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ForYouShipment.Constants.Port;
import ForYouShipment.Models.ClientUserModel;
import ForYouShipment.Models.Container;
import ForYouShipment.Models.ContainerMeasurements;
import ForYouShipment.Models.JourneyInfo;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Storage.ContainerStorage;

public class ContainerRegisterTest {
    String origin;
    String destination;
    String content_type;
    String company;
    Container container;
    UserModel user;

    @BeforeEach
    public void SetUpJourney() {
        ContainerStorage.addContainers(5, Port.LISBON );
        origin = "Lisbon";
        destination = "Copenhagen";
        content_type = "Fragile";
        company = "Coop";
        container = ContainerStorage.GetInstance().getContainers().iterator().next();
        user = new ClientUserModel();
        user.setUsername("Test");
        user.setID("1.1.1.1");
    }

    @Test
    public void TestSetJourney() throws Exception {
        
        Container c = ContainerRegister.setJourney(origin, destination, content_type, company, user);

        assertTrue(c.getJourney().getOrigin().equals(Port.ofString(origin)));
    }

    @Test
    public void TestSetJourneyInvalidPort() throws Exception {
        origin = "Porto";
        Container c = ContainerRegister.setJourney(origin, destination, content_type, company, user);

        assertTrue(c == null);
    }
    
    @Test
    public void TestGetFreeContainerValid() {
        Port valid_origin = Port.LISBON;
        assertTrue(ContainerRegister.getFreeContainer(valid_origin).getLocation() == valid_origin);
    }

    @Test
    public void TestGetFreeContainerInvalid() {
        assertTrue(ContainerRegister.getFreeContainer(Port.PORTO) == null);
    }

    @Test
    public void TestReturnContainer() throws Exception {
        ContainerMeasurements c = ContainerRegister.setJourney(origin, destination, content_type, company, user);
        JourneyInfo j = c.getJourney();
        assertFalse(j == null);
        ContainerRegister.returnContainer(c);
        assertTrue(c.getJourney() == null);
        assertTrue(c.getLocation() == j.getDestination());
    }


    @Test
    public void GetContainerByIDvalid() {
        ContainerMeasurements container = new ContainerMeasurements();
        container.setId("1234");
        ContainerStorage.GetInstance().getContainers().add(container);
        ContainerMeasurements c = ContainerRegister.GetContainerByID("1234");
        assertTrue(c.getId().equals("1234"));
    }

    @Test
    public void GetContainerByIDinvalid() {
        ContainerMeasurements c = ContainerRegister.GetContainerByID("1234");
        assertTrue(c == null);
    }

    @Test
    public void DeleteContainerTest() {
        ContainerMeasurements c = new ContainerMeasurements();
        c.setId("1234");
        ContainerStorage.GetInstance().getContainers().add(c);
        ContainerRegister.DeleteContainer("1234");
        ContainerMeasurements c2 = ContainerRegister.GetContainerByID("1234");
        assertTrue(c2 == null);

    }
}
