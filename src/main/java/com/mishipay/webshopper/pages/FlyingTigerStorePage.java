package com.mishipay.webshopper.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.mishipay.webshopper.base.TestBase;
import com.mishipay.webshopper.utils.BarcodeScannerUtil;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import com.mishipay.webshopper.utils.PaymentUtil;
import com.mishipay.webshopper.utils.ScreenshotUtil;
import com.mishipay.webshopper.utils.TestUtil;


public class FlyingTigerStorePage extends TestBase {
	   //WebDriver driver;
    WebDriverWait wait;

      private BarcodeScannerUtil barcodeScannerUtil;
       @FindBy(xpath = "//img[@src='https://static-assets.mishipay.com/flying-tiger/images/BrandImageScanner.png']")
        WebElement FlyingTigerLogo;
	   
	    
	    @FindBy(xpath = "//*[*[local-name()='svg']]//circle[@cx='12' and @cy='12' and @r='11']")
	    WebElement increaseQuantityButton;
	    
	    @FindBy(xpath = "//*[@id=\"mat-dialog-0\"]")
	    WebElement donationPopUpDailogue;
	    
	    @FindBy(xpath = "//p[contains(text(),'Flying Tiger Copenhagen is working with AKT Donation')]")
	    WebElement donationPopUpText;
	    
	    
	    @FindBy(xpath = "//mat-dialog-actions//button//span[text()=' OK ']")
	    WebElement donationPopUpOkButton;
	    
	    @FindBy(xpath = "//*[@id='fallback-button']")   
	    WebElement checkoutButton;
	    
	    @FindBy(xpath = "//*[@id='hosted']")    
	    WebElement checkoutButton2;
	    
	    
	    
	     public FlyingTigerStorePage(WebDriver driver) {
           // super(driver);
            this.driver = driver; // Assign WebDriver to the local driver variable
            this.barcodeScannerUtil = new BarcodeScannerUtil(driver);
            new PaymentUtil();
            
		    PageFactory.initElements(driver, this);
		    this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    
	     }
	
	    public boolean ftc_Logo(){
	        wait.until(ExpectedConditions.visibilityOf(FlyingTigerLogo));  // Wait for visibility
		   return FlyingTigerLogo.isDisplayed();

	    }
	    
	    public void add_Item_To_Basket(String barcode, String type) {
	    	TestUtil.waitForPageToLoad(driver);
	    	barcodeScannerUtil.executeScanCommand(barcode, type);
	    }
	    
	   /* public void waitforSpinner() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	       
	            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='spinner-container ng-star-inserted']")));            
	        
	    }*/
	    
	    
	    
	    public boolean isdonationPopUpDisplayed() {
	    	
	    	wait.until(ExpectedConditions.visibilityOf(donationPopUpDailogue));
	    	return donationPopUpDailogue.isDisplayed();
	    	 		    	
	    }
	    
	    public String getDonationPopUpText() {
	    	wait.until(ExpectedConditions.visibilityOf(donationPopUpText));
	    	return donationPopUpText.getText();
	    	
	    }
	    
	    public void clickOKOnDonationPopUp() {
	        try {
	        	TestUtil.waitForElementToBeClickable(driver, donationPopUpOkButton);
	            donationPopUpOkButton.click();
	            System.out.println("Click successful");
	        } catch (Exception e) {
	            System.out.println("Test failed: " + e.getMessage());
	            ScreenshotUtil.captureScreenshot(driver, "clickOKOnDonationPopUp_Failure");
	        }

	    }
	
	    
	    public WebElement clickOnCheckoutButton() {
	    	 TestUtil.waitForElementToBeClickable(driver, checkoutButton);
	    	 checkoutButton.click(); 	 
	    	return checkoutButton;
	    }

	    public void clickOnCheckoutButton2() {
	    	 TestUtil.waitForElementToBeClickable(driver, checkoutButton2);
	    	 checkoutButton2.click();;
	    	 
	    	  new WebDriverWait(driver, Duration.ofSeconds(30)).until(
	    		        webDriver -> ((JavascriptExecutor) webDriver)
	    		            .executeScript("return document.readyState").equals("complete"));
	    	  try {
	    		  Thread.sleep(5000);
	    	  }catch (InterruptedException e) {
		    	     e.printStackTrace();
		    	 }
		    }
	    	  	      
	    
	    
}
	

