package ForYouShipment.Search;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ForYouShipment.Constants.Port;
import ForYouShipment.JourneySearch.CriteriaDestination;
import ForYouShipment.JourneySearch.CriteriaOrigin;
import ForYouShipment.Models.JourneyInfo;

public class AndCriteriaTest {

    @Test
    public void TestMeetCriteria() {
        List<JourneyInfo > journeys = new ArrayList<>();
        JourneyInfo  j1 = new JourneyInfo();
        JourneyInfo j2 = new JourneyInfo();

        j1.setOrigin(Port.COPENHAGEN);
        j2.setOrigin(Port.CAPETOWN);
        
        j1.setDestination(Port.COPENHAGEN);
        j2.setDestination(Port.COPENHAGEN);
        

        journeys.add(j1);
        journeys.add(j2);
        

        Criteria<JourneyInfo > o1 = new CriteriaOrigin();
        Criteria<JourneyInfo > o2 = new CriteriaDestination();
        Criteria<JourneyInfo > and = new AndCriteria<JourneyInfo >(o1, o2);
        journeys = and.meetCriteria(journeys, "Copen");
        assertTrue(journeys.size() == 1);

    }
    
}
