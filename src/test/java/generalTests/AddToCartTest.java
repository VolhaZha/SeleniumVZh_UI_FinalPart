package generalTests;

import baseTests.BaseTest;
import constants.TestDataConstants;
import constants.TimeConstants;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.MainPage;
import pages.ProductsPage;
import util.PropertiesFileReader;
import util.PropertyKey;

import java.io.IOException;
import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddToCartTest extends BaseTest {
    private MainPage mainPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private String emailExist;
    private String password;

    private String executionMode;


    @Test
    public void testAddToCartList () throws IOException, InterruptedException {
        String executionMode = PropertiesFileReader.getProperty(PropertyKey.EXECUTIONMODE);

        launchBrowser();

        String url = PropertiesFileReader.getProperty(PropertyKey.URLMAIN);
        driver.get(url);

        mainPage = new MainPage(driver);

        mainPage.goToSignIn();

        emailExist = PropertiesFileReader.getProperty(PropertyKey.EMAILEXIST);
        password = PropertiesFileReader.getProperty(PropertyKey.PASSWORD);

        mainPage.enterEmail(emailExist);
        mainPage.enterPassword(password);

        mainPage.clickSignIn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeConstants.MILLIS_WAIT_AFTER_CLICK));
        wait.until(ExpectedConditions.titleContains(TestDataConstants.INFO_AFTER_LOGIN));

        String actualTitle = driver.getTitle();
        assertEquals (actualTitle.contains(TestDataConstants.INFO_AFTER_LOGIN), true);

        mainPage.clickSale();

        productsPage = new ProductsPage(driver);

        productsPage.goToClothesTypeSection();
        productsPage.goToParticularItem();
        productsPage.addToCart();
        driver.navigate().back();

        productsPage.goToParticularItem2();
        productsPage.addToCart();
        driver.navigate().back();

        productsPage.goToParticularItem3();
        productsPage.addToCart();

        Thread.sleep(5000);

        productsPage.goToCart();

        cartPage = new CartPage(driver);
        cartPage.countings();

        cartPage.clearUpCart();
        String emptyCart = driver.findElement(By.cssSelector(".subtitle.empty")).getText();
        assertThat (emptyCart,containsString("You have no items in your shopping cart."));
    }
}
