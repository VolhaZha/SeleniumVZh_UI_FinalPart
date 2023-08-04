package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    @FindBy(css = "a[href='https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/']")
    private WebElement linkSignIn;
    @FindBy(css = "#email")
    private WebElement fieldEmail;
    @FindBy (name = "login[password]")
    private WebElement fieldPassword;
    @FindBy (css = "#send2")
    private WebElement buttonSignIn;

    @FindBy(css = "a[href='https://magento.softwaretestingboard.com/sale.html']")
    private WebElement linkSale;

    public MainPage(WebDriver driver) {

        super(driver);
    }

    public MainPage goToSignIn() {
        linkSignIn.click();
        return this;
    }

    public MainPage enterEmail(String email) {
        fieldEmail.sendKeys(email);
        return this;
    }

    public MainPage enterPassword(String password) {
        fieldPassword.sendKeys(password);
        return this;
    }

    public MainPage clickSignIn() {
        buttonSignIn.click();
        return this;
    }

    public ProductsPage clickSale() {
        linkSale.click();
        return new ProductsPage(driver);
    }


}
