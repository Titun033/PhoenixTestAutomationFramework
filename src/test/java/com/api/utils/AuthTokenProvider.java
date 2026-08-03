package com.api.utils;

import static com.api.constant.Role.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

import com.api.constant.Role;
import com.pojo.UserCredentials;

public class AuthTokenProvider {
	//Make a LoginAPI request, extract the token and print it on the console
	private AuthTokenProvider() {
		
	}
	
	public static String getToken(Role role) {
		UserCredentials userCreds = null;
		if(role==FD) {
			userCreds= new UserCredentials("iamfd","password");
		}else if(role==SUP) {
			userCreds= new UserCredentials("iamsup","password");
		}else if(role==ENG) {
			userCreds= new UserCredentials("iameng","password");
		}else if(role==QC) {
			userCreds= new UserCredentials("iamqc","password");
		}
		 
		String token=given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.contentType(JSON)
		.body(userCreds)
		.log().all()
		.when()
		.post("/login")
		.then()
		.log().ifValidationFails()
		.statusCode(200)
		.body("message", equalTo("Success"))
		.extract()
		.body()
		.jsonPath()
		.getString("data.token");

		
		return token;
	}

}
