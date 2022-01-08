package ForYouShipment.JourneySearch;

import java.util.ArrayList;
import java.util.List;

import ForYouShipment.Models.ContainerMeasurements;
import ForYouShipment.Search.Criteria;
/**  Gets the container with the provided journey*/
public class CriteriaCID implements Criteria<ContainerMeasurements> {

    @Override
    public List<ContainerMeasurements> meetCriteria(List<ContainerMeasurements> Containers, String query) {
            List<ContainerMeasurements> Container_ID = new ArrayList<ContainerMeasurements>();
            
            for (ContainerMeasurements c: Containers){
                if(c.getId().equals(query))
                    Container_ID.add(c);

            }
        return Container_ID;
    }
}
