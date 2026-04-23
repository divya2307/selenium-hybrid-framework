package browserFactory;

import java.time.Duration;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import dataProvider.ConfigReader;

/***
 * BrowserFactory is already responsible for below, all driver-related logic in one place:
 * browser selection
	driver creation
	browser options
	timeouts
	launching URL
	
	
	I added ThreadLocal WebDriver in BrowserFactory because WebDriver is a shared framework-level object. 
	Earlier it was static, which works for sequential execution but can cause driver conflicts in parallel execution. 
	With ThreadLocal, each test thread gets its own isolated browser instance.
	BrowserFactory is the right place because it owns browser creation and driver management.”
 */

public class BrowserFactory {
	
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static WebDriver getDriverInstance() {
		return tlDriver.get();	
	}
	
	public static void setDriver(WebDriver driver) {
		tlDriver.set(driver);
	}
	public static void removeDriver() {
		tlDriver.remove();
	}
	
	
	public static WebDriver setOptions(WebDriver driver , String browser) {
		
		if (browser.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			
			if(Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
			}
				

			// Create a map to store preferences
			Map<String, Object> prefs = new HashMap<String, Object>();

			// 1. Disable the 'Offer to save passwords' popup
			prefs.put("credentials_enable_service", false);

			// 2. Disable the internal password manager entirely
			prefs.put("profile.password_manager_enabled", false);

			// 3. Disable the 'Change your password' data breach warning (Chrome 120+)
			prefs.put("profile.password_manager_leak_detection", false);

			// Apply preferences to ChromeOptions
			options.setExperimentalOption("prefs", prefs);
			
			driver = new ChromeDriver(options);
		}
		
		else if(browser.equalsIgnoreCase("Edge")) {
			EdgeOptions options = new EdgeOptions();
			
			if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
			    options.addArguments("--headless=new");
			    options.addArguments("--window-size=1920,1080");
			}

			// Create a map to store preferences
			Map<String, Object> prefs = new HashMap<String, Object>();

			// 1. Disable the 'Offer to save passwords' popup
			prefs.put("credentials_enable_service", false);

			// 2. Disable the internal password manager entirely
			prefs.put("profile.password_manager_enabled", false);

			// 3. Disable the 'Change your password' data breach warning (Chrome 120+)
			prefs.put("profile.password_manager_leak_detection", false);

			// Apply preferences to ChromeOptions
			options.setExperimentalOption("prefs", prefs);
			
			driver = new EdgeDriver(options);
		}
		
		else if(browser.equalsIgnoreCase("Firefox")) {
			
			FirefoxOptions options = new FirefoxOptions();
			
			if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
			    options.addArguments("--headless");
			}

			// Disable the "Remember Logins" prompt
			options.addPreference("signon.rememberSignons", false);

			// Optional: Disable warning for insecure login forms (http)
			options.addPreference("security.insecure_password.ui.enabled", false);
			
			driver = new FirefoxDriver(options);
			
		}
		
		else {
			System.out.println("For safari, options are now available, please configure manually");
		}
		
			return driver;
		
	}

	
	public static WebDriver startBrowser(String browser, String applicationURL) {
		//I am preparing a local variable. Based on the browser name, 
		//I will assign ChromeDriver, FirefoxDriver, EdgeDriver, etc
		
		/***
		 * I use a local WebDriver variable during browser creation because ThreadLocal should store the final driver object after it is created. 
		 * Setting ThreadLocal to null at the beginning is not useful because it still returns null and does not create a browser. 
		 * For cleanup, I use remove() instead of setting null, because remove() clears the ThreadLocal value completely for that thread.”
		 */
		
		WebDriver driver = null;
		
		if (browser.equalsIgnoreCase("Chrome") || browser.equalsIgnoreCase("GC") || browser.equalsIgnoreCase("Google Chrome")) {
			driver = setOptions(driver,"Chrome");

		}
		else if (browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("FF") || browser.equalsIgnoreCase("Mozilla Firefox")) {
			driver = setOptions(driver,"Firefox");
		}
		else if (browser.equalsIgnoreCase("Safari")){
			driver = new SafariDriver();
		}
		else if (browser.equalsIgnoreCase("Edge") || browser.equalsIgnoreCase("IE")){
			driver = setOptions(driver,"Edge");
		}
		else {
			System.out.println("Sorry, cannot open this browser, for now opening chrome");
			driver = setOptions(driver,"Chrome");
		}
		
		setDriver(driver);
		
		getDriverInstance().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		getDriverInstance().manage().timeouts().scriptTimeout(Duration.ofSeconds(15));
		getDriverInstance().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		getDriverInstance().manage().window().maximize();
		getDriverInstance().get(applicationURL);
		
		return getDriverInstance();
	}

}
