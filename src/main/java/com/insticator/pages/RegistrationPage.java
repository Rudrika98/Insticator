package com.insticator.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.insticator.baseclass.TestBase;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RegistrationPage extends TestBase {

	public RegistrationPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='My Account']")
	WebElement Myaccount;

	@FindBy(id = "input-firstname")
	WebElement firstNameTextBox;

	@FindBy(id = "input-lastname")
	WebElement lastNameTextBox;

	@FindBy(id = "input-email")
	WebElement emailTextBox;

	@FindBy(id = "input-telephone")
	WebElement telephoneTextBox;

	@FindBy(id = "input-password")
	WebElement passwordTextBox;

	@FindBy(id = "input-confirm")
	WebElement passwordConfirmTextBox;

	@FindBy(linkText = "Register")
	WebElement registerLink;

	public void navigateToRegisterPage() {
		try {
			Myaccount = selUtil.waitForVisibiltyOfElementLocated(By.xpath("//span[text()='My Account']"), 2);
			Myaccount.click();
			registerLink = selUtil.waitForVisibiltyOfElementLocated(By.linkText("Register"), 2);
			registerLink.click();
		} catch (Exception e) {

		}
	}

	public void enterValues(String firstName, String lastName, String email, String telephone, String password,
			String confirmPassword) {
		try {
			System.out.println("###################" + firstNameTextBox.getTagName());
			selUtil.waitForTitleIs("Register Account", 2);
			firstNameTextBox = selUtil.waitForVisibiltyOfElementLocated(By.id("input-email"), 2);
			firstNameTextBox.sendKeys(firstName);
			lastNameTextBox.sendKeys(lastName);
			emailTextBox.sendKeys(email);
			telephoneTextBox.sendKeys(telephone);
			passwordTextBox.sendKeys(password);
			passwordConfirmTextBox.sendKeys(confirmPassword);
			// registerLink.sendKeys("");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
