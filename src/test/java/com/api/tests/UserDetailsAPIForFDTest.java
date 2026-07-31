package com.api.tests;

import static com.api.utils.ConfigManager.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.ANY;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.http.Header;

public class UserDetailsAPIForFDTest {
	
	@Test
	 public void userDetailsForFDTest() {
		Header header= new Header("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiZmlyc3RfbmFtZSI6ImZkIiwibGFzdF9uYW1lIjoiZmQiLCJsb2dpbl9pZCI6ImlhbWZkIiwibW9iaWxlX251bWJlciI6Ijg4OTk3NzY2NTUiLCJlbWFpbF9pZCI6Im1hcmtAZ21haWwuY29tIiwicGFzc3dvcmQiOiI1ZjRkY2MzYjVhYTc2NWQ2MWQ4MzI3ZGViODgyY2Y5OSIsInJlc2V0X3Bhc3N3b3JkX2RhdGUiOm51bGwsImxvY2tfc3RhdHVzIjowLCJpc19hY3RpdmUiOjEsIm1zdF9yb2xlX2lkIjo1LCJtc3Rfc2VydmljZV9sb2NhdGlvbl9pZCI6MSwiY3JlYXRlZF9hdCI6IjIwMjEtMTEtMDNUMDg6MDY6MjMuMDAwWiIsIm1vZGlmaWVkX2F0IjoiMjAyMS0xMS0wM1QwODowNjoyMy4wMDBaIiwicm9sZV9uYW1lIjoiRnJvbnREZXNrIiwic2VydmljZV9sb2NhdGlvbiI6IlNlcnZpY2UgQ2VudGVyIEEiLCJpYXQiOjE3ODUyOTUzMTR9.1ZmUnTPDhC9xd8PLewSNLWDhMMKAZLKmYRD6n5esgtE");
		
		given()
        .baseUri(readPropertiesFile("BASE_URI"))
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
