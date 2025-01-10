package com.softwaretestingboard.magento.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager extends TestBase {


    public static ExtentReports extentReport;
    public static String extentReportPrefix;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public ExtentManager() throws IOException {
        super();
    }
    //checking if the extentReport field is a null value. If the extent report instance is null, it sets up a new extent report with the project name "LiveProject" and returns it
    public static ExtentReports getReport() {

        if(extentReport == null) {

            setUpExtentReport("LiveProject");
        }
        return extentReport;

    }
    //setting up extent reports
    public static ExtentReports setUpExtentReport(String testName) {

        extentReport = new ExtentReports();
        //defining the location where the reports are saved
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/report/" + extentReportsPrefix_Name(testName) + ".html");
        extentReport.attachReporter(spark);

        //sets up additional information for the report
        extentReport.setSystemInfo("Tester", "Dewni Alahakoon");
        extentReport.setSystemInfo("Browser", "Chrome");
        extentReport.setSystemInfo("OS", "Windows");
        spark.config().setReportName("Regression Test");
        spark.config().setDocumentTitle("Test Results");
        spark.config().setTheme(Theme.DARK);
        return extentReport;
    }
    //defines a method extentReportsPrefix_Name() which generates a prefix for the extent report file name based on the test name
    public static String extentReportsPrefix_Name(String testName) {

        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        extentReportPrefix = testName + "_" + date;
        return extentReportPrefix;
    }
    public synchronized static ExtentTest getTest() {
        return extentTest.get();
    }
    public synchronized static ExtentTest createTest(String name, String description) {
        ExtentTest test = extentReport.createTest(name, description);
        extentTest.set(test);
        return getTest();
    }
    public synchronized static void log(String message) {

        getTest().info(message);
    }
    public synchronized static void pass(String message) {
        getTest().pass(message);
    }
    public synchronized static void fail(String message) {
        getTest().fail(message);
    }
    public synchronized static void attachImage() {
        getTest().addScreenCaptureFromPath(getScreenshotDestinationPath());
    }
    public static void flushReport() {
        extentReport.flush();
    }
}
