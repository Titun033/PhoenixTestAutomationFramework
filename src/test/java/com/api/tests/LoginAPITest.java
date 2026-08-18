package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import static com.api.utils.ConfigManager.*;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	UserCredentials userCreds= new UserCredentials("iamfd","password");
	
	@Test
	public void loginAPITest() {
		
		given() 
		.spec(SpecUtil.reqestSpec(userCreds))
		.when()
		.post("/login")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponseSchema/LoginAPIResponseSchema.json"))
		.body("message", equalTo("Success"));
	}

}
