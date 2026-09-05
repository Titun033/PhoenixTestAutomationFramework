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
import com.dataproviders.api.bean.UserBean;
import com.poiji.bind.Poiji;

public class ExcelReaderUtility {
    
	public static <T> Iterator<T> loadExcelData(String fileName, Class<T> clazz) {
		
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		XSSFWorkbook xssfwb = null;
		
		try {
			xssfwb = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XSSFSheet sheet= xssfwb.getSheetAt(0);
		
		List<T> dataList=Poiji.fromExcel(sheet, clazz);
		return dataList.iterator();
		
		

	}

}
