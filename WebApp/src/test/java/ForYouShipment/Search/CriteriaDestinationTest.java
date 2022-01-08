package ForYouShipment.Search;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ForYouShipment.Constants.Port;
import ForYouShipment.JourneySearch.CriteriaDestination;
import ForYouShipment.Models.JourneyInfo;

public class CriteriaDestinationTest {


    
    @Test
    public void TestMeetCriteria() {
        List<JourneyInfo > journeys = new ArrayList<>();
        JourneyInfo j = new JourneyInfo();
        JourneyInfo j2 = new JourneyInfo();
        j.setDestination(Port.ROTTERDAM);
        j2.setDestination(Port.CAPETOWN);
        journeys.add(j);
        journeys.add(j2);
        Criteria<JourneyInfo > c = new CriteriaDestination();
        journeys = c.meetCriteria(journeys, "ROT");
        assertTrue(journeys.size() == 1);

        
    }
    
}