package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import static com.api.utils.SpecUtil.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {
	
	@Test(description="Verifying if the Master API is giving correct response",groups= {"smoke","api","regression"})
	public void masterAPITest() {
	
		 given()
		 .spec(requestSpecWithAuth(FD))
         .post("/master")
         .then()
         .spec(responseSpec_OK())
         .body("message", equalTo("Success"))
         .body("data", notNullValue())
         .body("data",hasKey("mst_oem")) 
         .body("data",hasKey("mst_model"))
         .body("data",hasKey("mst_action_status"))
         .body("data",hasKey("mst_warrenty_status"))
         .body("data",hasKey("mst_platform"))
         .body("data",hasKey("mst_product"))
         .body("data",hasKey("mst_role"))
         .body("data",hasKey("mst_service_location"))
         .body("data",hasKey("mst_problem"))
         .body("data",hasKey("map_fst_pincode"))
         .body("$", hasKey("message"))
         .body("data.mst_oem.size()", greaterThan(0))
         .body("data.mst_oem.id", everyItem(notNullValue()))
         .body("data.mst_oem.name", everyItem(notNullValue()))
         .body(matchesJsonSchemaInClasspath("ResponseSchema/MasterAPIResponseSchema.json"));
	}
	
	@Test(description="Verifying if the Master API is giving correct response for invalid Token",groups= {"smoke","api","negative","regression"})
	public void invalidToken() {
		given()
		.spec(reqestSpec())
         .when()
         .post("/master")
         .then()
         .spec(responseSpec(401));
		
	}

}
