@BuyOrder
Feature: To validate buy order e2e functionality

Background: User is logged in
Given browser is open
When user navigates to login page 
And user enters username and password
Then user is successfully logged in

Scenario: Search a product and add the first product to cart
Given user is on search page
When user move to the first laptop and clicks on add to cart button
And select delivery date
And increase quantity 
And click on Add To cart
Then validate message displayed on top and click Shopping cart
And click Check out button
And complete buy order process until payment
And make sure product is ordered.
