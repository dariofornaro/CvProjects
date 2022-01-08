package ForYouShipment.Models;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;



public class JourneyInfoTest {


    @Test
    public void TestGetCargo() {
        JourneyInfo j = new JourneyInfo();
        assertTrue(j.getCargo() == null);
        j.setCargo("test");
        assertTrue(j.getCargo().equals("test"));
    }
    
    @Test
    public void TestGetID() {
        JourneyInfo j = new JourneyInfo();
        assertTrue(j.getId() != null);
    }




}
