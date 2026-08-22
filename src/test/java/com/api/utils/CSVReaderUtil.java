package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
	/*
	 * Constructor is private static methods
	 * 
	 * Job: Help me Read the CSV File and map it to a Bean
	 */

	private CSVReaderUtil() {

	}

	public static void loadCSV(String pathOfCSVFile) {

		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);

		InputStreamReader streamReader = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(streamReader);

		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader).
				                                                   withType(UserBean.class)
				                                                   .withIgnoreEmptyLine(true).build();

		List<UserBean> userList = csvToBean.parse();
		System.out.println(userList.get(0).getUsername());

	}

}
