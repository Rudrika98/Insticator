package com.insticator.rough;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.insticator.baseclass.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BuyOrder extends TestBase{
	
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

		WebElement laptopNotebook = driver.findElement(By.linkText("Laptops & Notebooks"));

		System.out.println(laptopNotebook.getText());

		Actions builder = new Actions(driver);
		Action mouseOverHome = builder.moveToElement(laptopNotebook).build();

		mouseOverHome.perform();

		WebElement allLaptops = driver.findElement(By.linkText("Show All Laptops & Notebooks"));

		Action mouseOverLaptop = builder.moveToElement(allLaptops).click().build();

		mouseOverLaptop.perform();

		WebElement firstProduct = driver.findElement(By.linkText("HP LP3065"));

		Action mouseOverFirstProduct = builder.moveToElement(firstProduct).build();

		mouseOverFirstProduct.perform();

		WebElement element = driver.findElement(By.xpath(
				"//a[contains(text(),'LP3065')]/ancestor::div[contains(@class,'thumb')]/div/*/button[contains(@onclick,'cart')]"));

		if (element.isDisplayed()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);

			element.click();

			System.out.println("Rudrika testing this page" + driver.getTitle());
		}

		WebElement cal = driver.findElement(By.id("input-option225"));

		cal.clear();

		cal.sendKeys("2011-05-06");

		WebElement qtty = driver.findElement(By.id("input-quantity"));

		qtty.clear();

		qtty.sendKeys("3");

		WebElement addToCartButton = driver.findElement(By.id("button-cart"));

		if (addToCartButton.isDisplayed()) {
			addToCartButton.click();
		}

		WebElement message = driver.findElement(By.xpath("//div[@class=\"alert alert-success alert-dismissible\"]"));

		String actual_message = message.getText();

		System.out.println(message.getText());

		String[] arrofStr = actual_message.split("!");

		actual_message = arrofStr[0];

		System.out.println("After splitting array : " + actual_message);

		Assert.assertEquals(actual_message, "Success: You have added HP LP3065 to your shopping cart");

		WebElement shoppingCart = driver.findElement(By.linkText("shopping cart"));

		shoppingCart.click();

		WebElement checkout = driver.findElement(By.linkText("Checkout"));

		checkout.click();

		/*
		 * for (String a : arrofStr) System.out.println(a);
		 **/

		WebElement accountName = driver.findElement(By.xpath("//input[@name='account'][@value='guest']"));

		if (accountName.isDisplayed())
			accountName.click();

		WebElement inputEmail = driver.findElement(By.id("input-email"));

		inputEmail.sendKeys("test12340@gmail.com");

		WebElement inputPassword = driver.findElement(By.id("input-password"));

		inputPassword.sendKeys("test@123");

		WebElement loginButton = driver.findElement(By.id("button-login"));

		loginButton.click();

		/**
		 * WebDriverWait wait = new WebDriverWait(driver, 20);
		 * 
		 * WebElement firstName; firstName =
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-payment-firstname")));
		 * firstName.sendKeys("Rudrika");
		 * 
		 * WebElement lastName = driver.findElement(By.id("input-payment-lastname"));
		 * lastName.sendKeys("Sharma");
		 * 
		 * WebElement companyName = driver.findElement(By.id("input-payment-company"));
		 * companyName.sendKeys("Amazon");
		 * 
		 * WebElement address1 = driver.findElement(By.id("input-payment-address-1"));
		 * address1.sendKeys("House 1047 street 2 as");
		 * 
		 * WebElement city = driver.findElement(By.id("input-payment-city"));
		 * city.sendKeys("Fazilka");
		 * 
		 * WebElement postalCode = driver.findElement(By.id("input-payment-postcode"));
		 * postalCode.sendKeys("152123");
		 * 
		 * WebElement countryDropdown =
		 * driver.findElement(By.id("input-payment-country"));
		 * 
		 * Select dropDown = new Select(countryDropdown); List<WebElement> selectList =
		 * dropDown.getOptions(); for (WebElement e : selectList) {
		 * dropDown.selectByVisibleText("India"); }
		 * 
		 * WebElement regionDropdown = driver.findElement(By.id("input-payment-zone"));
		 * Select dropDownOne = new Select(regionDropdown); List<WebElement>
		 * selectListOne = dropDownOne.getOptions(); for (WebElement e : selectListOne)
		 * { dropDownOne.selectByVisibleText("Punjab"); }
		 * 
		 * WebElement continueButton =
		 * driver.findElement(By.id("button-payment-address")); continueButton.click();
		 **/

		WebDriverWait wait = new WebDriverWait(driver, 2);

		WebElement continueShippingButton;

		continueShippingButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-payment-address")));

		continueShippingButton.click();

		WebElement deliveryContinueButton;

		deliveryContinueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-shipping-address")));

		deliveryContinueButton.click();

		WebElement commentsTextBox;

		commentsTextBox = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@class='form-control']")));

		commentsTextBox.sendKeys("NA");

		WebElement shippingContinueButton;

		shippingContinueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-shipping-method")));

		shippingContinueButton.click();

		WebElement tickCheckBox;

		tickCheckBox = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox'][@name='agree']")));

		tickCheckBox.click();
		
		// button-payment-method
		
		WebElement paymentMethodContinueButton;
		
		paymentMethodContinueButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("button-payment-method")));

		paymentMethodContinueButton.click();
		
		WebElement confirmOrderButton;
		
		confirmOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-confirm")));
		
		confirmOrderButton.click();
		
		
		
	}


}
