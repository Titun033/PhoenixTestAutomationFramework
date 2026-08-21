package com.api.tests;

import static com.api.utils.DateTimeUtility.daysAgo;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;

public class CreateJobAPITest {

	CreateJobPayload createJobPayload;

	@BeforeMethod(description="Setting up the CreateJob API Request Payload")
	public void setUp() {
		Customer customer = new Customer("Titun", "Chakraborty", "9908563210", "", "titun_ch@rediffmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("001", "Shawn Apartments", "Vasant Vihar", "",
				"Chinchpokli", "223648", "India", "MH");
		CustomerProduct customerProduct = new CustomerProduct(daysAgo(10), "11961060626249", "11961060626249",
				"11961060626249", daysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		// Create the CreateJobPayLoad Object
		createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(),
				Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer,
				customerAddress, customerProduct, problemList);

	}

	@Test(description = "Verify if the Create API is able to create InWarranty Job", groups = { "smoke", "api",
			"regression" })
	public void createJobAPITest() {

		given().spec(requestSpecWithAuth(Role.FD, createJobPayload)).when().post("/job/create").then()
				.spec(responseSpec_OK())
				.body(matchesJsonSchemaInClasspath("ResponseSchema/CreateJobAPIJSONResponseSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"));

	}
}
