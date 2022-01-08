package ForYouShipment.Search;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ForYouShipment.Constants.Port;
import ForYouShipment.JourneySearch.CriteriaDestination;
import ForYouShipment.JourneySearch.CriteriaOrigin;
import ForYouShipment.Models.JourneyInfo;

public class OrCriteriaTest {

    @Test
    public void TestMeetCriteria() {
        List<JourneyInfo > journeys = new ArrayList<>();
        JourneyInfo j1 = new JourneyInfo();
        JourneyInfo j2 = new JourneyInfo();
        JourneyInfo j3 = new JourneyInfo();

        j1.setOrigin(Port.ROTTERDAM);
        j2.setOrigin(Port.CAPETOWN);
        j3.setOrigin(Port.SHANGHAI);
        
        j1.setDestination(Port.CAPETOWN);
        j2.setDestination(Port.PORTO);
        j3.setDestination(Port.NEW_YORK);
        

        journeys.add(j1);
        journeys.add(j2);
        journeys.add(j3);
        

        Criteria<JourneyInfo > o1 = new CriteriaOrigin();
        Criteria<JourneyInfo > o2 = new CriteriaDestination();
        Criteria<JourneyInfo > or = new OrCriteria<JourneyInfo >(o1, o2);
        journeys = or.meetCriteria(journeys, "Cape");
        assertTrue(journeys.size() == 2);

    }

    @Test
    public void TestMeetCriteriaNeg() {
        List<JourneyInfo > journeys = new ArrayList<>();
        JourneyInfo j1 = new JourneyInfo();
        JourneyInfo j2 = new JourneyInfo();
        JourneyInfo j3 = new JourneyInfo();

        j1.setOrigin(Port.CAPETOWN);
        j2.setOrigin(Port.SYDNEY);
        j3.setOrigin(Port.SHANGHAI);
        
        j1.setDestination(Port.CAPETOWN);
        j2.setDestination(Port.PORTO);
        j3.setDestination(Port.NEW_YORK);
        

        journeys.add(j1);
        journeys.add(j2);
        journeys.add(j3);
        

        Criteria<JourneyInfo > o1 = new CriteriaOrigin();
        Criteria<JourneyInfo > o2 = new CriteriaDestination();
        Criteria<JourneyInfo > or = new OrCriteria<JourneyInfo >(o1, o2);
        journeys = or.meetCriteria(journeys, "Cape");
        assertTrue(journeys.size() == 1);

    }
    
}
