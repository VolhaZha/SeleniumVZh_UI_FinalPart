package generalTests;

import baseTests.BaseTest;
import constants.TestDataConstants;
import constants.TimeConstants;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import util.PropertiesFileReader;
import util.PropertyKey;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogInTest extends BaseTest {
    @Test
    public void testLogIn () throws IOException {
        String executionMode = PropertiesFileReader.getProperty(PropertyKey.EXECUTIONMODE);

        launchBrowser();

        String url = PropertiesFileReader.getProperty(PropertyKey.URLMAIN);
        driver.get(url);

        MainPage loginPage = new MainPage(driver);

        loginPage.goToSignIn();

        String emailExist = PropertiesFileReader.getProperty(PropertyKey.EMAILEXIST);
        String password = PropertiesFileReader.getProperty(PropertyKey.PASSWORD);

        loginPage.enterEmail(emailExist);
        loginPage.enterPassword(password);

        loginPage.clickSignIn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeConstants.MILLIS_WAIT_AFTER_CLICK));
        wait.until(ExpectedConditions.titleContains(TestDataConstants.INFO_AFTER_LOGIN));

        String actualTitle = driver.getTitle();
        assertEquals (actualTitle.contains(TestDataConstants.INFO_AFTER_LOGIN), true, "Title does not contain the expected information after login.");
    }

}


