package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Updated locators for nopCommerce demo site
    private By cartItems = By.cssSelector(".cart-item-row, .cart-item");
    private By removeButtons = By.cssSelector(".remove-btn, .remove-product");
    private By checkoutButton = By.cssSelector("button#checkout");

    public CartPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int getCartItemCount(){
        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItems));
        return items.size();
    }

    public void removeFirstItem(){
        List<WebElement> removeBtns = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(removeButtons));
        if(!removeBtns.isEmpty()) removeBtns.get(0).click();
    }

    public void clickCheckout(){
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}
