package com.mishipay.webshopper.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mishipay.webshopper.base.TestBase;


public class StoreLocatorPage extends TestBase {
	//Page Factory or Object repository
	
	
	@FindBy(xpath = "//p[text()='Start your Scan & Go experience']")
    WebElement StartScanText;
	
	
	@FindBy(xpath = "//span[text()='Okay']")
	 WebElement AcceptCookiesButton;			
	
	@FindBy(xpath= "//span[contains(text(),' Enable Location Access ')]")
	WebElement EnableLocationAccessButton;

	
	public StoreLocatorPage(){
		
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String validateStoreLocatorPageTitle(){
		
		return driver.getTitle();
	}
	
	
	public boolean verifyStartScanTextAtSpalshScreen(){
		
		return StartScanText.isDisplayed();
	}
	
	
	public WebElement validateOkayButtonOnacceptAllCookies(){
		
		AcceptCookiesButton.click();
		return AcceptCookiesButton;
		
	}
	
	public MapViewPage validateClickOnEnableLocationAccessButton() {
		EnableLocationAccessButton.click();
        return new MapViewPage();
	}
	
}
