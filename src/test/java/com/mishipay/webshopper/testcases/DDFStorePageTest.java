/*package com.mishipay.webshopper.testcases;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mishipay.webshopper.base.TestBase;
import com.mishipay.webshopper.pages.DDFStorePage;
import com.mishipay.webshopper.utils.LoggerHelper;
import com.mishipay.webshopper.utils.TestUtil;

public class DDFStorePageTest extends TestBase {
	
	   DDFStorePage ddfStorePage;
	   private String storeId;
	   private static final Logger log = LoggerHelper.getLogger(DDFStorePageTest.class);
	   
	   
	   
	   @DataProvider(name = "DDFStoreData")
   	   public Object[][] storeData() {
   		String storeId = prop.getProperty("DDF.store.id"); // Retrieve the DDF Store ID from config file
   		System.out.println("Loaded Store ID: " + storeId); // Debugging

   		return new Object[][] {
       
   			{storeId, "DDF"}
   			};
   	     
	   	}
	   
	   @BeforeMethod(alwaysRun=true)
   	  	public void setUp(Object[] data) {
		   this.storeId = (String) data[0];
		   System.out.println("Store ID in setUp: " + this.storeId); // Debugging

       // Initialise WebDriver and navigate to store-specific URL
		   initialisation(storeId);
		   ddfStorePage = new DDFStorePage(driver);
       
		   TestUtil.handleCookies(driver); // accepts cookies
       
   		}
	   
	    @Test(dataProvider = "DDFStoreData", priority = 1)
	    public void validateDDFStoreLogo(String storeId, String storeName) {
		   log.info("Starting DDF Test");

		   try {
			   log.debug("Performing the validation of DDF store logo");
			   System.out.println("Store ID: " + storeId + ", Store Name: " + storeName); // Debugging
			   boolean isLogoDisplayed = ddfStorePage.DDF_Logo();
			   Assert.assertTrue(isLogoDisplayed, "DDF logo is not displayed");	    
			   log.info("DDF Logo validation test executed successfully");
		   		} catch (Exception e) {	   
		   			log.error("Error occurred during FTC Logo validation ", e);
		   		}
	    }    
	   
	    @Test(dataProvider = "DDFStoreData", priority = 2)
	    public void testBoardingPassInjection(String storeId, String storeName) {
	       log.info("Boardingpass screen loaded");
	       try {
	    	  
	    	   log.debug("clicking on upload screenshot button");
	    	   ddfStorePage.uploadBoardingPassScreenshot();	    	  	    	   
	    	   log.info("upload button clicked successfully");
	    	   ddfStorePage.uploadBoardingPassScreenshot2();
	    	   
	    	   log.info("second upload button clicked successfully");
	    	   Thread.sleep(2000);

	       		} catch (Exception e) {	   
	   				log.error("Error in uploading the boarding pass ", e);
	   				}
	       
	    	}
	    
	    @AfterMethod(alwaysRun = true)
    	public void tearDown() {
    	    log.info("Resetting browser state");
    	    driver.manage().deleteAllCookies();
    	    driver.navigate().refresh();
    	    driver.quit();

    	}

       
}*/
