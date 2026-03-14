package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import helper.Utilities;

public class ExtentManager {
	
	
	public static ExtentReports extentReports;
	
	public static ExtentReports getExtentReportIntanceStatus() {
		if(extentReports == null) {
			extentReports = createInstance();
			return extentReports;
		}
		
		else {
			return extentReports;
		}
		
	}
	
	public static ExtentReports createInstance() {
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/Automation_" + Utilities.getCurrentTime() + ".html");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Automation Report");
		sparkReporter.config().setDocumentTitle("Sprint Report");
		
		ExtentReports extentReports = new ExtentReports();
		
		extentReports.attachReporter(sparkReporter);
		
		return extentReports;
	}

}
