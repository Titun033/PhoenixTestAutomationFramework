package com.database;

import java.sql.SQLException;

public class DBManagerRunner {

	public static void main(String[] args) throws SQLException {
		
		long startTime= System.currentTimeMillis();
		for(int i=0;i<15;i++) {
			DatabaseManager.createConnection();
	
		}
		
		long endTime= System.currentTimeMillis();
		
		System.out.println("Duration: "+(endTime-startTime)+" ms");
		

	}

}
