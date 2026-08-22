package com.demo.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {

	public static void main(String[] args) throws IOException, CsvException {
		//Code to read the CSV File in Java
		 /*
		 **   File csvFile= new File("C:\\Users\\Admin\\eclipse-workspace\\PhoenixTestAutomationFramework\\src\\main\\resources\\testData\\loginCreds.csv");
        *	    FileReader fr= new FileReader(csvFile);
		 *
		 *
		 *
		 */
		InputStream inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/loginCreds.csv");

		InputStreamReader streamReader=new InputStreamReader(inputStream);
		CSVReader csvReader= new CSVReader(streamReader);
		
		List<String[]> dataList=csvReader.readAll();
		
		for(String[] dataArray:dataList) {
			for(String data:dataArray) {
				System.out.print(data+" ");
			}
			System.out.println();
		}
		

	}

}
