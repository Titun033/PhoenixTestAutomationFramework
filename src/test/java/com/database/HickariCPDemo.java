package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HickariCPDemo {

	public static void main(String[] args) throws SQLException {
		
		HikariConfig hikariconfig= new HikariConfig();
		hikariconfig.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
		hikariconfig.setUsername(ConfigManager.getProperty("DB_USER_NAME"));
		hikariconfig.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
		hikariconfig.setMaximumPoolSize(10);
		hikariconfig.setConnectionTimeout(10000);
		hikariconfig.setIdleTimeout(10000);
		hikariconfig.setMaxLifetime(1800000);
		hikariconfig.setPoolName("Phoenix Test Automation Framework Pool");
		
		HikariDataSource ds= new HikariDataSource(hikariconfig);
		Connection conn=ds.getConnection();
		System.out.println(conn);
		
		Statement statement= conn.createStatement();
		
		ResultSet resultset= statement.executeQuery("SELECT first_name, last_name, mobile_number  FROM tr_customer");
		
//		while(resultset.next()) {
//			String fname=resultset.getString("first_name");
//			String lname=resultset.getString("last_name");
//			String mobile=resultset.getString("mobile_number");
//			System.out.println(fname+"|"+lname+"|"+mobile);
//		}
		
		ds.close();

	}

}
