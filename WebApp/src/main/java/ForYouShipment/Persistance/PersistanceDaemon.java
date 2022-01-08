package ForYouShipment.Persistance;

import java.util.concurrent.TimeUnit;

public class PersistanceDaemon extends Thread {
    final static int SECONDS_BETWEEN_SAVES = 20;

    @Override
    public void run() {
        System.out.println("Persistance Daemon Started");
        try {
            while (true) {
                TimeUnit.SECONDS.sleep(SECONDS_BETWEEN_SAVES);
                System.out.println("Persitance Daemon: Saving data");
                StoragePersistance.SaveStoragesToDisk();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        

    }
}
