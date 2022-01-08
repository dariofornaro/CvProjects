package ForYouShipment.Search;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ForYouShipment.Constants.Port;
import ForYouShipment.JourneySearch.CriteriaOrigin;
import ForYouShipment.Models.JourneyInfo;

public class CriteriaOriginTest {


    
    @Test
    public void TestMeetCriteria() {
        List<JourneyInfo > journeys = new ArrayList<>();
        JourneyInfo j = new JourneyInfo();
        JourneyInfo j2 = new JourneyInfo();
        j.setOrigin(Port.ROTTERDAM);
        j2.setOrigin(Port.CAPETOWN);
        journeys.add(j);
        journeys.add(j2);
        Criteria<JourneyInfo > c = new CriteriaOrigin();
        journeys = c.meetCriteria(journeys, "Rot");
        assertTrue(journeys.size() == 1);

        
    }
    
}