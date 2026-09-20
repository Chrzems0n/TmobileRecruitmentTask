package com.example.pages;

import com.example.driver.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BasePage {

    private static final Logger log = LoggerFactory.getLogger(BasePage.class);
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage() {
        this.driver = WebDriverProvider.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }


    protected WebDriver getDriver() {
        return driver;
    }

    protected void open(String url) {
        driver.get(url);
    }

    protected WebElement waitForElementIsVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementIsClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForElementIsClickable(WebElement locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        waitForElementIsClickable(locator).click();
    }

    protected String getText(By locator) {
        return waitForElementIsVisible(locator).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return waitForElementIsVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void acceptCookiesIfVisible() {

        try{
            By cookieButton = By.xpath("//button[normalize-space()='Akceptuję wszystkie' or normalize-space()='Accept all']");
           waitForElementIsVisible(cookieButton).click();
    }
        catch (Exception e){
            log.info("No cookie banner found, continuing...");
        }
    }

    protected static String escapeXPath(String value) {
        return value.replace("'", "\\'");
    }

    protected void clickByAction(WebElement element) {
        Actions actions = new Actions(driver);
        actions.click(element).perform();
    }

    //abstract public void waitforPageIsReadyForActions();

    //}
}
