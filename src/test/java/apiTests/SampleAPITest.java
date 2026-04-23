package apiTests;

import org.testng.annotations.Test;

import api.APIClient;
import api.ResponseValidator;
import io.restassured.response.Response;

public class SampleAPITest {

    @Test(groups = {"api", "regression"})
    public void verifyApplicationIsReachable() {
        Response response = APIClient.get("/");

        ResponseValidator.verifyStatusCode(response, 200);
    }
}
