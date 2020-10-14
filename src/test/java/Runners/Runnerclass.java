package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/Features"},
        glue = "Steps",
		tags = "@trial8",
		dryRun = false,    //checks whether each feature has a mapped step definition
		monochrome = true,// neat output after tc run
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}

)

public class Runnerclass {

}  