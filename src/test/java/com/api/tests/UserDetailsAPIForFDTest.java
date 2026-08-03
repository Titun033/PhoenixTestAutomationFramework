package com.api.tests;

import static com.api.utils.ConfigManager.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.ANY;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.Header;

public class UserDetailsAPIForFDTest {
	
	@Test
	 public void userDetailsForFDTest() {
		Header header= new Header("Authorization", getToken(FD));
		
		given()
        .baseUri(getProperty("BASE_URI"))
        .contentType(JSON)
        .accept(ANY)
        .header(header)
        .when()
        .get("userdetails")
        .then()
        .statusCode(200)
        .body("message", equalTo("Success"))
        .log().all()
        .time(lessThan(1500L))
        .body(matchesJsonSchemaInClasspath("ResponseSchema/UserDetailsAPIForFDResponseSchema.json"));
	 }

}
