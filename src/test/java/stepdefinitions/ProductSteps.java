package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import pages.ProductPage;

public class ProductSteps {
    private WebDriver driver = DriverManager.getDriver();
    private ProductPage productPage = new ProductPage(driver);

    @Given("user is on homepage")
    public void user_is_on_homepage() {
        driver.get("https://demo.nopcommerce.com");
    }

    @When("user searches for {string}")
    public void user_searches_for(String product) {
        productPage.searchProduct(product);
    }

    @Then("search results should be displayed")
    public void search_results_should_be_displayed() {
        Assert.assertTrue(productPage.getProductCount() > 0, "Search returned no results");
    }

    @Given("user navigates to Desktops category")
    public void user_navigates_to_desktops_category() {
        productPage.navigateToDesktops();
    }

    @When("user adds first product to cart")
    public void user_adds_first_product_to_cart() {
        productPage.addFirstProductToCart();
    }
}
