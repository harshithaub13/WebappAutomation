package com.mishipay.webshopper.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class BarcodeScannerUtil {

    private WebDriver driver;

    public BarcodeScannerUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void executeScanCommand(String barcode, String type) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

    // Check if 'scan' function is available
    Boolean isScanFunctionAvailable = (Boolean) jsExecutor.executeScript(
        "return typeof scan === 'function';"
    );

    if (isScanFunctionAvailable) {
        String command = String.format("scan('%s','%s')", barcode, type);
        jsExecutor.executeScript(command);
        
    } else 
    {
        throw new RuntimeException("Scan function is not defined on the page.");
    }
  }

    public static void injectBarcode(WebDriver driver, String barcodeFilePath) {
        WebElement uploadElement = driver.findElement(By.id("boarding-pass-upload")); // Update locator
        uploadElement.sendKeys(barcodeFilePath);
    }

}
    
