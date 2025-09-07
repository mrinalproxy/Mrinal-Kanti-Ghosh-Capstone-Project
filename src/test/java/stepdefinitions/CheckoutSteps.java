package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import pages.CartPage;

public class CheckoutSteps {
    private WebDriver driver = DriverManager.getDriver();
    private CartPage cartPage = new CartPage(driver);

    @When("user proceeds to checkout")
    public void user_proceeds_to_checkout() {
        cartPage.clickCheckout();
    }

    @Then("checkout page should open")
    public void checkout_page_should_open() {
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"), "Not on checkout page");
    }
}
