package driverfactory;

import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utility.PropertiesReader;

public class DriverFactory {
	
	//Singelton pattern implemented with Threadlocal Webdriver
	
	private static WebDriver driver;
	private static ThreadLocal<WebDriver> dr = new ThreadLocal<>();
	public static WebDriver getDriver() {
		return dr.get();
	}
	public static void setDriver (WebDriver driverref) {
		dr.set(driverref);
	}
	public static void unload() {
		dr.remove();
	}

	//All drivers are managed with SeleniumManager class that comes inbuilt with Selenium 4.6x onwards
	//Hence, no separate driver and path management is needed.

	public static void initDriver(String browserName) {//initializes driver with browser name as argument
		PropertiesReader.loadData();
		System.out.println("init driver");
		if(Objects.isNull(driver)) {
			if(browserName.equalsIgnoreCase("Chrome")) {
				driver=new ChromeDriver();

			}else if (browserName.equalsIgnoreCase("Firefox")) {
				driver=new FirefoxDriver();
			}
			
			else if (browserName.equalsIgnoreCase("Edge")) {
				driver=new EdgeDriver();
			}
			setDriver (driver);
		}
	}
	
	public static void quitDriver() {
		if(Objects.nonNull (getDriver())) {
			getDriver().quit();
			unload();
		}
	}
}
