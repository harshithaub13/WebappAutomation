package com.mishipay.webshopper.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
public class ScreenshotUtil {
	public static String captureScreenshot(WebDriver driver, String screenshotName) {
	    try {
	            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            String destPath = "screenshots/" + screenshotName + ".png";
	            File destFile = new File(destPath);
	            Files.createDirectories(destFile.getParentFile().toPath());
	            Files.copy(srcFile.toPath(), destFile.toPath());
	            return destPath;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }	

}
