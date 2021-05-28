@RegisterUser
Feature: Register Feature for users

Scenario Outline: To Create a new user
Given User is on register page
When user enters "<firstName>" "<lastName>" "<email>" "<telephone>" "<password>" "<confirmPassword>"
And clicks on newsletter subscription ad clicks continue button
Then validate user is created
And user logs out from application

Examples:

|firstName|lastName|email|telephone|password|confirmPassword|
|Rudrika|Sharma|fobepoy480@to200.com|1234567890|test@12345|test@12345|