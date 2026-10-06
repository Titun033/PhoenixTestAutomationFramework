package com.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.database.DatabaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {

	private static final String CUSTOMER_DETAIL_QUERY = """
			   SELECT * FROM tr_customer where id=448531
			""";

	public static CustomerDBModel getCustomerInfo() throws Exception {
		Connection conn = DatabaseManager.getConnection();
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(CUSTOMER_DETAIL_QUERY);
		CustomerDBModel customerDBModel = null;
		while (resultSet.next()) {
			customerDBModel = new CustomerDBModel(resultSet.getString("first_name"), resultSet.getString("last_name"),
					resultSet.getString("mobile_number"), resultSet.getString("mobile_number_alt"),
					resultSet.getString("email_id"));
		}
		return customerDBModel;
	}

}
