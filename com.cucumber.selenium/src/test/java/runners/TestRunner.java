package runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "resources/featurelib",
glue = {"stepdefinitions"},
plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"},
tags = "@SmokeTest1",
monochrome = true,
publish = true)
public class TestRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider (parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
