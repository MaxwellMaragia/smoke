package Runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = {"src/test/java/Features/Registration"},
	glue = "Steps",
	tags = "@UAT_TCS-01.32.1",
	plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
	dryRun = false,
	monochrome = true
)

public class Runnerclass {
	 
}  