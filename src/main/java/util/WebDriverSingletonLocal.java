package util;

import constants.TimeConstants;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class WebDriverSingletonLocal {
    private static WebDriver driver;
    private static ChromeOptions optionsChr;

    private static FirefoxOptions optionsFfx;

    private WebDriverSingletonLocal() {
    }

    public static WebDriver getDriver(String browserName) {
        System.out.println("LOCAL!!!");
        if (driver == null) {
            initialize(browserName);
        }
        return driver;
    }

    public static WebDriver initialize(String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {
            optionsChr = new ChromeOptions();
            optionsChr.addArguments("--remote-allow-origins=*");
            optionsChr.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver = new ChromeDriver(optionsChr);

        } else if (browserName.equalsIgnoreCase("firefox")) {
            optionsFfx = new FirefoxOptions();
            optionsFfx.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver = new FirefoxDriver(optionsFfx);
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeConstants.SECONDS_IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TimeConstants.SECONDS_PAGE_LOAD_TIMEOUT));
        driver.manage().window().maximize();

        return driver;

    }
}
