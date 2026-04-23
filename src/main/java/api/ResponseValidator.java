package api;

import org.testng.Assert;

import io.restassured.response.Response;

//Validation methods should be reusable

public class ResponseValidator {
	
	public static void verifyStatusCode(Response response , int expectedStatusCode) {	
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);	
	}
	
	public static void verifyResponseTimeBelow(Response response, long maxTimeInMillis) {
        Assert.assertTrue(
                response.getTime() <= maxTimeInMillis,
                "Response time exceeded " + maxTimeInMillis + " ms"
        );
    }
	
	public static void verifyFieldValue(Response response, String jsonPath, Object expectedValue) {
        Assert.assertEquals(response.jsonPath().get(jsonPath), expectedValue);
    }

}
