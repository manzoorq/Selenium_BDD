Feature: NYC Times functional tests

@SmokeTest1
Scenario: As a user I want to verify if the home page displays today's date 
Given User is on the Home page
Then Verify the home page title
#Then Verify the home page title is "The New York Times International - Breaking News, US News, World News, Videos" 
And Verify todays date is correctly displayed in the home page

@SmokeTest
Scenario Outline: As a user I want to verify the menu items
Given User is on the Home page
And Accept the Terms pop up
Then Verify the home page title
And Verify the main menu "<SheetName>" and "<DataKey>"
Examples:
| SheetName | DataKey |
| data      | MenuItems	|

@SmokeTest
Scenario: As a user I want to search for content
Given User is on the Home page
And Accept the Terms pop up
When User searches for desired content with keyword "Biden"
And Accept the Terms pop up
Then The relevant search results should be displayed for "Biden"

@SmokeTest
Scenario: As a registered user I want login to NYC Times
Given User is on the Home page
When The user navigates to Login page
Then The verify human page should be displayed
#Then Verify Login page header
#And Verify Email field and Continue button
#And Verify Social Media SSO buttons
#And Verify B2B SSO button
#And Log in with test credentials

@SmokeTest
Scenario: As a user I want change the edition of NYC Times
Given User is on the Home page
And Accept the Terms pop up
When User changes the edition to "chinese"
Then User verifies the "chinese" edition is displayed
