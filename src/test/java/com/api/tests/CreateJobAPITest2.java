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
import java.util.Locale;
import java.util.Random;

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
import com.api.utils.DateTimeUtility;
import com.github.javafaker.Faker;

public class CreateJobAPITest2 {

	private final static String COUNTRY="India";
	CreateJobPayload createJobPayload;

	@BeforeMethod(description = "Setting up the CreateJob API Request Payload")
	public void setUp() {
		Faker faker = new Faker(new Locale("en-IND")); // Helps in creating India Specific Fake Data

		String fName = faker.name().firstName();
		String lName = faker.name().lastName();
		String mobileNumber = faker.numerify("98########");
		String altMobileNumber = faker.numerify("98########");
		String customerEmailAddress = fName + "." + lName + "@gmail.com";
		String altCustomerEmailAddress = faker.internet().emailAddress();
		Customer customer = new Customer(fName, lName, mobileNumber, altMobileNumber, customerEmailAddress,
				altCustomerEmailAddress);
		System.out.println(customer);

		String flat_Number = faker.numerify("####");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landMark = faker.address().streetName();
		String area = faker.address().streetName();
		String pinCode = faker.numerify("#####");
		String state = faker.address().state();
		CustomerAddress customerAddress = new CustomerAddress(flat_Number, apartmentName, streetName, landMark, area,
				pinCode, COUNTRY, state);
		System.out.println(customerAddress);

		String dop = DateTimeUtility.daysAgo(10);
		String imeiSerialNumber = faker.numerify("##############");
		String popUrl = faker.internet().url();

		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,
				popUrl, 1, 1);
		
		Random random= new Random();
		int problemId=(random.nextInt(26)+1);
		String fakeRemark=faker.lorem().sentence(4);
		Problems problems=new Problems(problemId, fakeRemark);
		List<Problems> problemList=new ArrayList<Problems>();
		problemList.add(problems);
		
		 createJobPayload= new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);

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
