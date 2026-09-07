package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;

public class DatabaseManager {

	private static final String DB_URL = ConfigManager.getProperty("DB_URL");
	private static final String DB_USERNAME = ConfigManager.getProperty("DB_USER_NAME");
	private static final String DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
	private static  volatile Connection dbConnection;

	private DatabaseManager() {

	}

	public static void createConnection() throws SQLException {
		if (dbConnection == null) {
			synchronized (DatabaseManager.class) {
				if (dbConnection == null) {
					dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
					System.out.println(dbConnection);
				}
			}
		}
	}
	
	
	
	

}
