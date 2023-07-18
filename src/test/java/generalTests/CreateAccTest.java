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
    private CreateAccountPage createAccountPage;
    private AddressBookPage mainPage;

    private String firstName;
    private String lastName;
    private String eMail;
    private String password;

    private String executionMode;

    @Test
    public void testCreateAcc () throws IOException {
        String executionMode = PropertiesFileReader.getProperty(PropertyKey.EXECUTIONMODE);

        launchBrowser();

        String url = PropertiesFileReader.getProperty(PropertyKey.URLCREATE);
        driver.get(url);

        createAccountPage = new CreateAccountPage(driver);
        mainPage = new AddressBookPage(driver);

        firstName = PropertiesFileReader.getProperty(PropertyKey.FIRSTNAME);
        lastName = PropertiesFileReader.getProperty(PropertyKey.LASTNAME);
        eMail = PropertiesFileReader.getProperty(PropertyKey.EMAIL);
        password = PropertiesFileReader.getProperty(PropertyKey.PASSWORD);

        createAccountPage.enterFirstName(firstName);
        createAccountPage.enterLastName(lastName);
        createAccountPage.enterEmail(eMail);
        createAccountPage.enterPassword(password);
        createAccountPage.confirmPassword(password);

        createAccountPage.clickCreateAccount();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeConstants.MILLIS_WAIT_AFTER_CLICK));
        wait.until(ExpectedConditions.titleContains(TestDataConstants.INFO_AFTER_ACC_CREATE));

        String actualTitle = driver.getTitle();
        assertEquals (actualTitle.contains(TestDataConstants.INFO_AFTER_ACC_CREATE), true);
    }
}
