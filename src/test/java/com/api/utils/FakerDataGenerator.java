package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {
	private static Faker faker= new Faker(new Locale("en-IND"));
	private final static String COUNTRY="India";
	private final static Random RANDOM= new Random();
	private final static int MST_SERVICE_LOCATION_ID=0;
	private final static int MST_PLATFORM_ID=2;
	private final static int MST_WARRANTY_STATUS_ID=1;
	private final static int MST_OEM_ID=1;
	private final static int PRODUCT_ID=1;
	private final static int MST_MODEL_ID=1;
	private final static int validProblemsId[] = {1,2,3,4,5,6,7,8,9,10,11,12,15,16,17,19,20,22,24,26,27,28,29};
	private FakerDataGenerator() {
		
	}
	
	public static CreateJobPayload generateFakeCreateJobData() {
		
		Customer customer= generateFakeCustomerData();
		CustomerAddress customerAddress= generateFakeCustomerAddress();
		CustomerProduct customerProduct=generateFakeCustomerProduct();
		List<Problems> problemsList=generateFakeProblems();
		return new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
		
	}
	
public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
		List<CreateJobPayload> payloadList= new ArrayList<CreateJobPayload>();
		for(int i=1;i<=count;i++) {
			Customer customer= generateFakeCustomerData();
			CustomerAddress customerAddress= generateFakeCustomerAddress();
			CustomerProduct customerProduct=generateFakeCustomerProduct();
			List<Problems> problemsList=generateFakeProblems();
			CreateJobPayload payload=new  CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
			payloadList.add(payload);
		}
		
		return payloadList.iterator();
		
		
	}

	private static List<Problems> generateFakeProblems() {
		int count=RANDOM.nextInt(3)+1;
		int randomIndex;
		String fakeRemark;
		Problems problems;
		List<Problems> problemList=new ArrayList<Problems>();
		//Generating a random Problem ID and adding it to the list
		for(int i=1;i<=count;i++) {
			randomIndex= RANDOM.nextInt(validProblemsId.length);
			fakeRemark=faker.lorem().sentence(4);
			problems=new Problems(validProblemsId[randomIndex], fakeRemark);
			problemList.add(problems);
			
		}
		return problemList;
		
		 
	}

	private static CustomerProduct generateFakeCustomerProduct() {
		String dop=DateTimeUtility.daysAgo(10);
		String imeiSerialNumber=faker.numerify("##############");
		String popUrl=faker.internet().url();
		
		return new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber, popUrl, PRODUCT_ID, MST_MODEL_ID);
		 
	}

	private static CustomerAddress generateFakeCustomerAddress() {
		String flat_Number=faker.numerify("####");
		String apartmentName=faker.address().streetName();
		String streetName=faker.address().streetName();
		String landMark=faker.address().streetName();
		String area=faker.address().streetName();
		String pinCode=faker.numerify("#####");
		String state=faker.address().state();
		return new CustomerAddress(flat_Number, apartmentName,streetName , landMark, area, pinCode, COUNTRY,state);
		 
	}

	private static Customer generateFakeCustomerData() {
		String fName=faker.name().firstName();
		String lName=faker.name().lastName();
		String mobileNumber=faker.numerify("98########");
		String altMobileNumber=faker.numerify("98########");
		String customerEmailAddress=fName+"."+lName+"@gmail.com";
		String altCustomerEmailAddress=faker.internet().emailAddress();
		return new Customer(fName, lName, mobileNumber, altMobileNumber, customerEmailAddress, altCustomerEmailAddress);
		 
	}

}
