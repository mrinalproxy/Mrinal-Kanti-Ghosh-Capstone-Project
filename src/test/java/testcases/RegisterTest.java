package testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.RegisterPage;
import utils.DriverManager;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RegisterTest {
    private WebDriver driver;
    private RegisterPage registerPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = DriverManager.getDriver();
        registerPage = new RegisterPage(driver);
    }

    @Test(priority = 1)
    public void validRegistrationTest() {
        registerPage.register("John", "Doe", "john" + System.currentTimeMillis() + "@example.com", "Password123");
        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
    }

    @Test(priority = 2)
    public void duplicateEmailRegistrationTest() {
        registerPage.register("John", "Doe", "existingemail@example.com", "Password123");
        // Expected error handling based on site response
    }

    @Test(priority = 3)
    public void passwordMismatchTest() {
        registerPage.goToRegisterPage();
        registerPage.enterFirstName("John");
        registerPage.enterLastName("Doe");
        registerPage.enterEmail("johnmismatch@example.com");
        registerPage.enterPassword("Password123");
        registerPage.enterConfirmPassword("Password321");
        registerPage.clickRegister();
        // Assert error message appears
    }

    @Test(priority = 4)
    public void blankFieldsRegistrationTest() {
        registerPage.goToRegisterPage();
        registerPage.clickRegister();
        // Assert error messages for required fields
    }

    @AfterClass
    public void teardown() {
        DriverManager.quitDriver();
    }
}
