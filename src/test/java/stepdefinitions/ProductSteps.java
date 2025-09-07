package stepdefinitions;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import pages.ProductPage;
import utils.DriverManager;
import org.testng.Assert;

public class ProductSteps {

    private WebDriver driver = DriverManager.getDriver();
    private ProductPage productPage = new ProductPage(driver);

    @Given("user is on the homepage")
    public void user_is_on_homepage() {
        driver.get("https://demo.nopcommerce.com");
    }

    @When("user searches for a product {string}")
    public void user_searches_for_a_product(String productName) {
        productPage.searchProduct(productName);
    }

    @Then("search results should display at least one product")
    public void search_results_should_display_at_least_one_product() {
        Assert.assertTrue(productPage.getProductCount() > 0, "Expected at least one product in search results");
    }

    @When("user navigates to Computers category and then Desktops")
    public void user_navigates_to_computers_desktops() {
        productPage.navigateToDesktops();
    }

    @Then("Desktops category should display products")
    public void desktops_category_should_display_products() {
        Assert.assertTrue(productPage.getProductCount() > 0, "Expected at least one product in Desktops category");
    }

    @When("user adds the first product to the cart")
    public void user_adds_the_first_product_to_the_cart() {
        productPage.addFirstProductToCart();
    }

    @Then("the cart should have at least one item")
    public void cart_should_have_at_least_one_item() {
        // This step will be verified in CartSteps, so you can keep it simple
        System.out.println("Product added to cart. Verification in Cart test.");
    }
}
