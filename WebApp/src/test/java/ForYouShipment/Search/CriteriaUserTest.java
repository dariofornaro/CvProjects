package ForYouShipment.Search;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ForYouShipment.JourneySearch.CriteriaUser;
import ForYouShipment.Models.JourneyInfo;
import ForYouShipment.Models.JourneyInfo ;

public class CriteriaUserTest {


    
    @Test
    public void TestMeetCriteria() {
        List<JourneyInfo > journeys = new ArrayList<>();
        JourneyInfo  j = new JourneyInfo ();
        JourneyInfo  j2 = new JourneyInfo ();
        j.setParameter("Username", "Test");
        j2.setParameter("Username", "mmm");
        journeys.add(j);
        journeys.add(j2);
        Criteria<JourneyInfo > c = new CriteriaUser();
        journeys = c.meetCriteria(journeys, "Test");
        assertTrue(journeys.size() == 1);

        
    }
    
}
