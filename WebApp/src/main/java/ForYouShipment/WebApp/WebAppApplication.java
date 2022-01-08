package ForYouShipment.WebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import ForYouShipment.Models.LogisticsProfileModel;
import ForYouShipment.Models.LogisticsUserModel;
import ForYouShipment.Models.UserModel;
import ForYouShipment.Persistance.PersistanceDaemon;
import ForYouShipment.Persistance.StoragePersistance;
import ForYouShipment.Storage.UserStorage;

@SpringBootApplication @ComponentScan(basePackages = { "ForYouShipment.Controllers" } )
public class WebAppApplication {

	static void InitialiseUsers() {
		UserModel admin = new LogisticsUserModel();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setID("0.0.0.0");
		admin.setProfile(new LogisticsProfileModel());
		admin.getProfile().setParameter("First Name", "Administrator");
		admin.getProfile().setParameter("Last Name", "Administrator");
		admin.getProfile().setParameter("Email", "admin@dtu.dk");
		admin.getProfile().setParameter("Role", "Admin");
		UserStorage.GetInstance().getUsers().add(admin);

	}
	public static void main(String[] args) {
		InitialiseUsers();
		StoragePersistance.LoadStoragesFromDisk();

		PersistanceDaemon deamon = new PersistanceDaemon();
		deamon.start();

		SpringApplication.run(WebAppApplication.class, args);
	}

}
