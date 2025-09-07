package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import pages.LoginPage;

public class LoginSteps {
    private WebDriver driver = DriverManager.getDriver();
    private LoginPage loginPage = new LoginPage(driver);

    private final String validEmail = "existingemail@example.com";
    private final String validPassword = "Password123";

    @Given("user is on login page")
    public void user_is_on_login_page() {
        loginPage.goToLoginPage();
    }

    @When("user enters valid email and password")
    public void user_enters_valid_email_and_password() {
        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(validPassword);
    }

    @When("user enters invalid email and valid password")
    public void user_enters_invalid_email_and_valid_password() {
        loginPage.enterEmail("wrong@example.com");
        loginPage.enterPassword(validPassword);
    }

    @When("clicks login button")
    public void clicks_login_button() {
        loginPage.clickLogin();
    }

    @Then("user should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        Assert.assertTrue(driver.getCurrentUrl().contains("nopcommerce"), "Login failed");
    }

    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
        Assert.assertTrue(loginPage.getErrorMessage().contains("Login was unsuccessful"));
    }
}
