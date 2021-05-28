package com.insticator.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.insticator.baseclass.TestBase;

public class LogInPage extends TestBase {

	public LogInPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='My Account']")
	WebElement Myaccount;

	@FindBy(linkText = "Login")
	WebElement logInLink;

	@FindBy(id = "input-email")
	WebElement loginTextBox;

	@FindBy(id = "input-password")
	WebElement passwordTextBox;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement logInButton;

	public void myAccountClick() {
		try {
			if (Myaccount.isDisplayed())
				Myaccount.click();
		} catch (NullPointerException e) {
			Myaccount = selUtil.waitForVisibiltyOfElementLocated(By.xpath("//span[text()='My Account']"), 2);
			Myaccount.click();
		}

	}

	public void clickLogIn() {
		try {
			logInLink.click();
		} catch (Exception e) {
			logInLink = selUtil.waitForVisibiltyOfElementLocated(By.linkText("Login"), 2);
			logInLink.click();
		}

	}

	public void enterUserName(String userName) {
		try {
			loginTextBox = selUtil.waitForVisibiltyOfElementLocated(By.id("input-email"), 2);
			loginTextBox.click();
			selUtil.sendData(loginTextBox, userName);
			log.info("Entered username : "+userName);
		} catch (Exception e) {
			loginTextBox = selUtil.waitForVisibiltyOfElementLocated(By.id("input-email"), 2);
			loginTextBox.sendKeys(userName);
		}
	}

	public void enterPassword(String password) {
		try {
			//Thread.sleep(2000);
			passwordTextBox.sendKeys(password);
			log.info("Entered username : "+password);
		} catch (Exception e) {	
			passwordTextBox = selUtil.waitForVisibiltyOfElementLocated(By.id("input-password"), 2);
			passwordTextBox.sendKeys(password);
		}
	}

	public void clickSubmitButton() {

		try {
			logInButton.click();
		} catch (Exception e) {
			logInButton = selUtil.waitForVisibiltyOfElementLocated(By.xpath("//input[@type='submit']"), 2);
			logInButton.click();
		}
	}

}
