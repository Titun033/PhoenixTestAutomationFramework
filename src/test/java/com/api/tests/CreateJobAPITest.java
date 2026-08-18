package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.SpecUtil;

public class CreateJobAPITest {

	@Test
	public void createJobAPITest() {
		Customer customer= new Customer("Titun", "Chakraborty", "9908563210", "", "titun_ch@rediffmail.com", "");
		CustomerAddress customerAddress= new CustomerAddress("001", "Shawn Apartments", "Vasant Vihar", "", "Chinchpokli", "223648", "India", "MH");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "11961060626290", "11961060626290", "11961060626290", "2025-04-06T18:30:00.000Z", 1, 1);
		Problems problems= new Problems(1, "Battery Issue");
		List<Problems> problemList= new ArrayList<Problems>();
		problemList.add(problems);
	    //Create the CreateJobPayLoad Object
		CreateJobPayload createJobPayload= new CreateJobPayload(0,2,1,1,customer,customerAddress,customerProduct,problemList);
	        given()
	       .spec(SpecUtil.requestSpecWithAuth(Role.FD,createJobPayload))
	      .when()
	      .post("/job/create")
	      .then()
	      .spec(SpecUtil.responseSpec_OK())
	      .body(matchesJsonSchemaInClasspath("ResponseSchema/CreateJobAPIJSONResponseSchema.json"))
	      .body("message", equalTo("Job created successfully. "))
	      .body("data.mst_service_location_id", equalTo(1))
	      .body("data.job_number",startsWith("JOB_"))
	      ;
	        
}}
