package com.mishipay.webshopper.reporting;
// remove this class if it doesn't work 

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


 public class ReportManager {
	 
        private static ExtentReports extent;
        private static ExtentTest test;

        public static ExtentReports getExtentReports() {
            if (extent == null) {
                String reportPath = System.getProperty("user.dir") + "/test-output/webappresults.html";
                ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

                extent = new ExtentReports();
                sparkReporter.config().setReportName("Web Automation Results");
                sparkReporter.config().setDocumentTitle("Test Results");

                extent.attachReporter(sparkReporter);
                extent.setSystemInfo("Tester", "Harshitha");
                extent.setSystemInfo("Environment", "QA");
            }
            return extent;
        }

        public static ExtentTest createTest(String testName) {
            test = getExtentReports().createTest(testName);
            return test;
        }

        public static void flushReports() {
            if (extent != null) {
                extent.flush();
            }      
        
      }     
       
}



