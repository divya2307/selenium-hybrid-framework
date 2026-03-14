package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import browserFactory.BrowserFactory;
import dataProvider.ConfigReader;

public class BaseClass {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setupBrowser() {
		
		//1. property in config.properties , this doesn't suite for cross browser
		driver = BrowserFactory.startBrowser(ConfigReader.getProperty("browser"), ConfigReader.getProperty("url"));
		
		System.out.println("LOG:INFO -- Application is up and running");
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
		
		System.out.println("LOG:INFO -- Closing the browser and Application");
	}
}
