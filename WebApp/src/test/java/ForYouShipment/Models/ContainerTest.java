package ForYouShipment.Models;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ContainerTest {

    @Test
    public void TestGetID() {
        Container c = new Container();
        assertTrue(c.getId() != null);
    }
    
}
