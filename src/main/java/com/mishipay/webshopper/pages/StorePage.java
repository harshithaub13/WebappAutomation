package com.mishipay.webshopper.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mishipay.webshopper.base.TestBase;
import com.mishipay.webshopper.utils.TestUtil;

public class StorePage extends TestBase{
    WebDriverWait wait;

    @FindBy(xpath="//div[contains(text(), 'Cannot scan barcode')]")
    WebElement cannotScanButton;
    
    @FindBy(xpath="//input[@id='barcode']")
    WebElement barcodeField;
    
    @FindBy(xpath="//span[@class='mat-button-wrapper' and text()='Submit']")
    WebElement submitButton;
    
    @FindBy(xpath= "//button[.//div[contains(@class, 'nav-text') and text()='Basket']]")
    WebElement basket_button;
    
    @FindBy(xpath = "//*[@id='fallback-button']")   
    WebElement checkoutButton;
    
    @FindBy(xpath= "//*[@id=\"email-address\"]")
    WebElement emailField;
    	    
    @FindBy(xpath= "//*[@id=\"card-holder\"]")
    WebElement cardholderNameField;
    @FindBy(xpath= "//*[@id=\"card-number\"]")
    WebElement cardNumberField;
    
    @FindBy(xpath= "//*[@id=\"card-expiration\"]")
    WebElement expiryDateField;
    
    @FindBy(xpath= "//*[@id=\"cvv\"]")
    WebElement cvvField;

    @FindBy(xpath= "//*[@id='pay-btn']/span")
    WebElement payButton;
    
    public StorePage(WebDriver driver) {
         //super(driver);
         this.driver = driver; // Assign WebDriver to the local driver variable
         //new PaymentUtil();
         PageFactory.initElements(driver, this);
    	this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }
    
    public void clickOnCannotScanItemButton() {
    	TestUtil.waitForPageToLoad(driver);
    	wait.until(ExpectedConditions.elementToBeClickable(cannotScanButton));
    	cannotScanButton.click();
    }
    
    public void enterBarcodeField(String barcode) {
    	wait.until(ExpectedConditions.elementToBeClickable(barcodeField));
    	barcodeField.clear();
    	barcodeField.sendKeys(barcode);
    }
    
    
    public void clickOnSubmitButton() {
    	wait.until(ExpectedConditions.elementToBeClickable(submitButton));
    	submitButton.click();
    }
    
    public void basketOption() {    	
        TestUtil.waitForElementToBeClickable(driver, basket_button); // Wait for button to be clickable
    	 basket_button.click();
    }
    
    public void enterEmail(String email) {     
    	TestUtil.waitForPageToLoad(driver);
        TestUtil.waitForElementToBeClickable(driver, emailField);
    	emailField.clear();
    	emailField.sendKeys(email);
    }
    
    
    public void enterCardholderName(String cardholderName) {
        TestUtil.waitForElementToBeClickable(driver, cardholderNameField);
    	cardholderNameField.clear();
    	cardholderNameField.sendKeys(cardholderName);
    }
        
    public void enterCardNumberField(String cardNumber) {
        TestUtil.waitForElementToBeClickable(driver, cardNumberField);
    	cardNumberField.clear();
    	cardNumberField.sendKeys(cardNumber);
    }
    
    public void enterExpiryDateField(String expiryDate) {
        TestUtil.waitForElementToBeClickable(driver, expiryDateField);
    	expiryDateField.clear();
    	expiryDateField.sendKeys(expiryDate);
    }
    
    public void enterCVVField(String cvv) {
        TestUtil.waitForElementToBeClickable(driver, cvvField);
    	cvvField.clear();
    	cvvField.sendKeys(cvv);
    }
    
     public void clickPay() {
	   TestUtil.waitForElementToBeClickable(driver, payButton);
        payButton.click();
    }
 
}
    
    
    
    
    
    
    
