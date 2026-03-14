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
		
		parentTestThreadLocal.get().pass("Test Passed",MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
	    
	  }

	public void onTestFailure(ITestResult result) {
		String base64 = Utilities.captureScreenshotBase64Format(BrowserFactory.getDriverInstance());
		
		parentTestThreadLocal.get().fail("Test Failed" + result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
	    
	  }
	
	public void onTestSkipped(ITestResult result) {
		String base64 = Utilities.captureScreenshotBase64Format(BrowserFactory.getDriverInstance());
	    
		parentTestThreadLocal.get().skip("Test Skipped" + result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
	  }
	
	public void onFinish(ITestContext context) {
		
		extentReports.flush();
	    
	  }
	
}
