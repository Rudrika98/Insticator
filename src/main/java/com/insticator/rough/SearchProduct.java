package com.insticator.rough;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.insticator.baseclass.TestBase;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchProduct extends TestBase {

	public static void main(String[] args) {
		
		// 		
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
		
		WebElement firstElement = driver.findElement(By.xpath("//a[contains(text(),'LP3065')]"));
		
		System.out.println(firstElement.getText());
		
		WebElement secondElement = driver.findElement(By.xpath("//div[@id='search']/input"));
		
		secondElement.sendKeys(firstElement.getText());
		
		//secondElement.sendKeys(Keys.ENTER);
		
		WebElement thirdElement = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']"));
		
		thirdElement.click();
		
		System.out.println("Rudrika"+driver.getTitle());
		
		WebElement afterSearchElement = driver.findElement(By.xpath("//a[contains(text(),'LP3065')]"));
		
		String actual = afterSearchElement.getText();
		
		Assert.assertEquals(actual, "HP LP3065");
		
		System.out.println("End of Script");
		
	}
}
