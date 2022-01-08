package ForYouShipment.JourneySearch;

import java.util.ArrayList;
import java.util.List;

import ForYouShipment.Models.JourneyInfo;
import ForYouShipment.Search.Criteria;

public class CriteriaUser implements Criteria<JourneyInfo> {

    @Override
    public List<JourneyInfo> meetCriteria(List<JourneyInfo> Journeys, String query) {
            List<JourneyInfo> Journeys_User = new ArrayList<JourneyInfo>();
            
            for (JourneyInfo j: Journeys){
                if(j.getParameter("Username").toLowerCase().contains(query.toLowerCase()))
                    Journeys_User.add(j);
            }
            
        return Journeys_User;
    }
    
}
