package pages;

import constants.TimeConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;


public class CartPage extends BasePage {

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

        String actualNumberInCart = driver.findElement(By.cssSelector(".counter-label")).getText();
        assertThat (actualNumberInCart,containsString("3"));
        System.out.println("NumberOfProducts Actual: "+actualNumberInCart);

        String actualSumInCart = driver.findElement(By.cssSelector(".subtotal .price-wrapper")).getText();

        String priceProduct1 = driver.findElement(By.cssSelector("li.product-item:nth-of-type(1) span.minicart-price")).getText();
        priceProduct1 = priceProduct1.replace("$","");
        int priceValue1 = Integer.parseInt(priceProduct1.substring(0,priceProduct1.indexOf(".")));
        System.out.println("PriceProduct1 Actual: "+priceProduct1);

        String priceProduct2 = driver.findElement(By.cssSelector("li.product-item:nth-of-type(2) span.minicart-price")).getText();
        priceProduct2 = priceProduct2.replace("$","");
        int priceValue2 = Integer.parseInt(priceProduct2.substring(0,priceProduct2.indexOf(".")));
        System.out.println("PriceProduct2 Actual: "+priceProduct2);

        String priceProduct3 = driver.findElement(By.cssSelector("li.product-item:nth-of-type(3) span.minicart-price")).getText();
        priceProduct3 = priceProduct3.replace("$","");
        int priceValue3 = Integer.parseInt(priceProduct3.substring(0,priceProduct3.indexOf(".")));
        System.out.println("PriceProduct3 Actual: "+priceProduct3);

        String expectedSumInCart = String.valueOf(priceValue1 + priceValue2 +priceValue3);
        assertThat (actualSumInCart,containsString(expectedSumInCart));

        return this;
    }

    public CartPage clearUpCart() throws InterruptedException {
        delete1.click();
        accept.click();
        Thread.sleep(TimeConstants.SECONDS_CLEAR_CART);
        delete2.click();
        accept.click();
        Thread.sleep(TimeConstants.SECONDS_CLEAR_CART);
        delete1.click();
        accept.click();
        Thread.sleep(TimeConstants.SECONDS_CLEAR_CART);
        return this;
    }
}
