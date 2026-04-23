package api;

import dataProvider.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

//This centralizes common API request setup:

public class RequestBuilder {
	
	public static RequestSpecification getRequestSpec() {
		
		String baseUri = ConfigReader.getProperty("api.baseUri");
		
		if (baseUri == null || baseUri.isBlank()) {
            throw new RuntimeException(
                "api.baseUri is missing. Please set it in config.properties or pass -Dapi.baseUri=<base-uri>"
            );
        }
		
		return new RequestSpecBuilder()
				.setBaseUri(baseUri)
				.setContentType("application/json")
				.build();
		
	}

}
