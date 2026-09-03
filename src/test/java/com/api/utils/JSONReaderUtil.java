package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.api.request.model.UserCredentials;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONReaderUtil {

	public static <T> Iterator<T> loadJSON(String fileName, Class<T[]> clazz)  {
		
	     InputStream jsonStream=Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
	     
	     ObjectMapper objectMapper=new ObjectMapper();
	     T[] classArray;
	     List<T> classList = null;
		try {
			classArray = objectMapper.readValue(jsonStream, clazz);
			classList =Arrays.asList(classArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	     
		
	    return  classList.iterator();

	}

}
