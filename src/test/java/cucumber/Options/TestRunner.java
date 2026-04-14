package cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features ="src/test/java/Features",glue= "StepDefinitions",plugin = {
		"pretty",
        "html:target/cucumber-report.html",
        "json:target/cucumber.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        
},

monochrome = true

)
public class TestRunner extends AbstractTestNGCucumberTests {

	
	
}
