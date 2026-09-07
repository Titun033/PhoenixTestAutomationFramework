package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.dataproviders.api.bean.CreateJobBean;

public class CreateJobAPIDataDrivenTest {

	@Test(description = "Verify if the Create API is able to create InWarranty Job", groups = { "smoke", "api",
			"regression","datadriven","csv" },
			dataProviderClass=com.dataproviders.DataProviderUtils.class,
			dataProvider="CreateJobAPIExcelDataProvider")
	public void createJobAPITest(CreateJobBean createJobBean) {

		given().spec(requestSpecWithAuth(Role.FD, createJobBean)).when().post("/job/create").then()
				.spec(responseSpec_OK())
				.body(matchesJsonSchemaInClasspath("ResponseSchema/CreateJobAPIJSONResponseSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"));

	}
}
