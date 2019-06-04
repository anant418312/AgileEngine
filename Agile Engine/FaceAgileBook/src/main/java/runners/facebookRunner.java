package runners;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	     features = "src/main/java/features",  glue = {"stepDefinitions"},
	     dryRun = false)
public class facebookRunner {
	
}
