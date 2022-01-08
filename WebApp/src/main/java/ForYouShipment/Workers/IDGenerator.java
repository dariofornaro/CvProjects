package ForYouShipment.Workers;

import java.util.UUID;

public class IDGenerator {
    private IDGenerator() { }

    public static String GenerateID() {
        String id = UUID.randomUUID().toString();
        //LoggingWorker.GetInstance().Log("Generated new id: " + id);
        return id;
    }
}
