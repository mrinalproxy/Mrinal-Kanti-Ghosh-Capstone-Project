package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                System.out.println("Initializing ChromeDriver using WebDriverManager...");
                WebDriverManager.chromedriver().setup();  // auto-downloads correct driver
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                System.out.println("ChromeDriver initialized successfully.");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to initialize WebDriver.");
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("Driver quit successfully.");
        }
    }
}
