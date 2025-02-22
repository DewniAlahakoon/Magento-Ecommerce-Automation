package com.softwaretestingboard.magento.listeners;

import com.softwaretestingboard.magento.utils.ExtentManager;
import com.softwaretestingboard.magento.utils.TestBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends TestBase implements ITestListener {

    public Listeners() throws IOException {
        super();

    }
    public synchronized void onStart(ITestContext context) {
        ExtentManager.getReport();
        ExtentManager.createTest(context.getName(), context.getName());
    }

    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().pass("Test passed: " + result.getName());
        System.out.println("Test passed: " + result.getName());
    }


    public void onTestFailure(ITestResult result) {

        ExtentManager.getTest().fail(result.getThrowable());
        try {
            System.out.println("Test failed: " + result.getName());
            takeSnapshot(result.getMethod().getMethodName());
            ExtentManager.attachImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().skip("Test skipped: " + result.getName());
        System.out.println("Test skipped: " + result.getName());
    }

    public synchronized void onFinish(ITestContext context) {
        ExtentManager.flushReport();
    }
}