package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import pages.CartPage;

public class CartSteps {
    private WebDriver driver = DriverManager.getDriver();
    private CartPage cartPage = new CartPage(driver);

    @Then("product should be added to cart")
    public void product_should_be_added_to_cart() {
        Assert.assertTrue(cartPage.getCartItemCount() > 0, "Cart is empty after adding product");
    }

    @Given("user has at least one product in cart")
    public void user_has_at_least_one_product_in_cart() {
        driver.get("https://demo.nopcommerce.com/cart");
    }
}
