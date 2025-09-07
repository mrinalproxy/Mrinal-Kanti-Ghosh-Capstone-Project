package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class TestListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        // Suite start
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().pass("✅ Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        String path = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
        try {
            testThread.get().fail(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (Exception e) {
            testThread.get().fail("❌ Failed - screenshot could not be attached: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().skip("⚠️ Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // Writes all test cases into ONE report
    }
}
