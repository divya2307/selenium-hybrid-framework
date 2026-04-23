package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import helper.WaitUtils;
import base.BaseClass;


public class LoginPage extends BaseClass {
	
	// 1. Locators (Private to ensure encapsulation)
	WebDriver driver;
	private By username = By.xpath("//input[@placeholder='Email']");
	private By password = By.xpath("//input[@placeholder='Password']");
	private By signinBtn = By.xpath("//button[@class='submit-btn']");
	private By practise_href = By.xpath("//a[text()='Practise']");
	private By signup_link = By.xpath("//a[contains(text(),'Signup')]");
	private By text_validate = By.xpath("//h1[text()='iNeuron Courses']");
	private By signOutBtn = By.xpath("//button[text()='Sign out']");
	
	
	// 2. Constructor (Inject the driver)
	public LoginPage(WebDriver driver) {
		this.driver=driver;
//		super(driver);
	}
	
	// 3. Action Methods (Public services for the test)
//	public HomePage signIn() {
//		driver.findElement(username).sendKeys(Utilities.fetchCredentials("username"));
//		driver.findElement(password).sendKeys(Utilities.fetchCredentials("password"));
//		driver.findElement(signinBtn).click();
//		
//		return new HomePage();
//	}
	
	public void loginToApplication(String uname, String pwd) {
		
		WaitUtils.waitForElementVisible(driver, username, 10).sendKeys(uname);
		WaitUtils.waitForElementVisible(driver, password, 10).sendKeys(pwd);
		WaitUtils.waitForElementClickable(driver, signinBtn, 10).click();	
		//return new HomePage();
	}
	

	public PractisePage practise() {
		driver.findElement(practise_href).click();
		
		return new PractisePage();
	}
	
	public SignUp signUpNewUser() {
		driver.findElement(signup_link).click();
		
		return new SignUp();
	}
	
	public String textValidate() {
		return WaitUtils.waitForElementVisible(driver, text_validate, 10).getText();
	}
	
	public void signOut() {
		driver.findElement(signOutBtn).click();
	}
	
}
