package com.database.dao;

import org.testng.Assert;

import com.api.request.model.Customer;
import com.database.model.CustomerDBModel;

public class DAODemoRunner2 {

	public static void main(String[] args) throws Exception {
		CustomerDBModel customerDBModel=CustomerDao.getCustomerInfo();
		System.out.println(customerDBModel);
		Customer customer = new Customer("Titun", "Chakraborty", "9908563210", "", "titun_ch@rediffmail.com", "");
		System.out.println(customer.first_name());
		Assert.assertEquals(customerDBModel.getFirst_name(), customer.first_name());

	}

}
