package com.mishipay.webshopper.testcases;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mishipay.webshopper.base.TestBase;
import com.mishipay.webshopper.pages.FlyingTigerStorePage;
import com.mishipay.webshopper.utils.LoggerHelper;
import com.mishipay.webshopper.utils.TestUtil;

public class FlyingTigerStorePageTest extends TestBase{

	    private StorePageTest storePageTest;
	     FlyingTigerStorePage ftcStorePage;
	    private String storeId;
	    private static final Logger log = LoggerHelper.getLogger(FlyingTigerStorePageTest.class);

	    @DataProvider(name = "FTCStoreData")
	    public Object[][] storeData() {
	        String storeId = prop.getProperty("FTC.store.id"); // Retrieve from config
	        return new Object[][] { { storeId, "FTC" } };
	    }
	    
	    @BeforeClass(alwaysRun=true)
	    public void setUp() {
	        this.storeId = prop.getProperty("FTC.store.id");
	        System.out.println("Store ID in setUp: " + this.storeId); // Debugging

	        // Initialise WebDriver and navigate to store-specific URL
	        initialisation(storeId);
	        ftcStorePage = new FlyingTigerStorePage(driver);
	        storePageTest = new StorePageTest(driver);

	        TestUtil.handleCookies(driver); // Accepts cookies
	    }

	    @Test(dataProvider = "FTCStoreData", priority = 1)
    	public void verifyFTCStoreLogoIsDisplayed(String storeId, String storeName) {
    		log.info("Starting FTC Test");
    		try {
    			log.debug("Waiting for the store page to load...");
    	        TestUtil.waitForPageToLoad(driver); 
    			log.debug("Performing the validation of FTC store logo");         
    			System.out.println("Store ID: " + storeId + ", Store Name: " + storeName); // Debugging
    			boolean isLogoDisplayed = ftcStorePage.ftc_Logo();
    			Assert.assertTrue(isLogoDisplayed, "Flying Tiger logo is not displayed");	    
    			log.info("FTC Logo validation test executed successfully");
    		} catch (Exception e) { 	   
    			log.error("Error occurred during FTC Logo validation ", e);
    		}                 	
	    }

	    @Test(dataProvider = "FTCStoreData", priority = 2, dependsOnMethods = "verifyFTCStoreLogoIsDisplayed")
	    public void verifyFTCItemCanBeAddedToBasket(String storeId, String storeName) throws InterruptedException {
	    	Thread.sleep(9000);	        
	    	log.info("Starting Item Scan Test");

	        storePageTest.verifyItemCanBeAddedToBasket(storeId, storeName); // Call common function
	    }

	    @Test(dataProvider = "FTCStoreData",priority = 3, dependsOnMethods = "verifyFTCItemCanBeAddedToBasket")
    	public void verifyDonationPopupAppears (String storeId,String storeName) throws InterruptedException
    	{         		
	    	//verifyFTCItemCanBeAddedToBasket(storeId, storeName);
    		log.debug("Waiting for the page to load before scanning barcode...");           
       		//TestUtil.waitForPageToLoad(driver);
	    	Thread.sleep(9000);	        

       		  ftcStorePage.clickOKOnDonationPopUp();
    		log.info("clicking OK button on donation pop up test executed successfully");

    	}

	    @Test(dataProvider = "FTCStoreData", priority = 4, dependsOnMethods = "verifyDonationPopupAppears" )
    	public void verifyCheckoutButtonFunctionality(String storeId, String storeName) throws InterruptedException {           
    		//verifyDonationPopupAppears(storeId, storeName);
    		 TestUtil.waitForPageToLoad(driver);
            log.info("waiting for checkout button to appear");
            
            ftcStorePage.clickOnCheckoutButton();
            
            log.info("clicked on checkout button successfully");
         
           ftcStorePage.clickOnCheckoutButton2();

            log.info("clicked on checkout2 button successfully");
            log.info("app navigated to payment screen"); 
        

    	}

	    @Test(dataProvider = "FTCStoreData", priority = 5, dependsOnMethods = "verifyCheckoutButtonFunctionality")
	    public void verifyFTCPaymentScreenFunctionality(String storeId, String storeName) throws InterruptedException {
   		    TestUtil.waitForPageToLoad(driver);
	        log.info("Starting Payment Screen Test");
	        storePageTest.verifyPaymentScreenFunctionality(storeId, storeName); // Call common function
	    }

	    @AfterClass(alwaysRun = true)
	    public void tearDown() {
	        log.info("Resetting browser state");
	        driver.manage().deleteAllCookies();
	        driver.navigate().refresh();
	        driver.quit();
	    }
	}



