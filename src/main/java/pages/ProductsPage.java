package pages;

import constants.TimeConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage extends BasePage {
    @FindBy(css = "ul li.item a[href='https://magento.softwaretestingboard.com/gear/bags.html']")
    private WebElement linkClothesType;

    @FindBy(css = "ol li:nth-child(4) .product-item-info")
    private WebElement linkToParticularItem;

    @FindBy(css = ".towishlist")
    private WebElement linkAddToWishList;

    @FindBy(css = "ol li:nth-child(3) .product-item-info")
    private WebElement linkToParticularItem2;
    @FindBy(css = "ol li:nth-child(5) .product-item-info")
    private WebElement linkToParticularItem3;
    @FindBy(css = ".tocart")
    private WebElement linkAddToCart;
    @FindBy(css = ".showcart")
    private WebElement linkGoToCart;
    @FindBy(css = ".counter-label")
    private WebElement linkToCartNumberVisible;

    public ProductsPage(WebDriver driver) {

        super(driver);
    }

    public ProductsPage goToClothesTypeSection() {
        linkClothesType.click();
        return this;
    }

    public ProductsPage goToParticularItem() {
        linkToParticularItem.click();
        return this;
    }

    public WishListPage addToWishList() {
        linkAddToWishList.click();
        return new WishListPage(driver);
    }

    public ProductsPage goToParticularItem2() {
        linkToParticularItem2.click();
        return this;
    }

    public ProductsPage goToParticularItem3() {
        linkToParticularItem3.click();
        return this;
    }

    public ProductsPage addToCart() {
        linkAddToCart.click();
        return this;
    }

    public CartPage goToCart() {

        WebDriverWait waitCart = new WebDriverWait(driver, Duration.ofSeconds(TimeConstants.SECONDS_WAIT));
        waitCart.until(driver -> linkToCartNumberVisible.isDisplayed());

        linkGoToCart.click();

        return new CartPage(driver);
    }
}

