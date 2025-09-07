package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverManager;

import static org.testng.Assert.assertTrue;

public class ProductSteps {
    WebDriver driver = DriverManager.getDriver();

    @Given("user is on homepage")
    public void user_is_on_homepage() {
        driver.get("https://demo.nopcommerce.com/");
    }

    @When("user searches for {string}")
    public void user_searches_for(String product) {
        WebElement searchBox = driver.findElement(By.id("small-searchterms"));
        searchBox.clear();
        searchBox.sendKeys(product);
        driver.findElement(By.cssSelector("button.search-box-button")).click();
    }

    @Then("search results should display {string}")
    public void search_results_should_display(String product) {
        String resultsText = driver.findElement(By.cssSelector(".product-item")).getText();
        assertTrue(resultsText.toLowerCase().contains(product.toLowerCase()),
                "Search results do not contain: " + product);
    }

    @Given("user is on products page")
    public void user_is_on_products_page() {
        driver.get("https://demo.nopcommerce.com/electronics");
    }

    @When("user filters by {string}")
    public void user_filters_by(String category) {
        // Example: click on category link
        driver.findElement(By.linkText(category)).click();
    }

    @Then("only electronics products are shown")
    public void only_electronics_products_are_shown() {
        String pageTitle = driver.findElement(By.cssSelector(".page-title h1")).getText();
        assertTrue(pageTitle.contains("Electronics"), "Not filtered correctly!");
    }
}
