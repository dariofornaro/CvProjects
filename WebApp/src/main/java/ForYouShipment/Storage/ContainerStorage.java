package ForYouShipment.Storage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import ForYouShipment.Constants.Port;
import ForYouShipment.Models.Container;
import ForYouShipment.Models.ContainerMeasurements;
import ForYouShipment.Persistance.ContainerFactory;

/**
 * Singleton class storing all informations.
 */
public class ContainerStorage implements Storage{

    // Items to save.
    private Set <ContainerMeasurements> Containers;

    

    public void setContainers(Set<ContainerMeasurements> containers) {
        Containers = Collections.synchronizedSet(new HashSet<>());
        for (ContainerMeasurements m : containers)
            Containers.add(m);
    }

    private ContainerStorage() {
        Containers = Collections.synchronizedSet(new HashSet<>());
    }

    public JSONArray SaveContentToJSON() {
        JSONArray array = new JSONArray();

        for (ContainerMeasurements c : Containers)
            array.put(ContainerFactory.ContainerToJSON(c));
        return array;
    }

    public void ReadContentFromJSON(JSONArray array) {
        Containers = Collections.synchronizedSet(new HashSet<>());

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            ContainerMeasurements u = ContainerFactory.ContainerFromJSON(obj);
            Containers.add(u);
        }
    }

    public String StorageName() { 
        return "ContainerStorage"; 
    }

    public Set<ContainerMeasurements> getContainers() {
        return Containers;
    }

    /**
     * This static method adds new Containers in a given location
     * @param val       Number of new containers to add
     * @param location  Port where to add the containers
     */
    public static void addContainers(int val, Port location) {
        while (val-- > 0){
            ContainerMeasurements c = new ContainerMeasurements();
            c.setLocation(location);
            GetInstance().Containers.add(c);
        }
    }

    /**
     * Gets the total number of containers
     * @return
     */
    public static int countContainers(){
        return GetInstance().Containers.size();
    }

    /**
     * Gets the used containers (not in
     * a journey)
     * @return Used containers in a set
     */
    public static Set<ContainerMeasurements> getUsedContainers(){
        Set<ContainerMeasurements> used = new HashSet<>();
        for (ContainerMeasurements c : instance.Containers){
            if (c.getJourney()!=null){
                used.add(c);
            }
        }
        return used;
    }

    //     /**
    //  * Gets the number of free containers (not in
    //  * a journey)
    //  * @return Number of available container as int
    //  */
    // public static int getFreeContainers(){
    //     int i = 0;
    //     for (Container c : GetInstance().Containers){
    //         if (!(c.getJourney()==null)){
    //             i++;
    //         }
    //     }
    //     return i;
    // }

    /**
     * Returns the number of containters in each Port
     * @param location
     * @return int
     */
    public static int GetNrContainers(Port location) {
        int ans = 0;
        for (Container c : GetInstance().Containers) {
            if (c.getLocation() != null && c.getLocation().equals(location)) 
                ans++;
        }
        return ans;
    }


    public static void InitialiseContainers() {
        for (Port p: Port.class.getEnumConstants())
                ContainerStorage.addContainers(50, p);
    }


    private static ContainerStorage instance = null;

    public static ContainerStorage GetInstance() {
        if (instance == null) {
            instance = new ContainerStorage();
        }
        return instance;
    }
}
