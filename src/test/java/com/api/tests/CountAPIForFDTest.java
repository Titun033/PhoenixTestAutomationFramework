package com.api.tests;

import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;

import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPIForFDTest {
	
	@Test
	public void countAPIForFDTest() {
		Header header= new Header("Authorization",getToken(Role.FD));
		
		given()
        .baseUri(getProperty("BASE_URI"))
        .contentType(JSON)
        .header(header)
        .log().uri()
        .log().method()
        .log().headers()
        .when()
        .get("/dashboard/count")
        .then()
        .log().all()
        .statusCode(200)
        .time(lessThan(1500L))
        .body("message",Matchers.equalTo("Success"))
        .body("data", notNullValue())
        .body("data.size()", equalTo(3))
        .body("data.count", everyItem(greaterThanOrEqualTo(0)))
        .body("data.label",everyItem(not(blankOrNullString())))
        .body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/CountAPIForFDResponseSchema.json"));
		
	
        
	}
	
	@Test
	public void countAPITest_MissingAuthToken() {
           
		
		given()
        .baseUri(getProperty("BASE_URI"))
        .log().uri()
        .log().method()
        .log().headers()
        .when()
        .get("/dashboard/count")
        .then()
        .log().all()
        .statusCode(401);
		
		
	}

}
