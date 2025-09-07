package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchBox = By.id("small-searchterms");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By productItems = By.cssSelector(".product-item");

    // Category locators
    private By computersCategory = By.linkText("Computers");
    private By desktopsCategory = By.linkText("Desktops");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Search product by keyword
    public void searchProduct(String keyword) {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        search.clear();
        search.sendKeys(keyword);
        driver.findElement(searchButton).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productItems));
    }

    // Navigate to Computers → Desktops
    public void navigateToDesktops() {
        driver.findElement(computersCategory).click();
        wait.until(ExpectedConditions.elementToBeClickable(desktopsCategory)).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productItems));
    }

    // Get count of products displayed
    public int getProductCount() {
        List<WebElement> products = driver.findElements(productItems);
        return products.size();
    }

    // Add first product to cart
    public void addFirstProductToCart() {
        List<WebElement> products = driver.findElements(productItems);
        if (!products.isEmpty()) {
            products.get(0).findElement(By.cssSelector(".product-box-add-to-cart-button")).click();
        }
    }
}
