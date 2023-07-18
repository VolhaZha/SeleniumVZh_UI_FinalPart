package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateAccountPage extends BasePage {
    @FindBy(css = "#firstname")
    private WebElement fieldFirstName;

    @FindBy(css = "#lastname")
    private WebElement fieldLastName;

    @FindBy(css = "#email_address")
    private WebElement fieldEmail;

    @FindBy(css = "#password.input-text")
    private WebElement fieldPassword;

    @FindBy(css = "#password-confirmation")
    private WebElement fieldConfirmPassword;

    @FindBy(className = "submit")
    private WebElement buttonCreateAccount;

    public CreateAccountPage (WebDriver driver) {
        super(driver);
    }

    public CreateAccountPage enterFirstName(String firstname) {
        fieldFirstName.sendKeys(firstname);
        return this;
    }

    public CreateAccountPage enterLastName(String lastname) {
        fieldLastName.sendKeys(lastname);
        return this;
    }

    public CreateAccountPage enterEmail(String email) {
        fieldEmail.sendKeys(email);
        return this;
    }

    public CreateAccountPage enterPassword(String password) {
        fieldPassword.sendKeys(password);
        return this;
    }

    public CreateAccountPage confirmPassword (String password) {
        fieldConfirmPassword.sendKeys(password);
        return this;
    }

    public AddressBookPage clickCreateAccount() {
        buttonCreateAccount.click();
        return new AddressBookPage(driver);
    }
}

