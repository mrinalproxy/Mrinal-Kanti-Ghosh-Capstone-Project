package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    // Locators
    private By cartItems = By.cssSelector(".cart-item-row");
    private By removeButton = By.cssSelector(".remove-btn");
    private By checkoutButton = By.cssSelector(".checkout-button");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public void removeFirstItem() {
        driver.findElement(removeButton).click();
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
