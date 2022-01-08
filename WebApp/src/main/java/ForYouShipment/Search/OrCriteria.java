package ForYouShipment.Search;

import java.util.List;


public class OrCriteria<T> implements Criteria<T>{

    private Criteria<T> criteria;
    private Criteria<T> otherCriteria;

    public OrCriteria(Criteria<T> criteria, Criteria<T> otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<T> meetCriteria(List<T> Journeys, String query) {
        List<T> firstCriteriaItems = criteria.meetCriteria(Journeys, query);
        List<T> otherCriteriaItems = otherCriteria.meetCriteria(Journeys, query);

        for (T j: otherCriteriaItems) {
            if (! firstCriteriaItems.contains(j)) {
                firstCriteriaItems.add(j);
            }
        }
        return firstCriteriaItems;
    }
}