package com.insticator.rough;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.insticator.baseclass.TestBase;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Registration extends TestBase {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
		chromeOptions.addArguments("enable-automation");
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(25, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		System.out.println("Entering URL... ");

		driver.get("http://tutorialsninja.com/demo/index.php");

		System.out.println(driver.getTitle());
		
		WebElement Myaccount;
		
		WebDriverWait wait = new WebDriverWait(driver, 2);
		Myaccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='My Account']")));
		
		Myaccount.click();
		
		WebElement register;
		register = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Register")));
		
		register.click();
		
		WebElement firstNameTextBox;
		firstNameTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname")));
		firstNameTextBox.sendKeys("Rudrika");
	
		WebElement lastNameTextBox;
		lastNameTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-lastname")));
		lastNameTextBox.sendKeys("Sharma");

		WebElement emailTextBox;
		emailTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));
		emailTextBox.sendKeys("fobepoy489@to200.com");

		WebElement telephoneTextBox;
		telephoneTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-telephone")));
		telephoneTextBox.sendKeys("1234567890");
		
		
		WebElement passwordTextBox;
		passwordTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-password")));
		passwordTextBox.sendKeys("test@12345");
	
		WebElement passwordConfirmTextBox;
		passwordConfirmTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-confirm")));
		passwordConfirmTextBox.sendKeys("test@12345");
		
		WebElement newsLetterElement = driver.findElement(By.xpath("//input[@name='newsletter'][@checked='checked']"));
		if(!newsLetterElement.isSelected()) {
			newsLetterElement.click();
		}
		
		WebElement agreeCheckBox = driver.findElement(By.xpath("//input[@type='checkbox'][@name='agree']"));
		if(agreeCheckBox.isDisplayed()) {
			agreeCheckBox.click();
		}
		
		WebElement continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		continueButton.click();
		
		WebElement userMessage = driver.findElement(By.xpath("//div[@id='content']/h1"));
		userMessage.getText();
		
		Assert.assertEquals(userMessage.getText(), "Your Account Has Been Created!");
		
		
		
		//System.out.println("======Rudrika======="+driver.getTitle());
	}
}
