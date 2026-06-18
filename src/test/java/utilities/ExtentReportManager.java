package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/TestReport.html");
            spark.config().setReportName("SpotQuote API Automation Report");
            spark.config().setDocumentTitle("API Test Results");
            spark.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Framework", "RestAssured + TestNG");
            extent.setSystemInfo("Environment", System.getProperty("env", "qa"));
            extent.setSystemInfo("Base URL", ConfigReader.get("base.url"));
        }
        return extent;
    }
}
