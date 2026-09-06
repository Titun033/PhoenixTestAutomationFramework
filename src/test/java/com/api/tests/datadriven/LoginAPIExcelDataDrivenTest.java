package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.reqestSpec;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;

public class LoginAPIExcelDataDrivenTest {
	
	
	@Test(description="Verify if the login API is working for the IamFD User",groups= {"api","regression","datadriven"},
			 dataProviderClass=com.dataproviders.DataProviderUtils.class,
			 dataProvider="LoginAPIExcelDataProvider"
	
			
			)
	public void loginAPITest(UserBean userBean) {
		
		given() 
		.spec(reqestSpec(userBean))
		.when()
		.post("/login")
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("ResponseSchema/LoginAPIResponseSchema.json"))
		.body("message", equalTo("Success"));
	}

}
