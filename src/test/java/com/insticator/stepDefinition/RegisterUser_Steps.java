package com.insticator.stepDefinition;

import java.io.IOException;

import com.insticator.baseclass.TestBase;
import com.insticator.pages.RegistrationPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterUser_Steps extends TestBase {
	RegistrationPage register = new RegistrationPage();

	@Given("User is on register page")
	public void user_is_on_register_page() throws IOException {
		selUtil.openBrowserAndNavigateUrl(readProperties("url"));
		register.navigateToRegisterPage();

	}

	@When("user enters {string} {string} {string} {string} {string} {string}")
	public void user_enters(String string, String string2, String string3, String string4, String string5,
			String string6) {
		register.enterValues(string, string2, string3, string4, string5, string6);
	}

	@When("clicks on newsletter subscription ad clicks continue button")
	public void clicks_on_newsletter_subscription_ad_clicks_continue_button() {

	}

	@Then("validate user is created")
	public void validate_user_is_created() {

	}

	@Then("user logs out from application")
	public void user_logs_out_from_application() {

	}
}
