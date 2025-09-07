package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private By loginButton = By.xpath("//button[text()='Log in']");
    private By errorMessage = By.cssSelector(".message-error");
    private By logoutLink = By.cssSelector("a.ico-logout"); // appears after successful login

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToLoginPage(){
        driver.get("https://demo.nopcommerce.com/login");
    }

    public void enterEmail(String email){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        e.clear();
        e.sendKeys(email);
    }

    public void enterPassword(String password){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        e.clear();
        e.sendKeys(password);
    }

    public void clickLogin(){
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        e.click();
    }

    public String getErrorMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    /**
     * Unified login method
     * @param email
     * @param password
     * @return true if login is successful, false otherwise
     */
    public boolean login(String email, String password){
        goToLoginPage();
        enterEmail(email);
        enterPassword(password);
        clickLogin();
        try {
            // wait for logout link to confirm login success
            wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
