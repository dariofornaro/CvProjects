package ForYouShipment.JourneySearch;

import java.util.ArrayList;
import java.util.List;

import ForYouShipment.Models.ContainerMeasurements;
import ForYouShipment.Search.Criteria;

/** Use this class to filter find a container by JourneyInfo */
public class CriteriaJourney implements Criteria<ContainerMeasurements> {

    /** This method returns a List with one ContainerMeasurements object 
     * matching the given Journey ID
     * @param list  ContainerStorage converted to list
     * @param query JourneyID
     * @return List with one container matching the JourneyID
     */
    @Override
    public List<ContainerMeasurements> meetCriteria(List<ContainerMeasurements> list, String query) {
        List<ContainerMeasurements> answer = new ArrayList<ContainerMeasurements>();
        for (ContainerMeasurements c : list){
            if (c.getJourney().getId().equals(query)){
                answer.add(c);
                return answer;
            }
        }

        return null; //TODO Create our own exception 
    }
    
}
