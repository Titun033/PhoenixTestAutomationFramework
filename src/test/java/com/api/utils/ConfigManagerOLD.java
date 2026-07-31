package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLD {
	 private static final Properties properties= new Properties();
	 //Reading the file only once
	 private ConfigManagerOLD() {
		 //Private constructor!!!
	 }
	 static {
		 File propFile= new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
	         
	      try {
	    	  FileReader propertiesReader= new FileReader(propFile);
			      properties.load(propertiesReader);
		} catch (IOException e) {
			System.err.println("Exception found due to "+e.getMessage());
			e.printStackTrace();
		}
		 
		 
	 }
	//Read the Properties File from resources
	      public static String readPropertiesFile(String propertyName) {
	    	      
		    return properties.getProperty(propertyName);
		     
		      
	      }
	      

}
