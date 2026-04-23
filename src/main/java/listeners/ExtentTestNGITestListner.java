package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;


import browserFactory.BrowserFactory;
import helper.Utilities;

//This class "listens" for TestNG events and manages the ExtentTest lifecycle automatically.

public class ExtentTestNGITestListner implements ITestListener{
	
	
	private static ExtentReports extentReports;
	private static ThreadLocal<ExtentTest> parentTestThreadLocal = new ThreadLocal<>();

	
	//ThreadLocal<ExtentTest> parentTestThreadLocal = new ThreadLocal<ExtentTest>();
	
	
	
	
	public void onTestStart(ITestResult result) {
		
		try {
            // Only initialize when the test suite actually starts
            extentReports = ExtentManager.getExtentReportIntanceStatus();
        } catch (Exception e) {
            System.err.println("FAILED TO INITIALIZE EXTENT REPORTS:");
            e.printStackTrace(); // This will show the EXACT line and reason for the crash
        }
	    
	    
		ExtentTest test = extentReports.createTest(result.getMethod().getMethodName());
		parentTestThreadLocal.set(test);
		
	  }
	
	public void onTestSuccess(ITestResult result) {
		String base64 = Utilities.captureScreenshotBase64Format(BrowserFactory.getDriverInstance());
		if (base64 != null) {
			parentTestThreadLocal.get().pass("Test Passed",MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
		}
		else {
			parentTestThreadLocal.get().pass("Test Passed - screenshot not available");
		}
		parentTestThreadLocal.remove();
	    
	  }

	public void onTestFailure(ITestResult result) {
		String base64 = Utilities.captureScreenshotBase64Format(BrowserFactory.getDriverInstance());
		
		String errorMessage = result.getThrowable() != null
		        ? result.getThrowable().getMessage()
		        : "No exception message available";
		
		if (base64 != null) {
	        parentTestThreadLocal.get().fail(
	            "Test Failed: " + errorMessage,
	            MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build()
	        );
	    } else {
	        parentTestThreadLocal.get().fail("Test Failed: " + errorMessage + " - screenshot not available");
	    }
		
		parentTestThreadLocal.remove();
	    
	  }
	
	public void onTestSkipped(ITestResult result) {
		String base64 = Utilities.captureScreenshotBase64Format(BrowserFactory.getDriverInstance());
	    
		String skipMessage = result.getThrowable() != null
		        ? result.getThrowable().getMessage()
		        : "No skip reason available";
		    
		    if (base64 != null) {
		        parentTestThreadLocal.get().skip(
		            "Test Skipped: " + skipMessage,
		            MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build()
		        );
		    } else {
		        parentTestThreadLocal.get().skip("Test Skipped: " + skipMessage + " - screenshot not available");
		    }
		    
		    parentTestThreadLocal.remove();
	}
	
	public void onFinish(ITestContext context) {
		
		extentReports.flush();
	    
	  }
	
}
