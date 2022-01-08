package ForYouShipment.Cucumber;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import ForYouShipment.WebApp.WebAppApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@SpringBootTest
@ContextConfiguration(classes = WebAppApplication.class)
@CucumberOptions(
		features="src/main/resources")

public class Cucumbertest {
    
}

