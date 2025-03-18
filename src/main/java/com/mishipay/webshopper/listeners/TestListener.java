package com.mishipay.webshopper.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.IExecutionListener;

import com.aventstack.extentreports.ExtentTest;
import com.mishipay.webshopper.reporting.ReportManager;
import com.mishipay.webshopper.utils.EmailUtils;
import com.mishipay.webshopper.utils.ScreenshotUtil;
import com.mishipay.webshopper.base.TestBase;
import org.openqa.selenium.WebDriver;


public class TestListener implements IExecutionListener,ITestListener {
    private static ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = ReportManager.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail("Test Failed: " + result.getMethod().getMethodName());
        test.fail(result.getThrowable());
        
        Object testInstance = result.getInstance();
        WebDriver driver = ((TestBase) testInstance).getDriver();
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
        
        if (screenshotPath != null) {
            test.addScreenCaptureFromPath(screenshotPath, "Screenshot of failure");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ReportManager.flushReports();
    }
    
    @Override
    public void onExecutionFinish() {
        String reportPath = System.getProperty("user.dir") + "/test-output/emailable-report.html";

    	//String reportPath = "/test-output/webappresults.html";
        EmailUtils.sendEmail(reportPath);

    }
    
    @Override
    public void onExecutionStart() {
    	System.out.println("Test execution started...");
    }
   
}




