package ForYouShipment.JourneySearch;

import java.util.ArrayList;
import java.util.List;

import ForYouShipment.Models.JourneyInfo;
import ForYouShipment.Search.Criteria;

public class CriteriaJID implements Criteria<JourneyInfo> {

    @Override
    public List<JourneyInfo> meetCriteria(List<JourneyInfo> Journeys, String query) {
            List<JourneyInfo> Journeys_ID = new ArrayList<JourneyInfo>();
            
            for (JourneyInfo j: Journeys){
                if(j.getId().equals(query))
                    Journeys_ID.add(j);

            }
        return Journeys_ID;
    }
}
