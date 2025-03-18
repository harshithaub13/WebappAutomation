package com.mishipay.webshopper.utils;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

import org.openqa.selenium.WebDriver;

public class PaymentUtil {
	
	public static WebDriver driver; // so that we can use driver in child class eg: storelocator class 
	public static Properties prop1;
	
		static {
	        try (FileInputStream fis = new FileInputStream("/Users/harshitha/Desktop/WebappAutomation/WebShopperAutomation/src/main/resources/payments.properties")) {
	        	prop1 = new Properties();
	        	prop1.load(fis);
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Failed to load config.properties file");
	        }
	    }

	    public static String getProperty(String key) {
	        return prop1.getProperty(key);
	    }
}
	


