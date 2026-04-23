package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import browserFactory.BrowserFactory;
import dataProvider.ConfigReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/***
 * I added direct URL override support using an appUrl system property. 
 * The framework first checks if appUrl is passed from Maven command line. 
 * If not, it falls back to the environment-specific URL like qa.url or stage.url.
 *  This is useful for CI/CD pipelines and temporary test environments.
 */

public class BaseClass {
	
	public WebDriver driver;
	private static final Logger logger = LogManager.getLogger(BaseClass.class);

	//added alwaysRun=true to setup and teardown methods so they execute even when tests are filtered by groups
	@BeforeMethod(alwaysRun = true)
	public void setupBrowser() {
		
		String env = ConfigReader.getProperty("env");
		String applicationUrl = ConfigReader.getProperty("appurl");
		
		if (applicationUrl == null || applicationUrl.isBlank()) {
		    applicationUrl = ConfigReader.getProperty(env + ".url");
		}
		
		//1. property in config.properties , this doesn't suite for cross browser
		driver = BrowserFactory.startBrowser(ConfigReader.getProperty("browser"), applicationUrl);
		
		logger.info("Application is up and running on environment: " + env);
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		if (BrowserFactory.getDriverInstance() != null) {
			BrowserFactory.getDriverInstance().quit();
			BrowserFactory.removeDriver();
		}
		logger.info("Closing the browser and Application");
	}
}
