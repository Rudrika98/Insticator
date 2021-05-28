package com.insticator.stepDefinition;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.insticator.baseclass.TestBase;
import com.insticator.pages.LogInPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LogIn_Steps extends TestBase {
	
	LogInPage logIn = new LogInPage();
	
	
	@Given("browser is open")
	public void browser_is_open() {
		log.info("Opening Browser");
		selUtil.openBrowser(readProperties("browser"));
		
	
	}

	@When("user navigates to login page")
	public void user_navigates_to_login_page() throws IOException, InterruptedException {
		log.info("Navigating to url ");
		selUtil.openBrowserAndNavigateUrl(readProperties("url"));
		selUtil.takeScreenshot("HomePage");
		log.info("Clicking on My Account");
		logIn.myAccountClick();
		log.info("Clicking on LogIn");
		Thread.sleep(2000);
		logIn.clickLogIn();
		selUtil.takeScreenshot("LogIn");
	}

	@When("user enters username and password")
	public void user_enters_username_and_password() throws InterruptedException {
		log.info("Entering username");
		logIn.enterUserName(readProperties("userName"));
		log.info("Entering password");
		logIn.enterPassword(readProperties("password"));
		log.info("Clicking on Submit button");
		logIn.clickSubmitButton();
	}

	@Then("user is successfully logged in")
	public void user_is_successfully_logged_in() throws InterruptedException {

	
		System.out.println("driver.getTitle() Two " + driver.getTitle());

	}

	@When("user LogOut from application")
	public void user_log_out_from_application() {
		

	}

	@Then("Account Logout message is displayed")
	public void account_logout_message_is_displayed() {

	}

}
