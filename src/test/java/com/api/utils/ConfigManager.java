package com.api.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class ConfigManager {
	 private static final Properties properties= new Properties();
	 private static String path= "config/config.properties";
	 private static String env;
	 
	 //Reading the file only once
	 private ConfigManager() {
		 //Private constructor!!!
	 }
	 
	 static {
		env= System.getProperty("env","qa");
		env=env.toLowerCase().trim();
		System.out.println("Running tests in env: "+env);
		
		switch(env) {
		
		     case "qa" -> path="config/config.qa.properties";
			
		     case "dev" -> path="config/config.dev.properties";
			
		     case "uat" -> path="config/config.uat.properties";
			
		     default -> path="config/config.qa.properties";
		
		}
		InputStream inputPropPath= Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if(inputPropPath==null) {
			throw new RuntimeException("Cannot find the file at the path"+path);
		}
	      try {
			      properties.load(inputPropPath);
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
