package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstName = By.id("FirstName");
    private By lastName = By.id("LastName");
    private By email = By.id("Email");
    private By password = By.id("Password");
    private By confirmPassword = By.id("ConfirmPassword");
    private By registerButton = By.id("register-button");
    private By successMessage = By.cssSelector(".result");

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToRegisterPage(){
        driver.get("https://demo.nopcommerce.com/register");
    }

    public void enterFirstName(String fname){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        e.clear();
        e.sendKeys(fname);
    }

    public void enterLastName(String lname){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
        e.clear();
        e.sendKeys(lname);
    }

    public void enterEmail(String mail){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(email));
        e.clear();
        e.sendKeys(mail);
    }

    public void enterPassword(String pass){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        e.clear();
        e.sendKeys(pass);
    }

    public void enterConfirmPassword(String pass){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPassword));
        e.clear();
        e.sendKeys(pass);
    }

    public void clickRegister(){
        driver.findElement(registerButton).click();
    }

    public String getSuccessMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }

    public void register(String fname, String lname, String mail, String pass){
        goToRegisterPage();
        enterFirstName(fname);
        enterLastName(lname);
        enterEmail(mail);
        enterPassword(pass);
        enterConfirmPassword(pass);
        clickRegister();
    }
}
