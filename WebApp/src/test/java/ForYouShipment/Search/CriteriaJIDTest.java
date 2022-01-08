package ForYouShipment.Search;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ForYouShipment.Constants.Port;
import ForYouShipment.JourneySearch.CriteriaJID;
import ForYouShipment.Models.JourneyInfo;

public class CriteriaJIDTest {


    
    @Test
    public void TestMeetCriteria() {
        List<JourneyInfo > journeys = new ArrayList<>();
        JourneyInfo j = new JourneyInfo();
        JourneyInfo j2 = new JourneyInfo();
        j.setId("1234");
        j2.setId("21234");
        journeys.add(j);
        journeys.add(j2);
        Criteria<JourneyInfo > c = new CriteriaJID();
        journeys = c.meetCriteria(journeys, "1234");
        assertTrue(journeys.size() == 1);

        
    }
    
}