package Steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.BaseClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class hooks extends BaseClass {

    public Scenario scenario = null;

    @Before()
    public void before(Scenario scenario) throws IOException {
        this.scenario = scenario;
    }

//    @After(order=1)
//    public void AfterSelenium()
//    {
//        driver.close();
//        System.out.println("Completed execution for the scenario :" + scenario.getName());
//    }

    @After(order=2)
    public void AftersaveScreenshot(Scenario scenario) {

        //Take the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //Copy the file to a location and use try catch block to handle exception
        try {
            FileUtils.copyFile(screenshot, new File("./test-output/Screenshots/"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
