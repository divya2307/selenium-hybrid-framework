package dataProvider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {
	
	@DataProvider(name="loginDetails" , parallel = true)
	public static Object[][] getData() {
		Object[][] arr = ExcelReader.getDataFromSheet("LoginDetails");
		return arr;
	}
	

}
