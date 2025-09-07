package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By billingAddressContinue = By.cssSelector("button[name='save']");
    private By shippingMethodContinue = By.cssSelector("button[name='save-shipping-method']");
    private By paymentMethodContinue = By.cssSelector("button[name='save-payment-method']");
    private By paymentInfoContinue = By.cssSelector("button[name='save-payment-info']");
    private By confirmOrderButton = By.cssSelector("button.confirm-order-next-step-button");
    private By orderSuccessMessage = By.cssSelector(".section.order-completed .title");

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickBillingAddressContinue(){
        wait.until(ExpectedConditions.elementToBeClickable(billingAddressContinue)).click();
    }

    public void clickShippingMethodContinue(){
        wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinue)).click();
    }

    public void clickPaymentMethodContinue(){
        wait.until(ExpectedConditions.elementToBeClickable(paymentMethodContinue)).click();
    }

    public void clickPaymentInfoContinue(){
        wait.until(ExpectedConditions.elementToBeClickable(paymentInfoContinue)).click();
    }

    public void clickConfirmOrder(){
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton)).click();
    }

    public String getOrderSuccessMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessMessage)).getText();
    }
}
