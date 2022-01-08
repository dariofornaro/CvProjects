package ForYouShipment.Search;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ForYouShipment.JourneySearch.CriteriaCargo;
import ForYouShipment.Models.JourneyInfo;

public class CriteriaCompanyTest {


    
    @Test
    public void TestMeetCriteria() {
        List<JourneyInfo > journeys = new ArrayList<>();
        JourneyInfo j = new JourneyInfo();
        JourneyInfo j2 = new JourneyInfo();
        j.setCargo("Test");
        j2.setCargo("company");
        journeys.add(j);
        journeys.add(j2);
        Criteria<JourneyInfo > c = new CriteriaCargo();
        journeys = c.meetCriteria(journeys, "Test");
        assertTrue(journeys.size() == 1);

        
    }
    
}
