package ForYouShipment.Search;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ForYouShipment.JourneySearch.CriteriaCID;
import ForYouShipment.Models.ContainerMeasurements;

public class CriteriaCIDTest {

    @Test
    public void TestMeetCriteria() {
        List<ContainerMeasurements > containers = new ArrayList<>();
        ContainerMeasurements c = new ContainerMeasurements();
        ContainerMeasurements c2 = new ContainerMeasurements();
        c.setId("1234");
        c2.setId("21234");
        containers.add(c);
        containers.add(c2);
        Criteria<ContainerMeasurements> criteria = new CriteriaCID();
        containers = criteria.meetCriteria(containers, "1234");
        assertTrue(containers.size() == 1);        
    }
}