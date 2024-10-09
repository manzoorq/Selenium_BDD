package stepdefinitions;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import driverfactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utility.PropertiesReader;

public class Hooks {

	WebDriver driver;

	@After
	public void teardown() {
		DriverFactory.quitDriver();
	}

	@Before
	public void setup_browser() {
		try {
			PropertiesReader.loadData();
			DriverFactory.initDriver(PropertiesReader.getProp("browser"));
			driver = DriverFactory.getDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
