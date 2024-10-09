package stepdefinitions;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import driverfactory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.NYT_Home;
import utility.ExcelUtils;
import utility.PropertiesReader;

public class StepDefs {
	WebDriver driver = DriverFactory.getDriver();;
	NYT_Home nyHomeObj;
	ExcelUtils excelReader;

	@Given("User is on the Home page")
	public void user_is_on_the_home_page() {
		PropertiesReader.loadData();
		driver.get(PropertiesReader.getProp("url"));
		nyHomeObj=new NYT_Home(driver);
	}

	@Then("Verify the home page title is {string}")
	public void verify_the_home_page_title(String title) {
		Assert.assertEquals(driver.getTitle(), title, "Title is incorrect");
	}

	@Then("Verify the home page title")
	public void verify_the_home_page_title() {
		PropertiesReader.loadData();
		Assert.assertEquals(driver.getTitle(), PropertiesReader.getProp("home_page_title"), "Title is incorrect");
	}
	
	/*
	 * Method Desc: This method verifies if the correct date is displayed in the top
	 * left corner of the Nyc Times website as per America/New York time zone.
	 * This is an important test as the display of correct date is absolutely critical for a Daily News website.
	 */
	@And("Verify todays date is correctly displayed in the home page")
	public void verify_todays_date() {
		//Getting and formatting America/New_York local date as expected date
		Date dt = new Date();
		DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");//formatting the expected date as per the date format of the displayed date
		dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		String expectedDate = dateFormat.format(dt);


		//Getting and formatting Home page date as actual date
		String displayedDate = nyHomeObj.getDisplayedDate();//get the date displayed on the home page as string
		Date dt1 = new Date();
		DateFormat dateFormat1 = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
		try {
			dt1 = dateFormat1.parse(displayedDate);//convert the date string to Date obj
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String displayedDate1 = dateFormat1.format(dt1);//format the displayed date object to expected date format and get as string
		Assert.assertEquals(displayedDate1,expectedDate, "The displayed date: " + displayedDate1 + " is incorrect");
	}//compare the actual and expected date strings

	@Then("Verify the main menu {string} and {string}")
	public void verify_the_main_menu(String sheetName, String dataKey) {
		excelReader = new ExcelUtils();
		PropertiesReader.loadData();
		HashMap<String, List<String>> excelDataMap= excelReader.readData(PropertiesReader.getProp("excelPath"), sheetName);
		Assert.assertTrue(nyHomeObj.verifyMenuItems(excelDataMap.get(dataKey)));
	}

	/*
	 * Method Desc: This method tests the Nyc times website's search functionality in the top left corner of the home page.
	 * It searches for a text passed as an argument to it.
	 * It navigates to the search results page and verifies the search text is displayed at the top of the page.
	 */ 
	@When("User searches for desired content with keyword {string}")
	public void user_searches_for_desired_content_with_keyword(String keyword) {
		String searchResultPageHeader = nyHomeObj.searchWithKeywordAndGetResultPageHeader(keyword);
		Assert.assertEquals(searchResultPageHeader, keyword, "FAIL- The search results page header did not contain the search keyword");
	}

	/*
	 * Method Desc: This method tests the relevance of the search results in the
	 * search results page. It assumes that the search keyword should be present in
	 * the title of each result item. It logs a failure notifying the user
	 * if a certain search result did not contain the search keyword, indicating irrelevance.
	 */
	@Then("The relevant search results should be displayed for {string}")
	public void the_relevant_search_search_results_should_be_displayed(String keyword){
		Assert.assertTrue(nyHomeObj.verifySearchResults(keyword), "FAIL- The titles of one or more search results did not contain the search keyword");
	}

	@When("The user navigates to Login page")
	public void the_user_navigates_to_Login_page() {
		nyHomeObj.navToLoginPage();
	}

	@Then("Verify Login page header")
	public void verify_Login_page_header() {
		Assert.assertTrue(nyHomeObj.isLoginPageHeaderDisplayed(), "FAIL- Login Page header not displayed");
	}

	@Then("Verify Email field and Continue button")
	public void verify_email_field_and_continue_button() {
		Assert.assertTrue(nyHomeObj.isEmailFieldAndContiuneBtnDisplayed(), "FAIL- Either Email field or Continue button is not displayed");
	}

	@Then("Verify Social Media SSO buttons")
	public void verify_Social_Media_SSO_buttons(){
		Assert.assertTrue(nyHomeObj.isSocialMediaSSOBtnsDisplayed(), "FAIL- One or more social media buttons not displayed");
	}

	@Then("Verify B2B SSO button")
	public void verify_B2B_SSO_button() {
		Assert.assertTrue(nyHomeObj.isSocialMediaSSOBtnsDisplayed(), "FAIL- B2B SSO link not displayed");
	}



	@Given("Log in with test credentials")
	public void log_in_with_test_credentials_from_properties() {
		PropertiesReader.loadData();
		Assert.assertTrue(nyHomeObj.login(PropertiesReader.getProp("username"), PropertiesReader.getProp("password")));
	}
	
	@And("The verify human page should be displayed")
	public void verify_human_page_should_be_displayed() {
		Assert.assertTrue(nyHomeObj.isCaptchaTitleDisplayed());
	}

	@When("User changes the edition to {string}")
	public void user_changes_the_edition(String edition) {
		try {
			switch(edition.toUpperCase()){    
			case "INTERNATIONAL":    
				nyHomeObj.navToInternationalEdition();
				break;  //optional  
			case "CANADA":    
				nyHomeObj.navToCanadaEdition();
				break;  //optional  
			case "ESPANOL":    
				nyHomeObj.navToEspanolEdition();
				break;  //optional  
			case "CHINESE":
				nyHomeObj.navToChineseEdition();

			}    
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	@And("User verifies the {string} edition is displayed")
	public void user_verifies_the_edition_is_displayed(String edition) {
		PropertiesReader.loadData();
		try {
			switch(edition.toUpperCase()){    
			case "INTERNATIONAL":
				System.out.println(driver.getCurrentUrl());
				System.out.println(PropertiesReader.getProp("url_international_edition"));
				Assert.assertEquals(driver.getCurrentUrl(), PropertiesReader.getProp("url_international_edition"));
				break;  //optional  
			case "CANADA":    
				System.out.println(driver.getCurrentUrl());
				System.out.println(PropertiesReader.getProp("url_canada_edition"));
				Assert.assertEquals(driver.getCurrentUrl(), PropertiesReader.getProp("url_canada_edition"));
				break;  //optional  
			case "ESPANOL":
				System.out.println(driver.getCurrentUrl());
				System.out.println(PropertiesReader.getProp("url_espanol_edition"));
				Assert.assertEquals(driver.getCurrentUrl(), PropertiesReader.getProp("url_espanol_edition"));
				break;  //optional  
			case "CHINESE":
				System.out.println(driver.getCurrentUrl());
				System.out.println(PropertiesReader.getProp("url_chinese_edition"));
				Assert.assertEquals(driver.getCurrentUrl(), PropertiesReader.getProp("url_chinese_edition"));

			}    
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
}
