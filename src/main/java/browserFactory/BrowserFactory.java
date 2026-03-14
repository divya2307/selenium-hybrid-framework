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

public class BrowserFactory {
	
	public static WebDriver driver;
	
	public static WebDriver getDriverInstance() {
		return driver;	
	}
	
	public static WebDriver setOptions(WebDriver driver , String browser) {
		
		if (browser.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();

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
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get(applicationURL);
		
		return driver;
	}

}
