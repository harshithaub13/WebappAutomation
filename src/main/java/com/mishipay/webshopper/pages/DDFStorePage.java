package com.mishipay.webshopper.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class DDFStorePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By boardingPassUploadButton = By.xpath("//button[span[text()='Upload screenshot']]");
    private By fileInput = By.xpath("//input[@type='file']");
    private By boardingPassDetails = By.xpath("//div[contains(text(), 'Boarding Pass Details')]");

    public DDFStorePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void uploadBoardingPassAndHandleTransit(String filePath, String transitOption) {
        uploadBoardingPass(filePath);
        handleTransitPopup(transitOption);
    }

    public void uploadBoardingPass(String filePath) {
        System.out.println("[INFO] Waiting for 'Upload Screenshot' button...");
        WebElement uploadButton = wait.until(ExpectedConditions.elementToBeClickable(boardingPassUploadButton));

        try {
            uploadButton.click();
            System.out.println("[SUCCESS] 'Upload Screenshot' button clicked successfully.");
        } catch (ElementClickInterceptedException e) {
            System.out.println("[WARNING] Click intercepted! Using JavaScript Click.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", uploadButton);
        }

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("[ERROR] File not found: " + filePath);
            throw new RuntimeException("[ERROR] Invalid file path provided.");
        }

        System.out.println("Trying to Upload the file: " + filePath);
        WebElement fileInputElement = wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
        fileInputElement.sendKeys(filePath);
        System.out.println("[SUCCESS] File uploaded successfully.");

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(boardingPassDetails));
            System.out.println("[SUCCESS] Boarding pass uploaded successfully, next screen loaded.");
        } catch (TimeoutException e) {
            System.err.println("[ERROR] Boarding pass upload failed! Next screen did not appear.");
            throw new RuntimeException("[ERROR] Boarding pass upload validation failed. Please check the file format or system response.");
        }
    }

    public void handleTransitPopup(String choice) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            WebElement dialogContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("mat-dialog-container.mat-dialog-container")));
            System.out.println("Dialog container located");

            js.executeScript("arguments[0].style.border='3px solid red'", dialogContainer);

            if ("yes".equalsIgnoreCase(choice)) {
                handleYesOption(js, wait);
            } else {
                handleNoOption(js, wait);
            }

            wait.until(ExpectedConditions.invisibilityOf(dialogContainer));
            System.out.println("Dialog closed successfully");

            postConditionVerification(choice, wait);

        } catch (Exception e) {
            System.err.println("Error in handleTransitPopup");
            System.err.println("Current URL: " + driver.getCurrentUrl());
            System.err.println("Page title: " + driver.getTitle());
            takeScreenshot("transit_error_" + System.currentTimeMillis());
            throw new RuntimeException("Failed to handle transit popup: " + e.getMessage(), e);
        }
    }

    private void handleYesOption(JavascriptExecutor js, WebDriverWait wait) throws TimeoutException {
        WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-test='boarding-start-scan']")));

        js.executeScript("arguments[0].scrollIntoView({block: 'center'})", yesButton);
        js.executeScript("arguments[0].style.border='2px solid green'", yesButton);

        new Actions(driver)
                .moveToElement(yesButton)
                .pause(Duration.ofMillis(500))
                .click()
                .perform();
        System.out.println("Clicked 'Yes, I have' button");

        try {
            WebElement stebMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(),'STEB Transaction required')]")));
            System.out.println("STEB message displayed");

            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[.//span[text()='OK']]")));

            js.executeScript("arguments[0].click()", okButton);
            System.out.println("Clicked OK button");
        } catch (TimeoutException e) {
            System.out.println("No STEB message displayed. Proceeding...");
        }
    }

    private void handleNoOption(JavascriptExecutor js, WebDriverWait wait) throws TimeoutException {
        WebElement noButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[contains(text(),'No')]]")));

        js.executeScript("arguments[0].click()", noButton);
        System.out.println("Clicked 'No' button");
    }

    private void postConditionVerification(String choice, WebDriverWait wait) throws TimeoutException {
        if ("no".equalsIgnoreCase(choice)) {
            wait.until(ExpectedConditions.urlContains("/sg/scan"));
            System.out.println("Navigated to scan page");
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[contains(text(),'Upload screenshot')]")));
            System.out.println("Returned to upload page");
        }
    }

    public void switchToNewWindow() {
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String originalWindow = iterator.next();
        String newWindow = iterator.next();
        driver.switchTo().window(newWindow);
    }

    public void switchToOriginalWindow(String originalWindow) {
        driver.switchTo().window(originalWindow);
    }

    private void takeScreenshot(String filename) {
        try {
            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            Files.write(Paths.get(filename + ".png"), screenshot);
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
