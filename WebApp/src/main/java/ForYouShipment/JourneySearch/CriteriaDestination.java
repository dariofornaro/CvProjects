package ForYouShipment.JourneySearch;

import java.util.ArrayList;
import java.util.List;

import ForYouShipment.Models.JourneyInfo;
import ForYouShipment.Search.Criteria;

public class CriteriaDestination implements Criteria<JourneyInfo> {
 
    @Override
    public List<JourneyInfo> meetCriteria(List<JourneyInfo> Journeys, String query) {
            List<JourneyInfo> Journeys_destination = new ArrayList<JourneyInfo>();
            
            for (JourneyInfo j: Journeys ){
                if(j.getDestination().toString().toLowerCase().contains(query.toLowerCase()))
                    Journeys_destination.add(j);

            }
        return Journeys_destination;
    }
}
