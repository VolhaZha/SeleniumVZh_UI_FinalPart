package generalTests;

import baseTests.BaseTest;
import constants.TestDataConstants;
import constants.TimeConstants;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.MainPage;
import pages.ProductsPage;
import util.PropertiesFileReader;
import util.PropertyKey;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddToCartTest extends BaseTest {

    @Test
    public void testAddToCartList () throws IOException, InterruptedException {
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

        ProductsPage productsPage = new ProductsPage(driver);

        String product1 = PropertiesFileReader.getProperty(PropertyKey.PRODUCT1);
        String product2 = PropertiesFileReader.getProperty(PropertyKey.PRODUCT2);
        String product3 = PropertiesFileReader.getProperty(PropertyKey.PRODUCT3);

        productsPage.goToClothesTypeSection();
        productsPage.enterSearchText(product1).getFieldSearch().sendKeys(Keys.ENTER);
        productsPage.goToParticularItemFromSearch();
        productsPage.addToCart();

        productsPage.enterSearchText(product2).getFieldSearch().sendKeys(Keys.ENTER);
        productsPage.goToParticularItemFromSearch();
        productsPage.addToCart();

        productsPage.enterSearchText(product3).getFieldSearch().sendKeys(Keys.ENTER);
        productsPage.goToParticularItemFromSearch();
        productsPage.addToCart();

        Thread.sleep(5000);

        productsPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.countings();

        String actualNumberInCart = driver.findElement(By.cssSelector(".counter-label")).getText();
        assertEquals (true, actualNumberInCart.contains ("3"), "Actual number in cart is not as expected.");
        System.out.println("NumberOfProducts Actual: "+actualNumberInCart);

        String actualSumInCart = driver.findElement(By.cssSelector(".subtotal .price-wrapper")).getText();
        assertEquals (true, actualSumInCart.contains("93"), "Actual sum in cart is not as expected.");

        cartPage.clearUpCart();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".subtitle.empty")));
        String emptyCart = driver.findElement(By.cssSelector(".subtitle.empty")).getText();
        assertEquals (true, emptyCart.contains("You have no items in your shopping cart."), "Cart is not empty after clearing it.");

    }
}
