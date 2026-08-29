package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {
	private final static String COUNTRY="India";
	public static void main(String[] args) {
		
		//Create a Fake CreateJobAPI Request Payload
		Faker faker= new Faker(new Locale("en-IND"));  //Helps in creating India Specific Fake Data
		
		String fName=faker.name().firstName();
		String lName=faker.name().lastName();
		String mobileNumber=faker.numerify("98########");
		String altMobileNumber=faker.numerify("98########");
		String customerEmailAddress=fName+"."+lName+"@gmail.com";
		String altCustomerEmailAddress=faker.internet().emailAddress();
		Customer customer= new Customer(fName, lName, mobileNumber, altMobileNumber, customerEmailAddress, altCustomerEmailAddress);
		System.out.println(customer);
		
		
		String flat_Number=faker.numerify("####");
		String apartmentName=faker.address().streetName();
		String streetName=faker.address().streetName();
		String landMark=faker.address().streetName();
		String area=faker.address().streetName();
		String pinCode=faker.numerify("#####");
		String state=faker.address().state();
		CustomerAddress customerAddress= new CustomerAddress(flat_Number, apartmentName,streetName , landMark, area, pinCode, COUNTRY,state);
		System.out.println(customerAddress);
		
		String dop=DateTimeUtility.daysAgo(10);
		String imeiSerialNumber=faker.numerify("##############");
		String popUrl=faker.internet().url();
		
		CustomerProduct customerProduct= new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber, popUrl, 1, 1);
		System.out.println(customerProduct);
		
		
		Random random= new Random();
		int problemId=(random.nextInt(26)+1);
		String fakeRemark=faker.lorem().sentence(4);
		Problems problems=new Problems(problemId, fakeRemark);
		List<Problems> problemList=new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload createJobPayload= new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		System.out.println(createJobPayload);
		
		
		
		
		
	}

}
