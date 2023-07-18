package generalTests;

import baseTests.BaseTest;
import constants.TestDataConstants;
import constants.TimeConstants;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AddressBookPage;
import pages.MainPage;
import util.PropertiesFileReader;
import util.PropertyKey;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddAdressTest extends BaseTest {
    private MainPage loginPage;
    private AddressBookPage adressBookPage;

    private String emailExist;
    private String password;

    private String phone;
    private String street;
    private String city;

    private String state;
    private String zip;
    private String country;

    @Test
    public void testAddAddress () throws IOException, InterruptedException {
        String executionMode = PropertiesFileReader.getProperty(PropertyKey.EXECUTIONMODE);

        launchBrowser();

        String url = PropertiesFileReader.getProperty(PropertyKey.URLADRBOOK);
        driver.get(url);

        loginPage = new MainPage(driver);

        emailExist = PropertiesFileReader.getProperty(PropertyKey.EMAILEXIST);
        password = PropertiesFileReader.getProperty(PropertyKey.PASSWORD);

        loginPage.enterEmail(emailExist);
        loginPage.enterPassword(password);

        loginPage.clickSignIn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeConstants.MILLIS_WAIT_AFTER_CLICK));
        wait.until(ExpectedConditions.titleContains(TestDataConstants.INFO_AFTER_LOGIN_ADDRESSBOOK));

        String actualTitle = driver.getTitle();
        assertEquals (actualTitle.contains(TestDataConstants.INFO_AFTER_LOGIN_ADDRESSBOOK), true);

        adressBookPage = new AddressBookPage(driver);

      //  adressBookPage.clickAddAddress();
        Thread.sleep(5000);

        phone = PropertiesFileReader.getProperty(PropertyKey.PHONE);
        street= PropertiesFileReader.getProperty(PropertyKey.STREET);
        city= PropertiesFileReader.getProperty(PropertyKey.CITY);
        state= PropertiesFileReader.getProperty(PropertyKey.STATE);
        zip= PropertiesFileReader.getProperty(PropertyKey.ZIP);
        country= PropertiesFileReader.getProperty(PropertyKey.COUNTRY);

        adressBookPage.enterPhone(phone) ;
        adressBookPage.enterCity(city);
        adressBookPage.enterStreet(street);
        adressBookPage.enterZip(zip);
        adressBookPage.selectState(state);
        adressBookPage.selectCountry(country);

        adressBookPage.clickSave();

        WebElement element = driver.findElement(By.cssSelector(".message-success"));
        String actualText = element.getText();
        assertEquals("You saved the address.", actualText);
    }
}