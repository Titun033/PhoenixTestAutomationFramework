package com.api.tests;

import static com.api.utils.ConfigManager.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.ANY;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import static com.api.constant.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.Header;

public class UserDetailsAPIForFDTest {
	
	@Test(description="Verify if the UserDetails API response is shown correctly",groups= {"smoke","api","regression"})
	 public void userDetailsForFDTest() {
		
		given()
		.spec(requestSpecWithAuth(FD))
        .when()
        .get("userdetails")
        .then()
       .spec(responseSpec_OK())
        .body(matchesJsonSchemaInClasspath("ResponseSchema/UserDetailsAPIForFDResponseSchema.json"));
	 }

}
