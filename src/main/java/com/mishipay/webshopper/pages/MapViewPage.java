package com.mishipay.webshopper.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mishipay.webshopper.base.TestBase;

public class MapViewPage extends TestBase{
	
	
	@FindBy(xpath = "//button[contains(text(),'Select a store to start shopping')]")
    WebElement SelectAStoreToStartShoppingButton;
	

 
     public MapViewPage() {
    	 PageFactory.initElements(driver, this);
     }
     
     
     public StoreListingPage clickOnSelectAStoreToStartShopping() {
    	 SelectAStoreToStartShoppingButton.click();
         return new StoreListingPage();
     }
     
}