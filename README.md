 # SeleniumCu_FW Selenium Cucumber BDD Framework
A robust Test Automation Framework with Selenium 4, Cucumber 7 and TestNG 7 designed to build and run automated tests on web based applications. It is fully compatible with BDD methodology to write and implement executable specifications(living test documentation).

## Getting Started
- Clone the repo 'SeleniumCu_FW' to you local machine from commandline by typing `git clone <repo url>.
- Alternatively open any IDE like Eclipse or InetlliJ and import/clone project from the github repo url as depicted below. 
![image](https://github.com/user-attachments/assets/2070a1fc-104d-425d-928a-7597b84d03e2)

### Prerequisite
- Java Development Kit (JDK 8 or higher)
- Maven
- Git (optional)
- Jenkins (opional)
- IDE Eclipse or IntelliJ
  
### Run tests locally
To run tests right click on the project root directory in the IDE > Package Explorer and click Run As > Maven test

### Run tests from commandline
1. To run tests from commandline open windows command prompt and navigate to the project's root directory.
2. Type `mvn clean test`

### Run tests on different browsers
By default the tests run in Chrome browser. If you wish to run it in Firefox browser or Edge navigate to the path _./resources/_ and open the file config.properties.
Change the value of the key `browser` to the desired browser name i.e. `chrome` or `firefox` or `edge` as per requirement and save the file.
Currently only Chrome, Firefox and Edge browsers are supported. In future support for browsers may be added.
![image](https://github.com/user-attachments/assets/d71ef424-b1d9-449b-95e7-6dee6c1f51b4)



### Read data from Excel
1. Use the Exceutils class under the utility package to read data from excel sheet.
2. The data is read as a HashMap with the first column being the key and the data in the remaining columns are stored as a list of strings is the value. For example the 'MenuItems' in the picture below is a key and the List of strings "U.S."	"World"	"Business"	"Arts"	"Lifestyle"	"Opinion"	"Audio"	"Games"	"Cooking"	"Wirecutter" and "The Athletic" is the value.
3. To understand the sample usage in code refer to the _Scenario Outline : As a user I want to verify the menu items_ in the nyctimes.feature.
4. The excel _'SheetName'_ and _'DataKey'_ are passed from the cucumber Examples table.
   ![image](https://github.com/user-attachments/assets/c3f79efc-f29e-4030-b38f-0c165859db7b)

### Read data from Properties file
1. The properties file reader utility is implemented under utils package.
2. It helps to read data data from the config.properties file present under the _./resources/config.properties_ file.
3. Use the loadData() method to load the config.property file and getObject(<<property name>) method to read properties.
4. For example please refer to the Log in functionliy test scenario where username and password have been read from the config.properties file.

![image](https://github.com/user-attachments/assets/1f8476ba-b70f-4c7b-bf3e-8fc8b0068608)


### Confguring run tags
1. Run tags can be configured in the _TestRunner_ files under _./src/test/java/runners_ package. For example add the tag below **'@SmokeTest1'** runs all tests with the same tag in the featurelib folder. For more details on tags please refer to [Cucumber reference documentation on tags](https://cucumber.io/docs/cucumber/api/?lang=java#tags).
2. In the Runner file _src/test/java/runners_ folder open the TestRunner file and add your tag.
3. Run the test from commandline or IDE.

Fature file

![image](https://github.com/user-attachments/assets/0ab17f35-b7a1-48ae-a234-58cdbfefa5cc) ![image](https://github.com/user-attachments/assets/afa3b34b-969b-4776-8e82-f68daf0a9e23)

Runner file

![image](https://github.com/user-attachments/assets/4ab828cf-d26e-4093-8490-b9dd4ca010a9)


### Adding new Feature files and Step Definitions
1. To add new features create your feature files under the _./resources/featurelib/_ folder. Refer to [Cucumber Gherkin Reference](https://cucumber.io/docs/gherkin/reference/) for writing better formatted feature files in Gherkin format.
2. For step implementations add you step definition files under  _./src/test/java/stepdefinitions_ package. For help please refer to the [Cucumber Step Sefinitions documentation](https://cucumber.io/docs/cucumber/step-definitions/?lang=java)

### Run tests in parallel
1. To run tests in parallel update the number of threads in the _./resources/testng.xml/_ file to number of derired test you want to run in parallel. For example the  _thread-count_ is set to _2_ as depicted in the testng.xml file below. Make sure atleast 2 scenarios are available to run in the feature files as per the run tag configured in _TestRunner_ file.![image](https://github.com/user-attachments/assets/22ffea88-902d-46ff-8331-fa393881f5a0)
2. Run the run test locally from IDE or from commandline `mvn clean test`. The tests should be trigerred parallelly.

### Run from Jenkins(CI/CD)
1. Spin up Jenkins server in you system and create a maven style project.
2. Add the pom.xml local path in the project/job configuration.![image](https://github.com/user-attachments/assets/7cb550d2-5b73-4da7-aaf6-ee18be03def7)
3. Alternatively to build from remote you can provide github/scm details like repository url, branch name etc.![image](https://github.com/user-attachments/assets/4362c845-a831-4c7c-a4c8-b2f240294d8d)
4. Save the project/job and Click _Build Now_.
5. Refer to **sample jenkins console output logs** from previous Jenkins runs under the folder _./archive-reports/jenkins_

## Test Reports
The test generate reports the following reports automatically and stores them under _./target_ folder-
  1. cucumber.json
  2. cucumber.html

Please refer to the sample reports from previous runs under  _./archive-reports/_.

For a quick reference a screenshot of the report provided below.
![image](https://github.com/user-attachments/assets/b82cfac6-8e6a-40cf-b0a1-45dec3901c89)

## Sample Test Cases
- The detailed sample test cases for testing the https://www.nytimes.com/ web application is present under the _./Test Cases_ folder.
- The corresponding feature file nyctimes.feature can be found under the _./reosources/featurelib_ package. 
- 4 sample tests have been added as below-
  1. Verify if today's is displayed correctly in the home page as per America/New York time zone.
  2. Verify if main menu is displayed and has the correct number and list of items.
  3. Verify if user is able to search for desired content and view relevant search results.
  4. Verify if the login page displays the email, social media login and school/work login  buttons. And the user is able to enter the credentials and login.
    
## Framework Best Practices
Thw following is a non exhaustive list of the best practices and principles implemented in the framework-
1. The framework follows the **'cucumber maven project'** archetype.
   - The _test packages_ and _methods_ under src/test/java folder.
   - _Page classess, factory classes_ and utility methods under src/main/java folder.
   - _Feature files, testng.xml_ and _excel data files_ in resources folder.
   - _Step Definition files_ in stepdefinition package in src/test/java.
2. **Factory pattern** implemented with Pagefactory and Driver factory classes.
3. **Pagefactory design pattern** for object repository implemented in page classes.
4. **Singleton design pattern** implemented in Drivefactory.
5. **Threadsafety** has been ensured with the use of Threadlocal.
6. The framework supports **parallel execution** with testng.
7. Currently the framework supports **crossbrowser testing** via passing browser names as parameters in feature files and runing multiple features. There a scope for future enhancements to add Browser and Platform factory class for enhanced cross browser and cross platform testing.
8. The browsers drivers are managed by **SeleniumManager** class that comes along with Selenium 4.6x and above. Hence, there is no need to use Webdriver Manager dependency or manually manage browser driver paths and setting system properties for various browsers in the code.
9. The framework can be run from Jenkins tool for **CICD**. Refer to section 'Run from Jenkins(CI/CD)' above for more details.
10. The Cucumber feature files have been written in Gherkin fromat best practices. The following cucumber traits have been implemented and exemplified in the _nyctimes.feature_ file
     - **Cucumber hooks** to initialize and closure activities.
     - **Scenario** with parameters.
     - **Scenario oulines** with Example tables data read from Excel.
     - Use of cucumber **tags** to control test selection for execution.
  11. The test runner files allows to configue the cucumber run time options like feature and step definition files and the type and output folder of reports that can be generated.
  12. The framework is **Data Driven** and supports
      - **External data** from Excel files. See 'Read data from excel' section for more details.
      - Data from Cucumber data tables, step perameters and Example tables.
  13. The code has properly descriibed **code comments** especially for reviewers and test automation developers to understand the usage with ease.
  14. The code base follows the **Test Automation Principles**-
      - **Reusability** (**DRY**-Don't Repeat Yourself)- The DRY principle emphazises to avoid code duplication through. This is achieved via reusable steps definitions, page methods e.g. `User is on the Home page of <url>`.
      - **Maintainability**- via isolation of code from locators (PageFactory implementation), use of test data readers (excel and config.properties).
      - **Scalabilty**- The prallel execution allows to run multiple tests on larger scale.
      - **Extensibility**- The utils packages can be enhanced with more util classes like DB connectors, screenshot utilities etc.
      - **KISS**(Keep it simple, stupid)- It emphasizes keeping code simple and avoiding unncessary complexity. This help in easy dedugging and troubleshooting. This framework writes simple and concise code.
      - **SRP**(Single Responsibility)- The PageFactory closely imeplements the SRP as if deals with the managemnet of page objects and interactions.

## Built With
- [Selenium](https://www.selenium.dev/documentation/webdriver/)- Browser automation framework conforming to W3C standard, that automates browser natively like a real user.
- [Cucumber](https://cucumber.io/docs/cucumber/)- Behaviour Driven Development framework for writing executable tests and specifications.
- [Maven](https://maven.apache.org/)- Project build and dependency management tool.
- [TestNG](https://testng.org/)-  Testing framework

## Scope for enhancements
The following features are good to have and are planned to be added to the framework in future.
1. Better reporting tools like Extent Reports or Allure Reports.
2. Taking screenshots and attaching to reports.
3. Scenario logger to log custom conditional pass, fail, skip statements in cucumber reports.
4. Test Failure analyzer and retry logics to handle flakiness.
5. Password encryptions.
6. Better exceptions handling in test and utility methods.
7. Handled wrapper methods for browser actions like clicking, entering text etc.
8. Explicit and fluent waits implementations.
9. Strongly implement SOLID principles in the code base like separating complex page methods from page classes, creating more inheritable classes and interfaces like base class.
