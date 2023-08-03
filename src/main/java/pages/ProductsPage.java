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

    @FindBy(css = ".tocart")
    private WebElement linkAddToCart;
    @FindBy(css = ".showcart")
    private WebElement linkGoToCart;
    @FindBy(css = ".counter-label")
    private WebElement linkToCartNumberVisible;

    @FindBy(css = "#search.input-text")
    private WebElement fieldSearch;

    @FindBy(css = ".products ol li:nth-child(1) .product-item-info")
    private WebElement linkToParticularItemFromSearch;

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

    public ProductsPage enterSearchText(String searchText) {
        fieldSearch.sendKeys(searchText);
        return this;
    }

    public WebElement getFieldSearch() {
        return fieldSearch;
    }

    public ProductsPage goToParticularItemFromSearch() {
        linkToParticularItemFromSearch.click();
        return this;
    }
}

