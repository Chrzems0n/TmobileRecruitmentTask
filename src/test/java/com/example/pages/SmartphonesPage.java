package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SmartphonesPage extends BasePage {


    private  static final By SMARTHPHONES_LIST = By.xpath("//section[@class='mainContent']");


    public List<WebElement> getSmartphonesList() {
        return getDriver().findElements(SMARTHPHONES_LIST);
    }

    public void clickDeviceFromList(String deviceName) {
        waitForSmartphonesPageToBeVisible();
        List<WebElement> devices = getSmartphonesList();
        for (WebElement device : devices) {
            if (device.getText().contains(deviceName)) {
                scrollToElementWithJavaScript(device);
                clickByAction(device);
                return;
            }
        }
        throw new IllegalStateException("Urządzenie nie znalezione w liście: " + deviceName);

    }


    public void waitForSmartphonesPageToBeVisible() {
        waitForElementIsVisible(SMARTHPHONES_LIST);
    }
    public boolean isPageVisible() {
        return isDisplayed(By.xpath("//*[contains(normalize-space(.), 'Smartfony') or contains(normalize-space(.), 'telefony')][1]"));
    }

}