package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    // Locators
    private By emailInput = By.id("Email");
    private By passwordInput = By.id("Password");
    private By loginButton = By.xpath("//button[contains(text(),'Log in')]");
    private By myAccountLink = By.xpath("//a[contains(text(),'My account')]");
    private By loginErrorMsg = By.xpath("//div[@class='message-error validation-summary-errors']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isMyAccountDisplayed() {
        return driver.findElement(myAccountLink).isDisplayed();
    }

    public boolean isLoginErrorDisplayed() {
        return driver.findElement(loginErrorMsg).isDisplayed();
    }
}
