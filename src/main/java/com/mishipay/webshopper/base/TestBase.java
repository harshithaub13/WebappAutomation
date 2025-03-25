package com.mishipay.webshopper.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import com.mishipay.webshopper.utils.TestUtil;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;


public class TestBase {

	public static WebDriver driver; // so that we can use driver in child class eg: storelocator class 
	public static Properties prop;
    private WebDriverWait wait;
	
	//creating a constructor
	public TestBase()
	{
		try {
			prop = new Properties();

            FileInputStream fis = new FileInputStream("src/main/resources/environments/config-test.properties");
					prop.load(fis);
			        System.out.println("Loaded Properties: " + prop);

		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialisation(String storeId)
	{
	
        
        String browserName = prop.getProperty("browser");
        String testURL = prop.getProperty("test.url");
        //String storeId = prop.getProperty("store.id"); //remove later if it works
        
        if (browserName.equals("chrome")) {
            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
            
          
            // Configure Chrome to automatically allow location permissions
            ChromeOptions options = new ChromeOptions();
            

            Map<String, Object> prefs = new HashMap<>();
            Map<String, Object> profile = new HashMap<>();
            Map<String, Object> contentSettings = new HashMap<>();
            
            contentSettings.put("geolocation", 1);  // 1 to allow, 2 to block- accepts location
            profile.put("managed_default_content_settings", contentSettings);
            prefs.put("profile", profile);
            options.setExperimentalOption("prefs", prefs);

            
         // Bypass security restrictions that may block payment form
            options.addArguments("--disable-blink-features=AutomationControlled"); // Prevent bot detection
            options.addArguments("--allow-running-insecure-content"); // Allow HTTP inside HTTPS
            options.addArguments("--ignore-certificate-errors"); // Ignore SSL errors
            options.addArguments("--disable-web-security"); // Disable CSP blocking
            options.addArguments("--disable-site-isolation-trials"); // Avoid site isolation blocking
            options.addArguments("--disable-popup-blocking"); // Ensure popups don't block fields
            options.addArguments("--incognito"); // Run in incognito mode to prevent caching issues
            
            
            
            
            
            options.addArguments("--use-fake-ui-for-media-stream"); // Automatically accept media (camera/microphone) permissions
            
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.Implicit_wait)); 

            
            //Navigate to the store specific URL 
            //String fullURL = testURL + storeId;
            String fullURL = prop.getProperty("test.url") + storeId;

            driver.get(fullURL);
            
           
        } else {
            // Handle unsupported browsers or throw an exception
            throw new RuntimeException("Unsupported browser: " + browserName);
        }
	} 
        public WebDriver getDriver() {
            return driver;

    }

    // Helper methods
    protected boolean isVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)) != null;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isPresent(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator)) != null;
        } catch (Exception e) {
            return false;
        }
    }
        
}
	   
	  
	

       
        
        
 
	


