package ForYouShipment.Constants;


import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class PortTest {


    @Test
    public void toStringTest() throws Exception{
        Port p = Port.PORTO;
        assertTrue(p.equals(Port.ofString("Porto")));
    }

    @Test
    public void toStringInvalidTest() throws Exception{

        assertThrows(EnumConstantNotPresentException.class, () -> {
                        Port.ofString("asd");});
    }


    @Test
    public void countPortsTest() {
        assertTrue(Port.values().length == Port.countPorts());
    }
}
    