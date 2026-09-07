package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

	public static void main(String[] args) throws SQLException {
		//Step1: Establish the Connection to the Phoenix DataBase
		Connection dbConnection=DriverManager.getConnection("jdbc:mysql://64.227.160.186 :3306/SR_DEV", "srdev_ro_automation", "Srdev@123");
		Statement statement=dbConnection.createStatement();
		ResultSet resultSet=statement.executeQuery("SELECT first_name, last_name, mobile_number  FROM tr_customer");
		
		while(resultSet.next()) {
			String fname=resultSet.getString("first_name");
			String lname=resultSet.getString("last_name");
			String mobile=resultSet.getString("mobile_number");
			System.out.println(fname+"|"+lname+"|"+mobile);
		}
	}

}
