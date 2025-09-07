package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

import static org.testng.Assert.assertTrue;

public class CheckoutSteps {
    WebDriver driver = DriverManager.getDriver();

    @Given("user is on checkout page")
    public void user_is_on_checkout_page() {
        driver.get("https://demo.nopcommerce.com/cart");
        driver.findElement(By.id("termsofservice")).click();
        driver.findElement(By.id("checkout")).click();
    }

    @When("user enters valid billing details")
    public void user_enters_valid_billing_details() {
        // Example fill fields
        driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("John");
        driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("Doe");
        driver.findElement(By.id("BillingNewAddress_Email")).sendKeys("john@example.com");
        driver.findElement(By.cssSelector("button.new-address-next-step-button")).click();
    }

    @Then("checkout process should move to next step")
    public void checkout_process_should_move_to_next_step() {
        boolean displayed = driver.findElement(By.cssSelector(".checkout-page")).isDisplayed();
        assertTrue(displayed, "Checkout did not proceed to next step!");
    }
}
