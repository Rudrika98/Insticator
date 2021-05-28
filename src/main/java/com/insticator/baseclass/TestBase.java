package com.insticator.baseclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

import com.insticator.utilities.LogUtilities;
import com.insticator.utilities.SeleniumUtilities;

public class TestBase {

	public static WebDriver driver;
	public static Properties properties;
	public static String sheetName = "TestDataSheet";
	// public static GenerateExtentReport ExtentSetup;
	public static LogUtilities log;
	public static SeleniumUtilities selUtil = new SeleniumUtilities();

	public static String readProperties(String propertyName) {

		try {
			log = new LogUtilities();
			//selUtil = new SeleniumUtilities();
			log.info("Reading Property: " + propertyName);
			properties = new Properties();
			FileInputStream file = new FileInputStream(
					System.getProperty("user.dir") + "//src//test//resources//properties/config.properties");
			properties.load(file);
			String property = properties.getProperty(propertyName);
			log.info("Value of property " + propertyName + " is : " + property);
			file.close();
			return property;
		} catch (FileNotFoundException e) {
			System.out.println("Properties file is not attached in project");
			log.error("Properties file is not attached in project");
			return null;

		} catch (IOException e) {
			log.error("IOException caught");
			return null;
		}

	}

	public static void initializeScript() throws IOException {
		selUtil.openBrowser(readProperties("browser"));
		selUtil.openBrowserAndNavigateUrl(readProperties("url"));

	}

}
