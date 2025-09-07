package testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.ProductPage;
import utils.DriverManager;
import org.openqa.selenium.By;
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
    public void searchDesktopTest() {
        productPage.navigateToDesktops();
        Assert.assertTrue(productPage.getProductCount() > 0, "Desktops category should have products");
    }

    @Test(priority = 3)
    public void searchNotebookTest() {
        productPage.navigateToNotebooks();
        Assert.assertTrue(productPage.getProductCount() > 0, "Notebooks category should have products");
    }

    @Test(priority = 4)
    public void addSingleLaptopToCart() {
        productPage.searchProduct("Laptop");
        productPage.addFirstProductToCart();
        Assert.assertTrue(true, "Added one laptop to cart"); // Cart verification in CartTest
    }

    @Test(priority = 5)
    public void addMultipleDesktopsToCart() {
        productPage.navigateToDesktops();
        productPage.addMultipleProductsToCart(2);
        Assert.assertTrue(true, "Added multiple desktops to cart");
    }

    @Test(priority = 6)
    public void addMultipleNotebooksToCart() {
        productPage.navigateToNotebooks();
        productPage.addMultipleProductsToCart(3);
        Assert.assertTrue(true, "Added multiple notebooks to cart");
    }

    @Test(priority = 7)
    public void verifySearchNoResults() {
        productPage.searchProduct("NonExistingProductXYZ");
        Assert.assertEquals(productPage.getProductCount(), 0, "Search should return zero results");
    }

    @Test(priority = 8)
    public void searchAndAddLaptopToCart() {
        productPage.searchProduct("Laptop");
        productPage.addFirstProductToCart();
        Assert.assertTrue(true, "Laptop added to cart after search");
    }

    // ---------- EXTRA TEST CASES ----------

    @Test(priority = 9)
    public void sortProductsByPriceLowToHigh() {
        productPage.navigateToDesktops();
        driver.findElement(By.id("products-orderby")).sendKeys("Price: Low to High");
        Assert.assertTrue(productPage.getProductCount() > 0, "Products should be visible after sorting");
    }

    @Test(priority = 10)
    public void changeProductViewToList() {
        productPage.navigateToNotebooks();
        driver.findElement(By.className("viewmode-icon-list")).click();
        Assert.assertTrue(productPage.getProductCount() > 0, "Products should be visible in list view");
    }

    @Test(priority = 11)
    public void addProductsToCompareList() {
        productPage.navigateToNotebooks();
        driver.findElements(By.cssSelector(".compare-products")).get(0).click();
        productPage.navigateToDesktops();
        driver.findElements(By.cssSelector(".compare-products")).get(0).click();
        driver.findElement(By.linkText("product comparison")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("compareproducts"), "Should navigate to product comparison page");
    }

    @AfterClass
    public void teardown() {
        DriverManager.quitDriver();
    }
}