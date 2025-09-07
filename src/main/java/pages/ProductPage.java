package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class ProductPage {
    private WebDriver driver;

    // Locators
    private By searchBox = By.id("small-searchterms");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By productTitle = By.cssSelector(".product-title a");
    private By addToCartButton = By.cssSelector("button.add-to-cart-button");

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void searchProduct(String productName) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public String getFirstProductName() {
        return driver.findElement(productTitle).getText();
    }

    public void addFirstProductToCart() {
        driver.findElement(addToCartButton).click();
    }
}
