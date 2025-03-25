package com.mishipay.webshopper.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import com.mishipay.webshopper.base.TestBase;
import com.mishipay.webshopper.pages.DDFStorePage;
import org.openqa.selenium.JavascriptExecutor;
import java.util.Set;
import java.util.Iterator;

public class DDFStorePageTest extends TestBase {

    private DDFStorePage ddfStorePage;
    private String filePath = "/Users/kaushalvarma/Downloads/WebappAutomation/src/main/resources/barcode1.png";

    @BeforeClass
    public void setUp() {
        initialisation("e2028c93-d118-4b6b-9d50-7973eee5ef4f");
        ddfStorePage = new DDFStorePage(driver);
    }

    @Test
    public void testBoardingPassUpload() {
        ddfStorePage.uploadBoardingPass(filePath);
    }

    @Test
    public void testClickUploadScreenshotButton() {
        ddfStorePage.uploadBoardingPass(filePath);
    }

    @Test
    public void testTransitPopup() {
        ddfStorePage.uploadBoardingPassAndHandleTransit(filePath, "yes");
    }

    @Test
    public void testSwitchWindow() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String originalWindow = iterator.next();
        String newWindow = iterator.next();
        driver.switchTo().window(newWindow);
        driver.get("https://www.example.com");
        assertTrue(driver.getTitle().contains("Example Domain"));
        driver.switchTo().window(originalWindow);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
