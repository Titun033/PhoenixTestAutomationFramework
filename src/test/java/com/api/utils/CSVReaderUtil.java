package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
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

	public static <T> Iterator<T> loadCSV(String pathOfCSVFile,Class<T> bean) {

		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);

		InputStreamReader streamReader = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(streamReader);

		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader).
				                                                   withType(bean)
				                                                   .withIgnoreEmptyLine(true).build();

		List<T> list = csvToBean.parse();
		return list.iterator();

	}

}
