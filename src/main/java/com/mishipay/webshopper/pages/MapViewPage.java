package com.mishipay.webshopper.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mishipay.webshopper.base.TestBase;

/**
 * This class represents the Map View Page of the web application.
 * It contains methods to interact with the elements on the Map View Page.
 */
public class MapViewPage extends TestBase{
	
    // Web element for the "Select a store to start shopping" button
	@FindBy(xpath = "//button[contains(text(),'Select a store to start shopping')]")
    WebElement SelectAStoreToStartShoppingButton;
	
    /**
     * Constructor to initialize the web elements on the Map View Page.
     */
    public MapViewPage() {
    	 PageFactory.initElements(driver, this);
     }
     
    /**
     * Method to click on the "Select a store to start shopping" button.
     * @return StoreListingPage - navigates to the Store Listing Page.
     */
     public StoreListingPage clickOnSelectAStoreToStartShopping() {
    	 SelectAStoreToStartShoppingButton.click();
         return new StoreListingPage();
     }
     
}
