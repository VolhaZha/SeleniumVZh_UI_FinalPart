package baseTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SreenWatcher.class)
@ExtendWith(AllureWatcher.class)
public class BaseTest {
    protected static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeEach
    public void launchBrowser() throws IOException {

        String executionMode = PropertiesFileReader.getProperty(PropertyKey.EXECUTIONMODE);
        String browserName = PropertiesFileReader.getProperty(PropertyKey.BROWSERNAME);

        if (executionMode.equals("local")) {
            driver = WebDriverSingletonLocal.getDriver(browserName);
        } else if (executionMode.equals("remote")) {
            driver = WebDriverSingletonRemote.openDriverInSauceLabs(browserName);
            WebDriverSingletonRemote.setDriver(driver);
            logger.info("Remote driver opened: {}", WebDriverSingletonRemote.getDriver().toString());
        }
        logger.info("Driver initialized: {}", driver);

    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Driver closed");
        }
    }
}