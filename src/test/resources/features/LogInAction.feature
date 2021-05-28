@LogIn
Feature: Login Action
Description: This  feature will test the LogIn and LogOut functionality

Scenario: Successful Login with valid credentials
Given browser is open
When user navigates to login page 
And user enters username and password
Then user is successfully logged in

Scenario: Successful Log out
When user LogOut from application 
Then Account Logout message is displayed
