package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager {

	private static final String DB_URL = ConfigManager.getProperty("DB_URL");
	private static final String DB_USERNAME = ConfigManager.getProperty("DB_USER_NAME");
	private static final String DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
	private static final int MAXIMUM_POOL_SIZE = Integer.parseInt(ConfigManager.getProperty("MAXIMUM_POOL_SIZE"));
	private static final int CONNECTION_TIMEOUT_IN_SECS = Integer
			.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SECS"));
	private static final int MINIMUM_IDLE_COUNT = Integer.parseInt(ConfigManager.getProperty("MINIMUM_IDLE_COUNT"));
	private static final long MAX_LIFE_TIME_IN_MINS = Integer
			.parseInt(ConfigManager.getProperty("MAX_LIFE_TIME_IN_MINS"));
	private static final String HIKARI_CP_POOLNAME = ConfigManager.getProperty("HIKARI_CP_POOLNAME");
	// private static volatile Connection dbConnection;
	private static volatile HikariDataSource hikariDataSource;

	private DatabaseManager() {

	}

	private static void initializePool() throws SQLException {
		if (hikariDataSource == null) {
			synchronized (DatabaseManager.class) {
				if (hikariDataSource == null) {
					HikariConfig hikariconfig = new HikariConfig();
					hikariconfig.setJdbcUrl(DB_URL);
					hikariconfig.setUsername(DB_USERNAME);
					hikariconfig.setPassword(DB_PASSWORD);
					hikariconfig.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
					hikariconfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SECS * 100);
					hikariconfig.setIdleTimeout(MINIMUM_IDLE_COUNT * 100);
					hikariconfig.setMaxLifetime(MAX_LIFE_TIME_IN_MINS * 60 * 100);
					hikariconfig.setPoolName(HIKARI_CP_POOLNAME);
					hikariDataSource = new HikariDataSource(hikariconfig);
				}
			}
		}
	}

	public static Connection getConnection() throws Exception {
		Connection connection = null;
		if (hikariDataSource == null) {
			initializePool(); // Automatic Initialization of HikariDataSource
		}
		
		if (hikariDataSource.isClosed()) {
			throw new Exception("HIKARI DATA SOURCE IS CLOSED");
		}
		connection = hikariDataSource.getConnection();

		return connection;

	}

}
