package com.database.model;

public class CustomerDBModelRunner {
	
	public static void main(String[] args) {
		CustomerDBModel dbModel= new CustomerDBModel("Titun","Chakraborty","9804881067","9007359531","titunch@rediffmail.com");
		System.out.println(dbModel);
		
		CustomerDBModel customer2= new CustomerDBModel();
		System.out.println(customer2);
	}
	

}
