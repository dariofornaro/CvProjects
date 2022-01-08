package ForYouShipment.Search;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ForYouShipment.JourneySearch.CriteriaCJID;
import ForYouShipment.Models.ContainerMeasurements;
import ForYouShipment.Models.Journey;
import ForYouShipment.Models.JourneyInfo;

public class CriteriaCJIDTest {

    @Test
    public void TestMeetCriteria() {
        List<ContainerMeasurements > containers = new ArrayList<>();
        ContainerMeasurements c = new ContainerMeasurements();
        ContainerMeasurements c2 = new ContainerMeasurements();
        JourneyInfo j1 = new JourneyInfo();
        j1.setId("1234");
        JourneyInfo j2 = new JourneyInfo();
        j2.setId("liusvdf");
        c.setJourney(j1);
        c2.setJourney(j2);
        containers.add(c);
        containers.add(c2);
        Criteria<ContainerMeasurements> criteria = new CriteriaCJID();
        containers = criteria.meetCriteria(containers, "1234");
        assertTrue(containers.size() == 1);        
    }

    @Test
    public void TestMeetCriteriaInvalid() {
        List<ContainerMeasurements > containers = new ArrayList<>();
        ContainerMeasurements c = new ContainerMeasurements();
        ContainerMeasurements c2 = new ContainerMeasurements();
        JourneyInfo j1 = new JourneyInfo();
        j1.setId("1234");
        c.setJourney(j1);
        containers.add(c);
        containers.add(c2);
        Criteria<ContainerMeasurements> criteria = new CriteriaCJID();
        containers = criteria.meetCriteria(containers, "54");
        assertTrue(containers.size() == 0);        
    }
}