package api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

//This gives reusable HTTP methods :GET,POST,PUT,DELETE

public class APIClient {
	
	public static Response get(String endpoint) {
		return given()
				.spec(RequestBuilder.getRequestSpec())
				.when()
				.get(endpoint);
	}
	
	public static Response post(String endpoint, Object payload) {
        return given()
                .spec(RequestBuilder.getRequestSpec())
                .body(payload)
                .when()
                .post(endpoint);
    }

    public static Response put(String endpoint, Object payload) {
        return given()
                .spec(RequestBuilder.getRequestSpec())
                .body(payload)
                .when()
                .put(endpoint);
    }

    public static Response delete(String endpoint) {
        return given()
                .spec(RequestBuilder.getRequestSpec())
                .when()
                .delete(endpoint);
    }

}
