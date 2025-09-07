package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;

    // Locators
    private By billingAddressContinue = By.cssSelector("button.new-address-next-step-button");
    private By shippingMethodContinue = By.cssSelector("button.shipping-method-next-step-button");
    private By paymentMethodContinue = By.cssSelector("button.payment-method-next-step-button");
    private By paymentInfoContinue = By.cssSelector("button.payment-info-next-step-button");
    private By confirmOrderButton = By.cssSelector("button.confirm-order-next-step-button");
    private By orderSuccessMessage = By.cssSelector(".order-completed strong");

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void completeCheckout() {
        driver.findElement(billingAddressContinue).click();
        driver.findElement(shippingMethodContinue).click();
        driver.findElement(paymentMethodContinue).click();
        driver.findElement(paymentInfoContinue).click();
        driver.findElement(confirmOrderButton).click();
    }

    public String getOrderConfirmationMessage() {
        return driver.findElement(orderSuccessMessage).getText();
    }
}
