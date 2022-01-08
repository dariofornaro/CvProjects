package ForYouShipment.Workers;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class IDGeneratorTest {
    @Test
    public void TestRandomGenerator() {
        String a = IDGenerator.GenerateID();
        String b = IDGenerator.GenerateID();

        assertNotEquals(a, b);
    }

    
}
