package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;
import java.util.Map;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By genderMale = By.id("gender-male");
    private By genderFemale = By.id("gender-female");
    private By firstNameField = By.id("FirstName");
    private By lastNameField = By.id("LastName");
    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private By confirmPasswordField = By.id("ConfirmPassword");
    private By registerButton = By.id("register-button");
    private By successMessage = By.cssSelector(".result");
    private By errorMessage = By.cssSelector(".validation-summary-errors");

    public RegisterPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Navigate
    public void goToHome() {
        driver.get("https://demo.nopcommerce.com/");
    }

    public void openRegister() {
        driver.findElement(By.className("ico-register")).click();
    }

    // Generic register method (feature file DataTable)
    public void register(Map<String, String> data) {
        selectGender(data.getOrDefault("gender", "male"));
        driver.findElement(firstNameField).sendKeys(data.getOrDefault("firstName", ""));
        driver.findElement(lastNameField).sendKeys(data.getOrDefault("lastName", ""));
        driver.findElement(emailField).sendKeys(data.getOrDefault("email", ""));
        driver.findElement(passwordField).sendKeys(data.getOrDefault("password", ""));
        driver.findElement(confirmPasswordField).sendKeys(data.getOrDefault("password", ""));
        driver.findElement(registerButton).click();
    }

    // Register with mismatched confirm password
    public void registerWithConfirm(Map<String, String> data, String confirmPassword) {
        selectGender(data.getOrDefault("gender", "male"));
        driver.findElement(firstNameField).sendKeys(data.getOrDefault("firstName", ""));
        driver.findElement(lastNameField).sendKeys(data.getOrDefault("lastName", ""));
        driver.findElement(emailField).sendKeys(data.getOrDefault("email", ""));
        driver.findElement(passwordField).sendKeys(data.getOrDefault("password", ""));
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword); // different confirm
        driver.findElement(registerButton).click();
    }

    // Helper: select gender
    private void selectGender(String gender) {
        if ("female".equalsIgnoreCase(gender)) {
            driver.findElement(genderFemale).click();
        } else {
            driver.findElement(genderMale).click();
        }
    }

    // Check result
    public boolean isRegistrationSuccessful() {
        try {
            String text = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
            return text.contains("Your registration completed");
        } catch (Exception e) {
            return false; // means failure
        }
    }
}
