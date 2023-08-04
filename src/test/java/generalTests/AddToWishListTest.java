package generalTests;

import baseTests.BaseTest;
import constants.TestDataConstants;
import constants.TimeConstants;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.ProductsPage;
import util.PropertiesFileReader;
import util.PropertyKey;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddToWishListTest extends BaseTest {

    @Test
    public void testAddToWishList () throws IOException, InterruptedException {
        String executionMode = PropertiesFileReader.getProperty(PropertyKey.EXECUTIONMODE);

        launchBrowser();

        String url = PropertiesFileReader.getProperty(PropertyKey.URLMAIN);
        driver.get(url);

        MainPage mainPage = new MainPage(driver);

        mainPage.goToSignIn();

        String emailExist = PropertiesFileReader.getProperty(PropertyKey.EMAILEXIST);
        String password = PropertiesFileReader.getProperty(PropertyKey.PASSWORD);

        mainPage.enterEmail(emailExist);
        mainPage.enterPassword(password);

        mainPage.clickSignIn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeConstants.MILLIS_WAIT_AFTER_CLICK));
        wait.until(ExpectedConditions.titleContains(TestDataConstants.INFO_AFTER_LOGIN));

        String actualTitle = driver.getTitle();
        assertEquals (actualTitle.contains(TestDataConstants.INFO_AFTER_LOGIN), true, "Title does not contain the expected information after login.");

        mainPage.clickSale();

        ProductsPage salePage = new ProductsPage(driver);

        salePage.goToClothesTypeSection();
        salePage.goToParticularItem();
        salePage.addToWishList();

        String actualTitleWishlist = driver.getTitle();
        assertEquals (actualTitleWishlist.contains(TestDataConstants.TITLE_AFTER_WISHLIST_ADDING), true, "Title does not contain the expected information after login.");

        WebElement element = driver.findElement(By.cssSelector(".message-success"));
        String actualText = element.getText();
        assertEquals(true, actualText.contains("has been added to your Wish List"), "Item has not been successfully added to Wish List.");
    }
}