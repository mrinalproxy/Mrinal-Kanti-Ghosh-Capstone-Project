package testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import pages.CheckoutPage;
import utils.DriverManager;
import org.openqa.selenium.WebDriver;

public class CheckoutTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    private final String testEmail = "existingemail@example.com";
    private final String testPassword = "Password123";

    @BeforeClass
    public void setup(){
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        boolean loggedIn = loginPage.login(testEmail, testPassword);
        if(!loggedIn){
            throw new RuntimeException("Login failed, cannot run CheckoutTest");
        }

        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        // Ensure cart has at least 1 product
        productPage.searchProduct("Laptop");
        productPage.addFirstProductToCart();

        driver.get("https://demo.nopcommerce.com/cart");
    }

    @Test(priority = 1)
    public void checkoutProcessTest(){
        cartPage.clickCheckout();
        checkoutPage.clickBillingAddressContinue();
        checkoutPage.clickShippingMethodContinue();
        checkoutPage.clickPaymentMethodContinue();
        checkoutPage.clickPaymentInfoContinue();
        checkoutPage.clickConfirmOrder();

        String successMsg = checkoutPage.getOrderSuccessMessage();
        Assert.assertTrue(successMsg.contains("Your order has been successfully processed!"),
                "Order was not successful");
    }

    @AfterClass
    public void teardown(){
        DriverManager.quitDriver();
    }
}
