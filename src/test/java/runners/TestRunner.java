package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/features",   // make sure folder 'features' contains your .feature files
        glue = {"stepdefinitions"},                 // package containing step definitions
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        monochrome = true,
        publish = false   // set true only if you want Cucumber online reports
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
