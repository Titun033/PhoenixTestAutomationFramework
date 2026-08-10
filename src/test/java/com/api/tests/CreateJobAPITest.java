package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;
import com.pojo.CreateJobPayload;
import com.pojo.Customer;
import com.pojo.CustomerAddress;
import com.pojo.CustomerProduct;
import com.pojo.Problems;

import io.restassured.http.ContentType;

public class CreateJobAPITest {
	
	
	

	@Test
	public void createJobAPITest() {
		Customer customer= new Customer("Titun", "Chakraborty", "9908563210", "", "titun_ch@rediffmail.com", "");
		CustomerAddress customerAddress= new CustomerAddress("001", "Shawn Apartments", "Vasant Vihar", "", "Chinchpokli", "223648", "India", "MH");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "13963160626693", "13963160626693", "13963160626693", "2025-04-06T18:30:00.000Z", 1, 1);
		Problems problems= new Problems(1, "Battery Issue");
		Problems[] problemsArray= new Problems[1];
		problemsArray[0]=problems;
	    //Create the CreateJobPayLoad Object
		CreateJobPayload createJobPayload= new CreateJobPayload(0,2,1,1,customer,customerAddress,customerProduct,problemsArray);
	        given()
	       .spec(SpecUtil.requestSpecWithAuth(Role.FD,createJobPayload))
	      .when()
	      .post("/job/create")
	      .then()
	      .spec(SpecUtil.responseSpec_OK());

	;
}}
