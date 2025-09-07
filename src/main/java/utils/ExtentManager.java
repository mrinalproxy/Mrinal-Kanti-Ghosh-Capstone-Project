package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // Add environment/system details
            extent.setSystemInfo("Project", "NopCommerce Capstone");
            extent.setSystemInfo("Tester", "Mrinal");
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }
}
