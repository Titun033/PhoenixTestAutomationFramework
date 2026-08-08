package com.api.tests;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static com.api.constant.Role.*;
import static com.api.utils.SpecUtil.*;


import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPIForFDTest {
	
	@Test
	public void countAPIForFDTest() {
		
		
		given()
        .spec(requestSpecWithAuth(FD))
        .when()
        .get("/dashboard/count")
        .then()
        .spec(responseSpec_OK())
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
		.spec(reqestSpec())
        .when()
        .get("/dashboard/count")
        .then()
        .log().all()
        .statusCode(401);
		
		
	}

}
