package ForYouShipment.JourneySearch;

import java.util.ArrayList;
import java.util.List;

import ForYouShipment.Models.JourneyInfo;
import ForYouShipment.Search.Criteria;


public class CriteriaCargo implements Criteria<JourneyInfo>{
    
    @Override
    public List<JourneyInfo> meetCriteria(List<JourneyInfo> Journeys, String query) {
            List<JourneyInfo> Journeys_cargo = new ArrayList<JourneyInfo>();
            
            for (JourneyInfo j: Journeys) {
                if(j.getCargo().toString().toLowerCase().contains(query.toLowerCase()))
                    Journeys_cargo.add(j);

            }
        return Journeys_cargo;
    }
}
