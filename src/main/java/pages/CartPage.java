package pages;

import constants.TimeConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);

    public CartPage(WebDriver driver) {

        super(driver);
    }

    @FindBy(css = "li.product-item:nth-of-type(1) .product-item-details .actions .secondary")
    private WebElement delete1;
    @FindBy(css = "li.product-item:nth-of-type(2) .product-item-details .actions .secondary")
    private WebElement delete2;
    @FindBy(css = "li.product-item:nth-of-type(3) .product-item-details .actions .secondary")
    private WebElement delete3;
    @FindBy(css = ".action-accept")
    private WebElement accept;


    public CartPage countings () {

        String priceProduct1 = driver.findElement(By.cssSelector("li.product-item:nth-of-type(1) span.minicart-price")).getText();
        priceProduct1 = priceProduct1.replace("$","");
        int priceValue1 = Integer.parseInt(priceProduct1.substring(0,priceProduct1.indexOf(".")));
        logger.info("PriceProduct1 Actual: {}", priceProduct1);

        String priceProduct2 = driver.findElement(By.cssSelector("li.product-item:nth-of-type(2) span.minicart-price")).getText();
        priceProduct2 = priceProduct2.replace("$","");
        int priceValue2 = Integer.parseInt(priceProduct2.substring(0,priceProduct2.indexOf(".")));
        logger.info("PriceProduct2 Actual: {}", priceProduct2);

        String priceProduct3 = driver.findElement(By.cssSelector("li.product-item:nth-of-type(3) span.minicart-price")).getText();
        priceProduct3 = priceProduct3.replace("$","");
        int priceValue3 = Integer.parseInt(priceProduct3.substring(0,priceProduct3.indexOf(".")));
        logger.info("PriceProduct3 Actual: {}", priceProduct3);

        return this;
    }

    public CartPage clearUpCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeConstants.MILLIS_WAIT_AFTER_CLICK));

        delete1.click();
        wait.until(ExpectedConditions.elementToBeClickable(accept)).click();

        delete2.click();
        wait.until(ExpectedConditions.elementToBeClickable(accept)).click();

        delete3.click();
        wait.until(ExpectedConditions.elementToBeClickable(accept)).click();

        return this;
    }
}
