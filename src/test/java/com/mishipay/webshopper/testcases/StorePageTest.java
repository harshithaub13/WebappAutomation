package com.mishipay.webshopper.testcases;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mishipay.webshopper.base.TestBase;
import com.mishipay.webshopper.pages.StorePage;
import com.mishipay.webshopper.utils.LoggerHelper;
import com.mishipay.webshopper.utils.PaymentUtil;
import com.mishipay.webshopper.utils.TestUtil;

public class StorePageTest extends TestBase{
    

	   private static final Logger log = LoggerHelper.getLogger(StorePageTest.class);
	   StorePage storePage;
	   
	   public StorePageTest(WebDriver driver) {
		    this.driver = driver;
		    storePage = new StorePage(driver);  // ✅ Initialize StorePage with driver
		}
	   
	   @BeforeMethod(alwaysRun = true)
	    public void setUp() {
	        storePage = new StorePage(driver);  // ✅ Initialize storePage
	    }

	   
	@Test
    public void verifyItemCanBeAddedToBasket(String storeId, String storeName) throws InterruptedException {
		String barcode = "0200024008604" ;
        TestUtil.waitForPageToLoad(driver);           		

        log.info("Barcode scanning function started");
        	try {
        		TestUtil.waitForPageToLoad(driver);

    	        log.debug("Clicking on 'Cannot Scan' button...");
    	        Thread.sleep(9000);
    	        storePage.clickOnCannotScanItemButton();
    	        
    	        log.info("Entering barcode: " + barcode);
    	        TestUtil.waitForPageToLoad(driver);
    	        
    	        storePage.enterBarcodeField(barcode);
    	        
    	        log.info("Submitting barcode...");
    	        
    	        TestUtil.waitForPageToLoad(driver);
    	        storePage.clickOnSubmitButton();
    	        
    	        log.info("click on basket ");

        		TestUtil.waitForPageToLoad(driver);
                 Thread.sleep(4000);

        		storePage.basketOption();
	          
        		log.info("clicking on basket button is success");
          

        	} catch (Exception e) {
        		log.error("Error occurred during add item to scan validation ", e);
        		Assert.fail("Test failed due to an exception: " + e.getMessage());

        	}
    	}
	
	
		@Test(dataProvider = "FTCStoreData", priority = 5)
		public void verifyPaymentScreenFunctionality(String storeId, String storeName) throws InterruptedException {
		
			log.info("Waiting for payment screen to load...");
			TestUtil.waitForPageToLoad(driver);
		    log.info("waiting for payment screen to host");
		    log.info("payment screen is hosted" );
		 //Read data from config file
		   String email = PaymentUtil.getProperty("email");
		   String cardholderName = PaymentUtil.getProperty("cardholderName");
		   String cardNumber = PaymentUtil.getProperty("cardNumber");
		   String expiryDate = PaymentUtil.getProperty("expiryDate");
		   String cvv = PaymentUtil.getProperty("cvv");
		 try {
		 //Performing the actions on payment screen
		    log.info("Entering email: " + email);
		    storePage.enterEmail(email);
		    log.info("Entering cardholder name: " + cardholderName);
		    storePage.enterCardholderName(cardholderName);
		    log.info("Entering card number: " + cardNumber);
		    storePage.enterCardNumberField(cardNumber);
		    log.info("Entering expiry date: " + expiryDate);
		    storePage.enterExpiryDateField(expiryDate);
		    log.info("Entering CVV: " + cvv);
		    storePage.enterCVVField(cvv);
		    log.info("Clicking pay button");
		    storePage.clickPay();
		    TestUtil.waitForPageToLoad(driver);
		 }  catch (Exception e) {
			 log.error("Error interacting with payment form: " + e.getMessage(), e);
	
		 }
	}	
		

}
