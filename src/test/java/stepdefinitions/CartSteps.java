package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

import static org.testng.Assert.assertTrue;

public class CartSteps {
    WebDriver driver = DriverManager.getDriver();

    @Given("user has a product in cart")
    public void user_has_a_product_in_cart() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.linkText("Computers")).click();
        driver.findElement(By.linkText("Desktops")).click();
        driver.findElement(By.cssSelector(".product-box-add-to-cart-button")).click();
    }

    @When("user views the shopping cart")
    public void user_views_the_shopping_cart() {
        driver.findElement(By.linkText("Shopping cart")).click();
    }

    @Then("cart should display the selected product")
    public void cart_should_display_the_selected_product() {
        String cartText = driver.findElement(By.cssSelector(".cart-item-row")).getText();
        assertTrue(cartText.length() > 0, "Cart is empty!");
    }
}
