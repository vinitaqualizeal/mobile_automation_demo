package org.rahushettyacademy.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG
{
    static ExtentReports extent;

    public static ExtentReports getReporterObject()
    {
        //ExtentReports, ExtentSparkReporter
        String path = System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Java Mobile Automation");
        reporter.config().setDocumentTitle("Srinivasan Automation");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Srinivasan Sakthivel");
        return extent;

    }
}
