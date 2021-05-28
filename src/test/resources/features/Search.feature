@SearchProduct
Feature: Validate Search Product functionality
Description: This feature will validate Search Product functionality

Scenario: Add first product to search bar
Given Browser is open and user is on application
When user moves to All Laptops & Notebooks page 
And enter first product name into search bar
And clicks search button 
Then validate same product is displayed on searched page
