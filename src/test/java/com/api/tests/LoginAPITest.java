package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import static com.api.utils.ConfigManager.*;
import com.pojo.UserCredentials;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	UserCredentials userCreds= new UserCredentials("iamfd","password");
	
	@Test
	public void loginAPITest() {
		
		given() 
		.baseUri(readPropertiesFile("BASE_URI"))
		.contentType(JSON)
		.body(userCreds)
		.log().all()
		.when()
		.post("/login")
		.then()
		.log().all()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginAPIResponseSchema.json"))
		.body("message", equalTo("Success"));
	}

}
