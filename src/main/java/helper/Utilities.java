package helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	
	public static WebElement highlightElement(WebDriver driver, WebElement element) {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border :2px solid red;' )", element);
		
		waitForSeconds(1);
		
		js.executeScript("arguments[0].setAttribute('style', 'border :2px solid white;')", element);
		
		return element;
	}
	
	
	public static Alert waitForAlert(WebDriver driver) {
		
		Alert alt = null;
		
		for(int i=0; i<=15 ; i++) {
				
			try 
			{
				alt = driver.switchTo().alert();
				break;
			}
			catch(NoAlertPresentException e)
			{
				System.out.println("No Alert Found- Waiting for Alert");
				
				waitForSeconds(1);
			}
			
		}
		
		return alt;
		
	}
	
	
	public static Alert waitForAlert(WebDriver driver, int time) {
		
		Alert alt = null;
		
		for(int i=0; i<=time ; i++) {
				
			try 
			{
				alt = driver.switchTo().alert();
				break;
			}
			catch(NoAlertPresentException e)
			{
				System.out.println("No Alert Found- Waiting for Alert");
				
				waitForSeconds(1);
			}
			
		}
		
		return alt;
		
	}
	
	public static void waitForSeconds(int seconds) {
				
			try 
			{
				Thread.sleep(seconds*1000);
			} 
			catch (InterruptedException e) 
			{
				
			}
			
	}
	
	public static void captureScreenShot(WebDriver driver) {
		
		try 
		{
			FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),new File("./Screenshots/"+ Utilities.getCurrentTime()+".png"));
		} 
		catch (IOException e) 
		{
			System.out.println("Something went wrong "+ e.getMessage());
		}
		
	}
	
	public static String getCurrentTime() {
		
		//Date date = new Date();
		
		//SimpleDateFormat customDateFormat = new SimpleDateFormat("HH:mm:ss_dd_MM_yyyy");
		
		//return(customDateFormat.format(date));
		
		
		String date = new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy").format(new Date());
		
		return(date);
		
	}

	public static void captureScreenshotOfWebElement(WebDriver driver, WebElement element) {
		
		
		try 
		{
			FileHandler.copy(element.getScreenshotAs(OutputType.FILE),new File("./ElementScreenshot/"+ Utilities.getCurrentTime()+".png"));
		} 
		catch (IOException e) 
		{
			System.out.println("Something went wrong "+ e.getMessage());
		}
		
	}
	
	public static String captureScreenshotBase64Format(WebDriver driver) {
		
		if (driver == null) {
	        return null;
	    }
		
		TakesScreenshot tScreenshot = (TakesScreenshot) driver;
		
		String base64 = tScreenshot.getScreenshotAs(OutputType.BASE64);
		
		return base64;
	}
	
	//screenshot of complete page is captured by aShot library. Add the dependency and call. Explore this
	
	public static void waitForElement(WebDriver driver, By locator , int seconds, String type) {
		//you can use switch instead of if stmts
		
			for (int i=0;i<=seconds;i++) {
				
				if( type == "input" || type == "button") {
					try 
					{
							driver.findElement(locator).click();
						    driver.findElement(locator).sendKeys("Hello");			    		    
							System.out.println("Element found ");
							break;
					}
					
					catch(NoSuchElementException | StaleElementReferenceException |ElementNotInteractableException e) 
					{
						 System.out.println("Could not interact with element");
						 waitForSeconds(3);
					}
					
				}
				
				if (type == "text") {
					try 
					 {
						 System.out.println(driver.findElement(locator).isDisplayed());
						 System.out.println("Element found ");
							break;
						
					 } 
					 catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException e2) 
					 {
						System.out.println("Could not interact with elements");
						waitForSeconds(3);
					 }
				}
			}
		
	}
	
}
