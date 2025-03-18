package com.mishipay.webshopper.testcases;

//import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mishipay.webshopper.base.TestBase;
import com.mishipay.webshopper.pages.MapViewPage;
import com.mishipay.webshopper.pages.StoreLocatorPage;



public class StoreLocatorPageTest extends TestBase{
  
	StoreLocatorPage storelocatorPage;
	MapViewPage mapviewPage;
	
	
	public StoreLocatorPageTest() {
		super();   //super keyword will call super class constructor that is public TestBase() constructor to initialise the properties and its compulsory to call test base class construtor
		
	}
	
	
	@BeforeMethod
	public void setUp() {
		initialisation(null);
		storelocatorPage = new StoreLocatorPage();
	}

	@Test(priority=1, description= "verifying app is loaded and displaying splash screen")
	public void storeLocatorPageTitleTest() {
		
		String title = storelocatorPage.validateStoreLocatorPageTitle();
		Assert.assertEquals(title, "Mishipay | It's time we shop smart");
	}
	
	@Test(priority=2)
	public void startScanTextTest() {
		//TestUtil.waitForPageToLoad(driver); not working 
		boolean flag = storelocatorPage.verifyStartScanTextAtSpalshScreen();
	    Assert.assertTrue(flag);
	    
	}
	
	
	@Test(priority=3)
	public void acceptAllCookiesTest() {
		storelocatorPage.validateOkayButtonOnacceptAllCookies();
	
		}

		
	
	@Test(priority=4)
	
	public void enableLocationAccessTest() {
           storelocatorPage.validateClickOnEnableLocationAccessButton();
           
	}
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
		
	}

	
	
	
}
