package com.insticator.stepDefinition;

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

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BuyOrder_Steps extends TestBase {

	Actions builder;

	@Given("user navigates to loginPage")
	public void user_navigates_to_login_page() {

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
	}

	@When("user enters {string} {string} on login page")
	public void user_enters_on_login_page(String string, String string2) {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 5);

		WebElement logInLink;

		logInLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login")));

		logInLink.click();

		WebElement inputText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));

		inputText.sendKeys("test12340@gmail.com");

		WebElement inputPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-password")));

		inputPassword.sendKeys("test@123");

		WebElement submitButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']")));

		submitButton.click();

	}

	@Then("user is logged in")
	public void user_is_logged_in() {

		System.out.println(driver.getTitle());

	}

	@Given("user is on search page")
	public void user_is_on_search_page() {

		WebElement laptopNotebook = driver.findElement(By.linkText("Laptops & Notebooks"));

		System.out.println(laptopNotebook.getText());

		builder = new Actions(driver);
		Action mouseOverHome = builder.moveToElement(laptopNotebook).build();

		mouseOverHome.perform();

		WebElement allLaptops = driver.findElement(By.linkText("Show All Laptops & Notebooks"));

		Action mouseOverLaptop = builder.moveToElement(allLaptops).click().build();

		mouseOverLaptop.perform();

	}

	@When("user move to the first laptop and clicks on add to cart button")
	public void user_move_to_the_first_laptop_and_clicks_on_add_to_cart_button() {
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
	}

	@When("select delivery date")
	public void select_delivery_date() {
		WebElement cal = driver.findElement(By.id("input-option225"));

		cal.clear();

		cal.sendKeys("2011-05-06");

	}

	@When("increase quantity")
	public void increase_quantity() {
		WebElement qtty = driver.findElement(By.id("input-quantity"));

		qtty.clear();

		qtty.sendKeys("3");

	}

	@When("click on Add To cart")
	public void click_on_add_to_cart() {
		WebElement addToCartButton = driver.findElement(By.id("button-cart"));

		if (addToCartButton.isDisplayed()) {
			addToCartButton.click();
		}
	}

	@Then("validate message displayed on top and click Shopping cart")
	public void validate_message_displayed_on_top_and_click_shopping_cart() {

		WebElement message = driver.findElement(By.xpath("//div[@class=\"alert alert-success alert-dismissible\"]"));

		String actual_message = message.getText();

		System.out.println(message.getText());

		String[] arrofStr = actual_message.split("!");

		actual_message = arrofStr[0];

		System.out.println("After splitting array : " + actual_message);

		Assert.assertEquals(actual_message, "Success: You have added HP LP3065 to your shopping cart");

		WebElement shoppingCart = driver.findElement(By.linkText("shopping cart"));

		shoppingCart.click();

	}

	@Then("click Check out button")
	public void click_check_out_button() {

		WebElement checkout = driver.findElement(By.linkText("Checkout"));

		checkout.click();

	}

	@Then("complete buy order process until payment")
	public void complete_buy_order_process_until_payment() {

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

	@Then("make sure product is ordered.")
	public void make_sure_product_is_ordered() {
		
		WebElement text = driver.findElement(By.xpath("//h1[text()='Your order has been placed!']"));
		
		System.out.println(text.getText());
		
		Assert.assertEquals(text.getText(), "Your order has been placed!");

	}

}
