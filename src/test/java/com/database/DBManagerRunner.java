package com.database;

public class DBManagerRunner {

	public static void main(String[] args) throws Exception {
		
		long startTime= System.currentTimeMillis();
		for(int i=0;i<10;i++) {
			System.out.println(DatabaseManager.getConnection());
	
		}
		
		long endTime= System.currentTimeMillis();
		
		System.out.println("Duration: "+(endTime-startTime)+" ms");
		

	}

}
