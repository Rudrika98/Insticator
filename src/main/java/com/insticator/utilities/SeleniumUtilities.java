package com.insticator.utilities;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.insticator.baseclass.TestBase;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumUtilities extends TestBase {

	public static void openBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
				chromeOptions.addArguments("enable-automation");
				chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
				driver = new ChromeDriver(chromeOptions);
				log.info("Opening Browser :" + browserName);
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				// driver.manage().window().fullscreen();
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(25, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		} catch (WebDriverException e) {
			System.out.println("[Browser.openBrowser] WebDriverException in first time");
		}

	}

	public static void openBrowserAndNavigateUrl(String url) throws IOException {

		if (driver == null) {
			//System.out.println("In Browser Method:" +driver.getCurrentUrl());
			String browserName = readProperties("browser");
			System.out.println("Launching '" + browserName + "' browser");
			openBrowser(browserName);
			System.out.println("After Browser Method:");
			LogUtilities.info("Launching '" + browserName + "' browser");
		}

		LogUtilities.info("Navigating to url: " + url);
		startPage(url);
		System.out.println("###########################"+driver.getTitle());
		// driver.manage().window().maximize();
	}

	public String getTitle() {
		LogUtilities.info("Getting Title of the  page : ");
		return driver.getTitle();
	}

	/***
	 * Method to only navigate to url
	 * 
	 * @param url
	 */
	public static void startPage(String url) {
		driver.get(url);
	}

	public static String getCurrentUrl() {
		LogUtilities.info("Fetching current url: ");
		return driver.getCurrentUrl();
	}

	/**
	 * Method to send specific data to browser
	 * 
	 * @param element
	 * @param text
	 */

	public void sendData(WebElement element, String text) {
		// System.out.println("element.getTagName()"+element.getTagName());
		// element.click();
		if (element.isDisplayed()) {
			element.sendKeys(text);
		}

	}

	public void selectCheckBox(WebElement element) {

		boolean isSelected = element.isSelected();

		if (isSelected == false) {
			element.click();
		}
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public static void selectRadioButton(WebElement element) {
		if (element.isDisplayed()) {
			if (element.isSelected()) {
				System.out.println("Radio button already selected");
			} else {
				element.click();
			}
		} else {
			System.out.println("WebElement not present");
			// LogUtilities.logFail();
		}
	}

	public void switchFrame(WebElement ele, String nameOrIdOrIndexOrElement, String argumentValue) {
		if (driver != null) {
			switch (nameOrIdOrIndexOrElement) {
			case "Index":
				int indexValue = Integer.parseInt(argumentValue);
				driver.switchTo().frame(indexValue);
				break;
			case "Name":
				driver.switchTo().frame(argumentValue);
				break;
			case "Id":
				driver.switchTo().frame(argumentValue);
				break;
			case "WebElement":
				driver.switchTo().frame(ele);
				break;
			}
		}
	}

	public boolean switchWindow() {
		if (driver != null) {
			String mainWindow = driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			Iterator<String> itr = windows.iterator();
			while (itr.hasNext()) {
				String childWindow = itr.next();
				if (!mainWindow.equalsIgnoreCase(childWindow)) {
					driver.switchTo().window(childWindow);
					return true;
				}
			}
		} else {
			// LogUtilities.logFail();
			return false;
		}
		return false;
	}

	public void switchTab(WebElement element) {
		element.sendKeys(Keys.chord(Keys.ALT, Keys.TAB));
	}

	public void pressKeyEnter(WebElement element) {
		element.sendKeys(Keys.ENTER);
	}

	public void navigate_forward() {
		driver.navigate().forward();
	}

	public void navigate_refresh() {
		driver.navigate().refresh();
	}

	public void confirmAlert() {
		Alert al = driver.switchTo().alert();
		al.accept();
	}

	public void dismissAlert() {
		Alert al = driver.switchTo().alert();
		al.dismiss();
	}

	public void dragAndDrop(WebElement srcElement, WebElement destElement) {
		Actions builder = new Actions(driver);
		builder.dragAndDrop(srcElement, destElement).build().perform();
	}

	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);

	}

	public static void clickByJS(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void clickByJs(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", element);
	}

	public void hoverWebElement(WebElement element) {
		System.out.println("In Selenium Class:" +element.getText());
		Actions builder = new Actions(driver);
		builder.moveToElement(element).build().perform();

	}

	public void clickWebElement(WebElement element) {
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click().build().perform();

	}

	public void doubleClickWebElement(WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	/**
	 * Function to locate an element using XPath
	 * 
	 * @param path
	 * @return WebElement
	 */
	public WebElement findLocatorXPath(String path) {
		return driver.findElement(By.xpath(path));
	}

	public WebElement findLocatorId(String locator_Id) {
		return driver.findElement(By.id(locator_Id));
	}

	/****
	 * Function to locate an element using text
	 * 
	 * @param text
	 * 
	 */

	public WebElement findLocatorText(String text) {
		return driver.findElement(By.linkText(text));
	}

	public List<WebElement> findLocatorClass(String className) {
		return driver.findElements(By.className(className));
	}

	/***
	 * Function to read data from console
	 * 
	 * @return String
	 * @throws IOException
	 */
	public String readText() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter String");
		String s = br.readLine();
		return s;
	}

	public WebElement getWhenVisible(By locator, int timeout) {

		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;

	}

	public static List<WebElement> getAllLabels() {
		return driver.findElements(By.tagName("label"));
	}

	public static boolean driverWait(WebElement element, String condition) {
		boolean b = true;
		try {
			WebDriverWait wait;
			switch (condition) {
			case "visibilityOf":
				wait = new WebDriverWait(driver, 2);
				element = wait.until(ExpectedConditions.visibilityOf(element));
				b = true;
				return b;

			case "elementToBeClickable":
				wait = new WebDriverWait(driver, 2);
				element = wait.until(ExpectedConditions.elementToBeClickable(element));
				b = true;
				return b;

			}

		} catch (Exception e) {
			b = false;
			return b;

		}
		return b;
	}

	/***
	 * Method to find all anchor tags in HTML page
	 * 
	 * @return
	 */
	public static Map<Integer, String> findAllLinks()

	{
		List<WebElement> links = driver.findElements(By.tagName("a"));
		Map<Integer, String> linksMap = new HashMap<Integer, String>();
		int size = links.size();
		for (int i = 0; i < size; i++) {
			linksMap.put(i, links.get(i).getText());
		}
		return linksMap;
	}

	public boolean waitForElementToBeSelected(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeSelected(locator));
	}

	public void waitForFrameToBeAvailable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public WebElement waitForVisibiltyOfElementLocated(By locator, int timeOut) {
		WebElement ele;
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		ele = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return ele;
	}

	public void waitForTitleIs(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleIs(title));
	}

	public void waitForFrameToBeAvailable(String nameOrId, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
	}

	public void waitForFrameToBeAvailable(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	/****
	 * Function to take screen shot and save it at desired location
	 * 
	 * @param pathToStore
	 */
	public void takeSnapshot(String screenshotName) {
		// File src = ((TakesScreenshot)
		// driver).getScreenshotAs(OutputType.FILE);
		/*
		 * try { // String screenShot = TestBase.screenshotsPath + screenshotName +
		 * ".png"; //FileUtils.copyFile(src, new File(screenShot)); } catch (IOException
		 * e) { //LogUtilities.logFail(); }
		 **/
	}

	/**
	 * Method to close browser
	 */
	public void closeBrowser() {
		driver.quit();
	}

	public void takeScreenshot(String screenShotName) throws IOException {
		
		TakesScreenshot srcShot = ((TakesScreenshot)driver);
		File SrcShot = srcShot.getScreenshotAs(OutputType.FILE);
		String screenshotPath = "./src/test/resources/screenshots/"+screenShotName +".png";
		File destShot = new File(screenshotPath);
		FileUtils.copyFile(SrcShot, destShot);
	}
	/**
	 * public BufferedImage captureLogo(WebElement logoImageElement) {
	 * 
	 * try { Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver,
	 * logoImageElement); String path = TestBase.readProperties("screenshotsPath") +
	 * "_Logo.jpg"; ImageIO.write(logoImageScreenshot.getImage(), "png", new
	 * File(path)); File f = new File(path); if (f.exists()) {
	 * System.out.println("Image File Captured"); // Log.info("Logo Image Captured")
	 * return logoImageScreenshot.getImage(); } else { System.out.println("Logo
	 * Image doesn't exist"); // Log.info("Logo Image doesn't exist"); return null;
	 * } } catch (IOException e) { return null; }
	 * 
	 * }
	 * 
	 * public void compareImage(BufferedImage actualImage, BufferedImage
	 * expectedImage) { ImageDiffer imgDiff = new ImageDiffer(); ImageDiff diff =
	 * imgDiff.makeDiff(expectedImage, actualImage);
	 * 
	 * if (diff.hasDiff() == true) { LogUtilities.info("Images are same"); } else {
	 * LogUtilities.error("Images are not same"); }
	 * 
	 * }
	 * 
	 * public void doSelectByVisibleText(String text) { // Select select = new
	 * Select() }
	 * 
	 * public void selectDropdown(WebElement element, String TextOrValueOrIndex,
	 * String argumentValue) { Select dropDown = new Select(element);
	 * List<WebElement> selectList = dropDown.getOptions(); for (WebElement e :
	 * selectList) { if (TextOrValueOrIndex.contains("Text")) {
	 * dropDown.selectByVisibleText(argumentValue); break; } else if
	 * (TextOrValueOrIndex.contains("Value")) {
	 * dropDown.selectByValue(argumentValue); } } /**
	 * 
	 * for (int i = 0; i < selectList.size(); i++) {
	 * System.out.println(selectList.get(i).toString()); if
	 * (selectList.get(i).toString().contains(argumentValue)) { if
	 * (TextOrValueOrIndex.contains("Text")) {
	 * dropDown.selectByVisibleText(argumentValue); } else if
	 * (TextOrValueOrIndex.contains("Value")) {
	 * dropDown.selectByValue(argumentValue); } else { int index =
	 * Integer.parseInt(argumentValue); dropDown.selectByIndex(index); } } else { //
	 * LogUtilities.logFail(); } }
	 ***/

}
