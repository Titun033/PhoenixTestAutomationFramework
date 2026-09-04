package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentials;

public class ExcelReaderUtility {
    
	public static Iterator<UserCredentials> loadExcelData(String fileName) {
		
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		XSSFWorkbook xssfwb = null;
		
		try {
			xssfwb = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XSSFSheet sheet= xssfwb.getSheetAt(0);
		
		Iterator<Row> rowIterator=sheet.rowIterator();
		
		rowIterator.next();
		List<UserCredentials> credsList= new ArrayList<UserCredentials>();
		while(rowIterator.hasNext()) {
			Row myRow=rowIterator.next();
			UserCredentials userCreds=new UserCredentials(myRow.getCell(0).toString().trim(),myRow.getCell(1).toString().trim());
			System.out.println(userCreds);
			credsList.add(userCreds);
		}
		
		return credsList.iterator();

	}

}
