package ForYouShipment.Search;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ForYouShipment.JourneySearch.CriteriaContent_Type;
import ForYouShipment.Models.JourneyInfo;

public class CriteriaContent_TypeTest {


    
    @Test
    public void TestMeetCriteria() {
        List<JourneyInfo > journeys = new ArrayList<>();
        JourneyInfo j = new JourneyInfo();
        JourneyInfo j2 = new JourneyInfo();
        j.setContent_type("Test");
        j2.setContent_type("Content_type");
        journeys.add(j);
        journeys.add(j2);
        Criteria<JourneyInfo > c = new CriteriaContent_Type();
        journeys = c.meetCriteria(journeys, "Test");
        assertTrue(journeys.size() == 1);

        
    }
    
}
