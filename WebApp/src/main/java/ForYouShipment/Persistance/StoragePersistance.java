package ForYouShipment.Persistance;

import java.io.File;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import ForYouShipment.Storage.ContainerStorage;
import ForYouShipment.Storage.JourneyStorage;
import ForYouShipment.Storage.Storage;
import ForYouShipment.Storage.UserStorage;
import ForYouShipment.Workers.LoggingWorker;

public class StoragePersistance {

    private static Boolean is_unit_test = null;

    private static boolean CheckIfIsJUnitTest() {  
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            if (element.getClassName().startsWith("org.junit.")) {
                return true;
            }           
        }
        return false;
    }

    private static boolean IsJUnitTest() {
        if (is_unit_test == null)
            is_unit_test = CheckIfIsJUnitTest();
        return is_unit_test;
    }

    private static List<Storage> GetAllStorages() {
        List<Storage> storages = new ArrayList<>(); 
        storages.add(UserStorage.GetInstance());
        storages.add(JourneyStorage.GetInstance());
        storages.add(ContainerStorage.GetInstance());
        return storages;
    }

    private static String GetFolderPath() {
        String path = System.getProperty("user.home") + "/.ForYouShipment/";
        
        if (IsJUnitTest())
            path += "UnitTests/";

        File theDir = new File(path);
        if (!theDir.exists())
            theDir.mkdirs();
        return path;
    }

    public static void SaveStoragesToDisk() {
        LoggingWorker.GetInstance().Log("Saving storages to disk.");
        for (Storage item : GetAllStorages()) {
            try (Writer writer = Files.newBufferedWriter(
                    Paths.get(GetFolderPath() + item.StorageName() + ".json"))) {
                JSONArray array = item.SaveContentToJSON();
                writer.write(array.toString());
            }
            catch (Exception e) {
                LoggingWorker.GetInstance().Log("Received error " + e.getMessage()
                        + " when reading jsons from memory");  
            }
        }
    }

    public static void LoadStoragesFromDisk() {
        LoggingWorker.GetInstance().Log("Loading items from memory");
        for (Storage item : GetAllStorages()) {
            try {
                System.out.println(item.StorageName());
                java.nio.file.Path path = Paths.get(GetFolderPath() + item.StorageName() + ".json");
                String JSONtext = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
                JSONArray array = new JSONArray(JSONtext);
                item.ReadContentFromJSON(array);
            }
            catch (Exception e) {
                LoggingWorker.GetInstance().Log("Received error " + e.getMessage()
                        + " when saving json to memory!");
            }
        }
    }
}
