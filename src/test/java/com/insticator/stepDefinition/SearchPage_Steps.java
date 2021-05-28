package com.insticator.stepDefinition;

import java.io.IOException;

import com.insticator.baseclass.TestBase;
import com.insticator.pages.SearchPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchPage_Steps extends TestBase{

	SearchPage search = new SearchPage();
	
	@Given("Browser is open and user is on application")
	public void browser_is_open_and_user_is_on_application() throws IOException {
		log.info("Navigating to url ");
		//initializeScript();
		selUtil.openBrowser(readProperties("browser"));
//		selUtil.openBrowserAndNavigateUrl(readProperties("url"));
//		search.moveToLaptops();
//		search.moveToAllLaptops();
	}

	@When("user moves to All Laptops & Notebooks page")
	public void user_moves_to_all_laptops_notebooks_page() throws IOException, InterruptedException {
		selUtil.openBrowserAndNavigateUrl(readProperties("url"));
		System.out.println("In Laptops Section Test Runner");
		Thread.sleep(3000);
		search.moveToLaptops();
		search.moveToAllLaptops();
	}

	@When("enter first product name into search bar")
	public void enter_first_product_name_into_search_bar() {

	}

	@When("clicks search button")
	public void clicks_search_button() {

	}

	@Then("validate same product is displayed on searched page")
	public void validate_same_product_is_displayed_on_searched_page() {

	}

}
