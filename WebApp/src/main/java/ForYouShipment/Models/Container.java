package ForYouShipment.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ForYouShipment.Constants.Port;
import ForYouShipment.Workers.IDGenerator;

public class Container {

    private Port location;
    private String id;
    private JourneyInfo journey;
    private Set<JourneyInfo> journeyHistory = new HashSet<>();
    private List<Map<String,String>> measurementsHistory = new ArrayList<>();
   

    public Set<JourneyInfo> getJourneyHistory() {
        return journeyHistory;
    }

    public void addToJourneyHistory(JourneyInfo journey) {
        this.journeyHistory.add(journey);
    }

    public void setMeasurementsHistory(List<Map<String, String>> measurementsHistory) {
        this.measurementsHistory = measurementsHistory;
    }

    public List<Map<String,String>> getMeasurementsHistory() {
        return measurementsHistory;
    }

    /**
     * This method saves a measurement in the history
     * of measurements on the container
     * @param measurements Map containing the measurements done
     */
    public void saveMeasurements(Map<String,String> measurements) {
        this.measurementsHistory.add(new HashMap<>(measurements));
    }

    public Container() {
       setId(IDGenerator.GenerateID());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JourneyInfo getJourney() {
        return journey;
    }

    public void setJourney(JourneyInfo journey) {
        this.journey = journey;
    }

    public Port getLocation() {
        return location;
    }

    public void setLocation(Port location) {
        this.location = location;
    }


}
