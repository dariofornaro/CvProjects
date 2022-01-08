package ForYouShipment.JourneySearch;

import java.util.ArrayList;
import java.util.List;

import ForYouShipment.Models.ContainerMeasurements;
import ForYouShipment.Search.Criteria;
/**  Gets the container with the provided journey*/
public class CriteriaCJID implements Criteria<ContainerMeasurements> {

    
    @Override
    public List<ContainerMeasurements> meetCriteria(List<ContainerMeasurements> Containers, String query) {
            List<ContainerMeasurements> Journeys_ID = new ArrayList<ContainerMeasurements>();
            
            for (ContainerMeasurements j: Containers){
                if(j.getJourney() != null && j.getJourney().getId().equals(query))
                    Journeys_ID.add(j);

            }
        return Journeys_ID;
    }
}
