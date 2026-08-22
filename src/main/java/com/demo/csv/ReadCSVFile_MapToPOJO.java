package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile_MapToPOJO {

	public static void main(String[] args) throws IOException, CsvException {
		
		InputStream inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/loginCreds.csv");

		InputStreamReader streamReader=new InputStreamReader(inputStream);
		CSVReader csvReader= new CSVReader(streamReader);
		
		//Code to Map the CSV to POJO
		
		CsvToBean<UserPoJo> csvToBean = new CsvToBeanBuilder(csvReader)
				                                                   .withType(UserPoJo.class)
				                                                   .withIgnoreEmptyLine(true)
				                                                   .build();
		
		List<UserPoJo> userList=csvToBean.parse();
		System.out.println(userList.get(0).getUsername());
		
		
	 
		
		
		
		

	}

}
