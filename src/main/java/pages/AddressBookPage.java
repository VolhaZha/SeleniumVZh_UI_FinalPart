package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddressBookPage extends BasePage {
    @FindBy(css = "#telephone")
    private WebElement fieldPhone;
    @FindBy(css = "#street_1")
    private WebElement fieldStreet;
    @FindBy (css = "#city")
    private WebElement fieldCity;
    @FindBy (css = "#region_id")
    private WebElement dropdownState;
    @FindBy (css = "#zip")
    private WebElement fieldZip;
    @FindBy (css = "#country")
    private WebElement dropdownCountry;
    @FindBy (css = ".save")
    private WebElement buttonSave;
    @FindBy (css = ".add")
    private WebElement buttonClickAdd;

    public AddressBookPage (WebDriver driver) {
        super(driver);
    }

    public AddressBookPage clickAddAddress() {
        buttonClickAdd.sendKeys(Keys.ENTER);
        return this;
    }

    public AddressBookPage enterPhone(String phone) {
        fieldPhone.sendKeys(phone);
        return this;
    }
    public AddressBookPage enterStreet(String street) {
        fieldStreet.sendKeys(street);
        return this;
    }
    public AddressBookPage enterCity(String city) {
        fieldCity.sendKeys(city);
        return this;
    }
    public AddressBookPage enterZip(String zip) {
        fieldZip.sendKeys(zip);
        return this;
    }

    public AddressBookPage selectState(String option) {
        Select dropdownStateSelect = new Select(dropdownState);
        dropdownStateSelect.selectByValue(option);
        return this;
    }

    public AddressBookPage selectCountry(String option) {
        Select dropdownCountrySelect = new Select(dropdownCountry);
        dropdownCountrySelect.selectByValue(option);
        return this;
    }

    public AddressBookPage clickSave() {
        buttonSave.click();
        return this;
    }
}
