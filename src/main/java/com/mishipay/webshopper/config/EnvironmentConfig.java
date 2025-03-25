package com.mishipay.webshopper.config;

public class EnvironmentConfig {
	public static boolean isProd() {
		return System.getProperty("environment", "test").equals("prod");
	   }
}
