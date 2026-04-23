package helper;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//We are making methods static because this is a utility class and we can directly call the methods instead of creating the objects of the utility class.

public class WaitUtils {
	
	public static WebElement waitForElementVisible(WebDriver driver, By locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static WebElement waitForElementClickable(WebDriver driver , By locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static Alert waitForAlertPresent(WebDriver driver, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public static boolean waitForUrlContains(WebDriver driver, String partialUrl , int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.urlContains(partialUrl));		
	}
	
	public static boolean waitForTitleContains(WebDriver driver, String partialTitle , int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.titleContains(partialTitle));
	}
	
    public static boolean waitForTextPresent(WebDriver driver, By locator, String text, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
}
