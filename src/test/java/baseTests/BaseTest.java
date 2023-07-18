package baseTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import util.*;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SreenWatcher.class)
public class BaseTest {
    protected static WebDriver driver;

    private String browserName;
    protected String executionMode;

    @BeforeEach
    public void launchBrowser() throws IOException {

        executionMode = PropertiesFileReader.getProperty(PropertyKey.EXECUTIONMODE);
        browserName = PropertiesFileReader.getProperty(PropertyKey.BROWSERNAME);

        if (executionMode.equals("local")) {
            driver = WebDriverSingletonLocal.getDriver(browserName);
        } else if (executionMode.equals("remote")) {
            driver = WebDriverSingletonRemote.openDriverInSauceLabs(browserName);
            WebDriverSingletonRemote.setDriver(driver);
            System.out.println(WebDriverSingletonRemote.getDriver().toString());
        } else if (executionMode.equals("grid")) {
            // Use WebDriverSingletonGrid or any other implementation for grid execution
            // driver = WebDriverSingletonGrid.getDriver(browserName);
            System.out.println("GRID!!!");
        }
        System.out.println(driver);

    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}