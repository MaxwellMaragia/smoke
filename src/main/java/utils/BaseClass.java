package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//Base class to allow access to browser from hooks
public class BaseClass {
    //local variable that gets assigned below after properties class is instantiated
    public static WebDriver driver;
    public static Properties Pro;



    public static WebDriver getDriver() throws IOException
    {
        Pro = new Properties();
        FileInputStream fls = new FileInputStream("src\\test\\resources\\Objects\\object.properties");
        Pro.load(fls);

        System.setProperty("webdriver.chrome.driver", "./Browser/chromedriver3.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        return driver;
    }

    public static String randomDate() {

        LocalDate from = LocalDate.of(2000, 1, 1);
        LocalDate to = LocalDate.of(2017, 1, 1);
        long days = from.until(to, ChronoUnit.DAYS);
        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
        LocalDate randomDate = from.plusDays(randomDays);
        return randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String todaysDate() {
        LocalDate today = LocalDate.now();
        return today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
    }

    public static String tomorrowsDate() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        String formattedDate = tomorrow.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        return formattedDate;
    }

    public static void waitForPageToLoad()
    {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String getRandom(int n)
    {

        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);
        String randomString
                = new String(array, Charset.forName("UTF-8"));
        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();
        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < randomString.length(); k++) {
            char ch = randomString.charAt(k);
            if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    && (n > 0)) {
                r.append(ch);
                n--;
            }
        }
        // return the resultant string
        return r.toString();
    }
}