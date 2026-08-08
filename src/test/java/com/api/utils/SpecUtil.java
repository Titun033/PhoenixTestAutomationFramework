package com.api.utils;

import static com.api.constant.Role.FD;
import static com.api.utils.ConfigManager.getProperty;

import org.hamcrest.Matchers;

import com.api.constant.Role;
import com.pojo.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	
	//static methods
	
	//GET -DEL
	public static RequestSpecification reqestSpec() {
		//take care of common methods
		RequestSpecification request=new RequestSpecBuilder()
		.setBaseUri(getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.BODY)
		.build();
		return request;
	}
	
	//POST- PUT-DELETE
	public static RequestSpecification reqestSpec(Object payload) {
		//take care of common methods
		return new RequestSpecBuilder()
		.setBaseUri(getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.setBody(payload)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.BODY)
		.build();
		
	}
	
	public static RequestSpecification requestSpecWithAuth(Role role) {
		return new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.BODY)
				.build();
		
	}
	
	public static ResponseSpecification responseSpec_OK() {
		return new ResponseSpecBuilder()
		        .expectContentType(ContentType.JSON)
		        .expectStatusCode(200)
		        .expectResponseTime(Matchers.lessThan(1000L))
		        .log(LogDetail.ALL)
		        .build();
		
	}
	
	public static ResponseSpecification responseSpec_JSON(int statusCode) {
		return new ResponseSpecBuilder()
		        .expectContentType(ContentType.JSON)
		        .expectStatusCode(statusCode)
		        .expectResponseTime(Matchers.lessThan(1000L))
		        .log(LogDetail.ALL)
		        .build();
		
	}
	
	public static ResponseSpecification responseSpec(int statusCode) {
		return new ResponseSpecBuilder()
		        .expectStatusCode(statusCode)
		        .expectResponseTime(Matchers.lessThan(1000L))
		        .log(LogDetail.ALL)
		        .build();
		
	}

}
