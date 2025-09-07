package testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import pages.ProductPage;
import pages.LoginPage;
import utils.DriverManager;
import org.openqa.selenium.WebDriver;

public class CartTest {
    private WebDriver driver;
    private ProductPage productPage;
    private CartPage cartPage;
    private LoginPage loginPage;

    private final String testEmail = "existingemail@example.com";
    private final String testPassword = "Password123";

    @BeforeClass
    public void setup() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        boolean loggedIn = loginPage.login(testEmail, testPassword);
        if(!loggedIn){
            throw new RuntimeException("Login failed, cannot run CartTest");
        }

        driver.get("https://demo.nopcommerce.com/cart"); // go to cart page

        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @BeforeMethod
    public void cleanCartBeforeEachTest() {
        // Keep 1 laptop in cart to test remove/add if already present
        while(cartPage.getCartItemCount() > 1) {
            cartPage.removeFirstItem();
        }
    }

    @Test(priority = 1)
    public void verifyCartHasLaptop() {
        Assert.assertTrue(cartPage.getCartItemCount() > 0, "Cart should have at least 1 item (Laptop).");
    }

    @Test(priority = 2)
    public void removeProductFromCartTest() {
        int initialCount = cartPage.getCartItemCount();
        cartPage.removeFirstItem();
        Assert.assertTrue(cartPage.getCartItemCount() < initialCount, "Cart item count should decrease.");
    }

    @Test(priority = 3)
    public void emptyCartTest() {
        while(cartPage.getCartItemCount() > 0){
            cartPage.removeFirstItem();
        }
        Assert.assertEquals(cartPage.getCartItemCount(), 0, "Cart should be empty.");
    }

    @Test(priority = 4)
    public void cartCheckoutButtonTest() {
        // Add laptop to test checkout
        productPage.searchProduct("Laptop");
        productPage.addFirstProductToCart();
        cartPage.clickCheckout();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"), "Should navigate to checkout page.");
    }

    @AfterClass
    public void teardown() {
        DriverManager.quitDriver();
    }
}
