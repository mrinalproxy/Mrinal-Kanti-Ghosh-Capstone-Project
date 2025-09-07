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
    private By productList = By.cssSelector(".product-item");
    private By addToCartButtons = By.cssSelector(".product-box-add-to-cart-button");

    // Category locators
    private By computersMenu = By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]");
    private By desktopsSubMenu = By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Desktops')]");
    private By notebooksSubMenu = By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Notebooks')]");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Search
    public void searchProduct(String productName) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        searchInput.clear();
        searchInput.sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    // Navigate categories
    public void navigateToDesktops() {
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(computersMenu));
        menu.click();
        WebElement desktops = wait.until(ExpectedConditions.elementToBeClickable(desktopsSubMenu));
        desktops.click();
    }

    public void navigateToNotebooks() {
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(computersMenu));
        menu.click();
        WebElement notebooks = wait.until(ExpectedConditions.elementToBeClickable(notebooksSubMenu));
        notebooks.click();
    }

    // Get total products displayed
    public int getProductCount() {
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productList));
        return products.size();
    }

    // Add first product to cart
    public void addFirstProductToCart() {
        List<WebElement> buttons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addToCartButtons));
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    // Add multiple products to cart (up to n)
    public void addMultipleProductsToCart(int n) {
        List<WebElement> buttons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addToCartButtons));
        for (int i = 0; i < Math.min(n, buttons.size()); i++) {
            buttons.get(i).click();
        }
    }
}
