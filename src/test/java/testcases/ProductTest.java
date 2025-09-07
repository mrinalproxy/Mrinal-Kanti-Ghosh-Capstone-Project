package testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.ProductPage;
import utils.DriverManager;
import org.openqa.selenium.WebDriver;

public class ProductTest {
    private WebDriver driver;
    private ProductPage productPage;

    @BeforeClass
    public void setup() {
        driver = DriverManager.getDriver();
        driver.get("https://demo.nopcommerce.com");
        productPage = new ProductPage(driver);
    }

    @Test(priority = 1)
    public void searchLaptopTest() {
        productPage.searchProduct("Laptop");
        Assert.assertTrue(productPage.getProductCount() > 0, "Laptop search should return results");
    }

    @Test(priority = 2)
    public void navigateAndSearchDesktopsTest() {
        productPage.navigateToDesktops();
        Assert.assertTrue(productPage.getProductCount() > 0, "Desktops category should have products");
    }

    @AfterClass
    public void teardown() {
        DriverManager.quitDriver();
    }
}
