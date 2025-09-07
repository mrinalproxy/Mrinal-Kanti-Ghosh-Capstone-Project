package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[STARTING TEST] " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[PASSED TEST] " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[FAILED TEST] " + result.getName());

        Object currentClass = result.getInstance();
        WebDriver driver = DriverManager.getDriver();

        if (driver != null) {
            takeScreenshot(driver, result.getName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[SKIPPED TEST] " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("===== Test Execution Started =====");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("===== Test Execution Finished =====");
    }

    private void takeScreenshot(WebDriver driver, String testName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = "screenshots/" + testName + "_" + timestamp + ".png";

            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(srcFile.toPath(), Paths.get(screenshotName));

            System.out.println("Screenshot saved: " + screenshotName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
