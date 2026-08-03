package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() {
		Header header= new Header("Authorization",AuthTokenProvider.getToken(FD));
		 given()
		 .baseUri(ConfigManager.getProperty("BASE_URI"))
		 .contentType(ContentType.JSON)
		 .header(header)
		 .log().uri()
		 .log().body()
         .when()
         .post("/master")
         .then()
         .log().all()
         .statusCode(200)
         .time(lessThan(1000L))
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
         .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponseSchema/MasterAPIResponseSchema.json"));
	}
	
	@Test
	public void invalidToken() {
		Header header= new Header("Authorization","abcd");
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		 .contentType(ContentType.JSON)
		 .header(header)
		 .log().uri()
		 .log().body()
         .when()
         .post("/master")
         .then()
         .log().all()
         .statusCode(403);
		
	}

}
