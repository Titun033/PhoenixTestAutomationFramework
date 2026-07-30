package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.pojo.UserCredentials;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	UserCredentials userCreds= new UserCredentials("iamfd","password");
	
	@Test
	public void loginAPITest() {
		given() 
		.baseUri("http://64.227.160.186:9000/v1")
		.contentType(JSON)
		.body(userCreds)
		.when()
		.post("/login")
		.then()
		.log().all()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginAPIResponseSchema.json"))
		.body("message", equalTo("Success"));
	}

}
