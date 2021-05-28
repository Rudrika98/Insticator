package com.insticator.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(

		features = "./src/test/resources/features/LogInAction.feature",
		//tags= " @RegisterUser ,@LogIn,",
		glue = {"com.insticator.stepDefinition" },
		plugin = {"pretty", "html:src/test/resources/Reports/cucumber-html-report",
		//				"json:src/test/resources/Reports/cucumber-html-report/jsonreport",
		//				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:Reports/"
				},
		monochrome = true

)


public class TestRunner extends AbstractTestNGCucumberTests{

}
