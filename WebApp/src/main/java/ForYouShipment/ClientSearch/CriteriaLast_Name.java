package ForYouShipment.ClientSearch;

import java.util.ArrayList;
import java.util.List;

import ForYouShipment.Models.UserModel;
import ForYouShipment.Search.Criteria;

public class CriteriaLast_Name implements Criteria<UserModel>{

    @Override
    public List<UserModel> meetCriteria(List<UserModel> list, String query) {
        List<UserModel> answer = new ArrayList<UserModel>();
        for (UserModel u : list) {
            if (u.getProfile().getParameter("Last Name").toLowerCase()
                .contains(query.toLowerCase())
                && !u.IsLogisticUser()) {
                answer.add(u);
            }
        }
        return answer;
    }
}