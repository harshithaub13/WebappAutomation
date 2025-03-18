package com.mishipay.webshopper.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mishipay.webshopper.base.TestBase;
import com.mishipay.webshopper.pages.MapViewPage;
import com.mishipay.webshopper.pages.StoreLocatorPage;

public class MapViewPageTest extends TestBase{

	StoreLocatorPage storelocatorPage;
	MapViewPage mapviewPage;

	
	public MapViewPageTest() {
		super();   //super keyword will call super class constructor that is public TestBase() constructor to initialise the properties and its compulsory to call test base class construtor
			
		}
		
	
	@BeforeMethod
	public void setUp() {
		initialisation(null);//remove null later
		storelocatorPage = new StoreLocatorPage();
		mapviewPage = storelocatorPage.validateClickOnEnableLocationAccessButton();
	}
	
	//test cases should be independent or or avoid dependency between test cases
	@Test
	public void verifyClickOnSelectAStoreToStartShopping() {
		mapviewPage.clickOnSelectAStoreToStartShopping();
		
	}
	
	

	@AfterMethod
	public void tearDown(){
		driver.quit();
		
	}
	
	
}
