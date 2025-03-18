package com.mishipay.webshopper.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;



public class TestUtil {

	public static long Implicit_wait = 200;
	public static long Explicit_Wait=60;
	
	
	//wait element to load the page
	public static void waitForPageToLoad(WebDriver driver) {
		new WebDriverWait(driver, Duration.ofSeconds(6000)).until(
	            webDriver -> ((JavascriptExecutor) webDriver)
	                .executeScript("return document.readyState").equals("complete"));
	    }
	    
	
	public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Explicit_Wait));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
	    
	    public static void handleCookies(WebDriver driver) {

	    	try {
	            // Locate the "OK" button using its text or unique locator
	            WebElement okButton = driver.findElement(By.xpath("//span[text()='Okay']"));
	            okButton.click();
	            System.out.println("Cookies dialog dismissed.");
	        } catch (Exception e) {
	            System.out.println("No cookies dialog found.");
	        }
	    }
	    
	   
}	
	
	

