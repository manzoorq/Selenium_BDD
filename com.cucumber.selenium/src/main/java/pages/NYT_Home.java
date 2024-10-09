package pages;


import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.PropertiesReader;


public class NYT_Home {//page object class

	WebDriver driver;

	@FindBy(xpath = "//nav[@data-testid='desktop-nested-nav']/ul/li")
	List<WebElement> menuContainer;

	@FindBy(xpath = "//button[@data-testid='search-button']")
	WebElement searchBtn;

	@FindBy(xpath = "//input[@data-testid='search-input']")
	WebElement searchBox;

	@FindBy(xpath = "//button[@data-testid='search-submit']")
	WebElement goBtn;

	@FindBy(xpath = "//input[@data-testid='search-page-text-field']")
	WebElement searchResultsPageTextField;

	@FindBy(xpath = "//ol[@data-testid='search-results']")
	WebElement searchResultsContainer;

	@FindBy(xpath = "//ol[@data-testid='search-results' ]/li[@data-testid='search-bodega-result']")    
	List<WebElement> searchResultsList;

	@FindBy(xpath = "//span[text()='Log in']")
	WebElement loginBtn;

	@FindBy(xpath = "//h2[text()='Log in or create an account']")
	WebElement loginPageHeader;

	@FindBy(xpath = "//input[@id='email']")
	WebElement uNameField;

	@FindBy(xpath = "//button[@data-testid='google-sso-button']")
	WebElement googleSSOBtn;

	@FindBy(xpath = "//button[@data-testid='facebook-sso-button']")
	WebElement facebookSSOBtn;

	@FindBy(xpath = "//button[@data-testid='apple-sso-button']")
	WebElement appleSSOBtn;

	@FindBy(xpath = "//a[@data-testid='b2b-sso']")
	WebElement b2bSSOBtn;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement continueBtn;

	@FindBy(xpath = "//input[@id='password']")
	WebElement pwdField;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginSubmitBtn;

	@FindBy(xpath = "//a[@data-testid='subscribe-button']")
	WebElement subscribeBtn;

	@FindBy(xpath = "//span[@data-testid='todays-date']")
	WebElement displayDate;
	
	
	@FindBy(xpath = "//p[@class='captcha__human__title']")
	WebElement captchaHumanTitle;
	
	@FindBy(xpath = "//li[contains(@data-testid,'international')]")
	WebElement editionInternational;
	
	@FindBy(xpath = "//li[contains(@data-testid,'canada')]")
	WebElement editionCanada;
	
	@FindBy(xpath = "//li[contains(@data-testid,'spanish')]")
	WebElement editionEspanol;
	
	@FindBy(xpath = "//li[contains(@data-testid,'chinese')]")
	WebElement editionChinese;

	public NYT_Home(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getDisplayedDate() {
		return displayDate.getText();
	}

	/*
	 * Method desc: Verifies the menu items displayed in the home page and compares
	 * against the expected values passed as a List of strings to this method. The
	 * List<String> can be read from an excel file (.xlsx) with the
	 * ExcelUitls>readData method.
	 */
	public boolean verifyMenuItems(List<String> expMenuItems) {
		boolean result = false;
		try {
			if(expMenuItems.size()==menuContainer.size()) {
				for(int i=0; i<expMenuItems.size();i++) {
					if(menuContainer.get(i).getText().equals(expMenuItems.get(i))){//true if result matches
						result = true;
					}
					else {					//false if result does not match
						result = false;
					}
				}
			}
			else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * Method Desc: Clicks the search button in the Nyc times website's the top left corner of the home page.
	 * Enters the search text in the search box.
	 * Clicks on the magnify icon to navigate to the search results page.
	 * Gets the header text displayed at the top of the page and returns it.
	 */ 
	public String searchWithKeywordAndGetResultPageHeader(String text) {
		searchBtn.click();
		searchBox.clear();	
		searchBox.sendKeys(text);	
		goBtn.click();
		return searchResultsPageTextField.getAttribute("value");
	}

	/*
	 * Method Desc: This captures the title text of each search result from the list of search results
	 * displayed in the search results page. 
	 * It compares the title text with the search keyword and returns false in case of mismatch
	 */
	public boolean verifySearchResults(String text) {
		boolean result = false;
		int relevantResultCount = 0;
		int irrelevantResultCount = 0;
		for (WebElement we:searchResultsList) {
			//if(!we.getText().contains(text)) {
			if(!we.getText().toLowerCase().contains(text.toLowerCase())) {
				irrelevantResultCount++;
			}
			else 
				relevantResultCount++;
		}

		if(relevantResultCount==searchResultsList.size()) {
			result = true;
		}
		return result;
	}

	/*
	 * Method Desc: This method logins to the Nyc Times website's from the login
	 * page. However since the website has a human identification verifier so it is
	 * not possible to complete the login. Hence after entering username and
	 * password and submitting, the navigation to human verification page is
	 * considered as pass for this test
	 * It uses Properties file reader utility to read user name and password from resources/config.proprties file
	 */
	public boolean login(String uname, String pwd) {
		PropertiesReader.loadData();
		uNameField.sendKeys(uname);
		continueBtn.click();
		pwdField.sendKeys(pwd);
		loginSubmitBtn.click();
		if(driver.getCurrentUrl().contains(PropertiesReader.getProp("url_myaccounts"))) {
			return true;
		}
		else
			return false;
	}

	//Method Desc: This method navigates to the login page from the Nyc Times home page.
	public void navToLoginPage() {
		loginBtn.click();
	}

	public boolean isLoginPageHeaderDisplayed(){
		return loginPageHeader.isDisplayed();
	}

	public boolean isEmailFieldAndContiuneBtnDisplayed(){
		return uNameField.isDisplayed() && continueBtn.isDisplayed();
	}

	public boolean isSocialMediaSSOBtnsDisplayed(){
		return googleSSOBtn.isDisplayed() && googleSSOBtn.isDisplayed() && appleSSOBtn.isDisplayed() ;
	}

	public boolean isB2BSSOLinkDisplayed(){
		return b2bSSOBtn.isDisplayed();
	}
	
	public boolean isCaptchaTitleDisplayed(){
		return captchaHumanTitle.isDisplayed();
	}
	
	public void navToInternationalEdition() {
		editionInternational.click();
	}
	
	public void navToCanadaEdition() {
		editionCanada.click();
	}
	
	public void navToEspanolEdition() {
		editionEspanol.click();
	}
	
	public void navToChineseEdition() {
		editionChinese.click();
	}
}
