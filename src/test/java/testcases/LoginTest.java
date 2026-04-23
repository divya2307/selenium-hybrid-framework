package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import dataProvider.CustomDataProvider;
import pages.LoginPage;



public class LoginTest extends BaseClass {

	@Test(  dataProvider = "loginDetails",
			dataProviderClass = CustomDataProvider.class,
			groups = {"smoke", "regression"}		
		 )
	public void loginToApplication(String username, String password) {
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.loginToApplication(username, password);
		
		Assert.assertEquals(loginPage.textValidate(), "iNeuron Courses");
		
	}
}
