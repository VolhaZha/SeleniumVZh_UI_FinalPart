package generalTests;

import baseTests.BaseTest;
import constants.TestDataConstants;
import constants.TimeConstants;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CreateAccountPage;
import pages.AddressBookPage;
import util.PropertiesFileReader;
import util.PropertyKey;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateAccTest extends BaseTest {
    @Test
    public void testCreateAcc () throws IOException {
        String executionMode = PropertiesFileReader.getProperty(PropertyKey.EXECUTIONMODE);

        launchBrowser();

        String url = PropertiesFileReader.getProperty(PropertyKey.URLCREATE);
        driver.get(url);

        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        AddressBookPage mainPage = new AddressBookPage(driver);

        String firstName = PropertiesFileReader.getProperty(PropertyKey.FIRSTNAME);
        String lastName = PropertiesFileReader.getProperty(PropertyKey.LASTNAME);
        String eMail = PropertiesFileReader.getProperty(PropertyKey.EMAIL);
        String password = PropertiesFileReader.getProperty(PropertyKey.PASSWORD);

        createAccountPage.enterFirstName(firstName);
        createAccountPage.enterLastName(lastName);
        createAccountPage.enterEmail(eMail);
        createAccountPage.enterPassword(password);
        createAccountPage.confirmPassword(password);

        createAccountPage.clickCreateAccount();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeConstants.MILLIS_WAIT_AFTER_CLICK));
        wait.until(ExpectedConditions.titleContains(TestDataConstants.INFO_AFTER_ACC_CREATE));

        String actualTitle = driver.getTitle();
        assertEquals (actualTitle.contains(TestDataConstants.INFO_AFTER_ACC_CREATE), true, "Title does not contain the expected information after login.");
    }
}
