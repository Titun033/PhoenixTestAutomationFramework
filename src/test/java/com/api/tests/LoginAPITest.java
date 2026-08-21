package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPITest {
	private UserCredentials userCreds;
	
	@BeforeMethod(description="Create the request payload for login API")
	public void setUp() {
		userCreds= new UserCredentials("iamfd","password");
	}
	
	
	@Test(description="Verify if the login API is working for the IamFD User",groups= {"smoke","api","regression"})
	public void loginAPITest() {
		
		given() 
		.spec(reqestSpec(userCreds))
		.when()
		.post("/login")
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("ResponseSchema/LoginAPIResponseSchema.json"))
		.body("message", equalTo("Success"));
	}

}
