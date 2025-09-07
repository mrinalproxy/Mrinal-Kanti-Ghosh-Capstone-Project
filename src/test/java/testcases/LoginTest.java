package testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.DriverManager;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setup() {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = DriverManager.getDriver();
        driver.get("https://demo.nopcommerce.com/login"); // Navigate to login page
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void validLoginTest() {
        loginPage.enterEmail("validemail@example.com");
        loginPage.enterPassword("ValidPassword123");
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("nopcommerce"), "URL does not contain 'nopcommerce'");
    }
    @Test(priority = 2)
    public void invalidEmailLoginTest() {
        loginPage.enterEmail("invalid@example.com");
        loginPage.enterPassword("ValidPassword123");
        loginPage.clickLogin();
        Assert.assertTrue(
            loginPage.getErrorMessage().contains("Login was unsuccessful"),
            "Error message mismatch"
        );
    }

    @Test(priority = 3)
    public void invalidPasswordLoginTest() {
        loginPage.enterEmail("validemail@example.com");
        loginPage.enterPassword("WrongPassword");
        loginPage.clickLogin();
        Assert.assertTrue(
            loginPage.getErrorMessage().contains("Login was unsuccessful"),
            "Error message mismatch"
        );
    }

    @Test(priority = 4)
    public void blankFieldsLoginTest() {
        loginPage.enterEmail("");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        Assert.assertTrue(
            loginPage.getErrorMessage().contains("Login was unsuccessful"),
            "Error message mismatch"
        );
    }


    @AfterClass
    public void teardown() {
        DriverManager.quitDriver();
    }
}
