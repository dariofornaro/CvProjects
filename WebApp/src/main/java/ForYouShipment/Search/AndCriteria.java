package ForYouShipment.Search;

import java.util.List;


public class AndCriteria<T> implements Criteria<T> {

    private Criteria<T> criteria;
    private Criteria<T> otherCriteria;

    public AndCriteria(Criteria<T> criteria, Criteria<T> otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<T> meetCriteria(List<T> Journeys, String query) {
        List<T> firstCriteria = criteria.meetCriteria(Journeys, query);
        return otherCriteria.meetCriteria(firstCriteria, query);
    }
}
