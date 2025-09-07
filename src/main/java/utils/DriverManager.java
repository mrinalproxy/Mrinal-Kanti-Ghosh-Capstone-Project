package utils;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Thread-safe WebDriver manager.
 * Supports Chrome and Edge (headless or GUI).
 */
public class DriverManager {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static final Logger log = LoggerFactory.getLogger(DriverManager.class);

    // List of supported browsers
    public static final List<String> SUPPORTED_BROWSERS = Arrays.asList("chrome", "edge");

    /**
     * Initialize WebDriver for a given browser
     * @param browserName Browser name: chrome or edge
     */
    public static void initDriver(String browserName) {
        if (tlDriver.get() == null) {
            String browser = (browserName == null) ? "chrome" : browserName.toLowerCase();

            try {
                boolean isCI = "true".equalsIgnoreCase(System.getenv("CI"));
                log.info("Starting WebDriver for browser: {} (CI mode: {})", browser, isCI);

                switch (browser) {
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        EdgeOptions edgeOptions = new EdgeOptions();
                        if (isCI) {
                            edgeOptions.addArguments("--headless=new");
                            edgeOptions.addArguments("--disable-gpu");
                            edgeOptions.addArguments("--no-sandbox");
                            edgeOptions.addArguments("--disable-dev-shm-usage");
                            edgeOptions.addArguments("--window-size=1920,1080");
                        }
                        tlDriver.set(new EdgeDriver(edgeOptions));
                        break;

                    case "chrome":
                    default:
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions chromeOptions = new ChromeOptions();
                        if (isCI) {
                            chromeOptions.addArguments("--headless=new");
                            chromeOptions.addArguments("--disable-gpu");
                            chromeOptions.addArguments("--no-sandbox");
                            chromeOptions.addArguments("--disable-dev-shm-usage");
                            chromeOptions.addArguments("--window-size=1920,1080");
                        }
                        tlDriver.set(new ChromeDriver(chromeOptions));
                        break;
                }

                // Default implicit wait = 10s
                int waitTime = 10;
                tlDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
                tlDriver.get().manage().window().maximize();

                log.info("Initialized WebDriver for browser: {}", browser);
            } catch (Exception e) {
                log.error("Error initializing WebDriver for browser: {}", browser, e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Get WebDriver instance.
     * Ensures driver is initialized with default Chrome if not already.
     */
    public static WebDriver getDriver() {
        if (tlDriver.get() == null) {
            initDriver("chrome"); // fallback to Chrome
        }
        return tlDriver.get();
    }

    /**
     * Quit WebDriver
     */
    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
            log.info("Closed WebDriver");
        }
    }
}
