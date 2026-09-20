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
        List<WebElement> devices = getSmartphonesList();
        for (WebElement device : devices) {
            if (device.getText().contains(deviceName)) {
                clickByAction(device);
                return;
            }
        }
        throw new IllegalStateException("Device not found in the list: " + deviceName);
    }

    public boolean isPageVisible() {
        return isDisplayed(By.xpath("//*[contains(normalize-space(.), 'Smartfony') or contains(normalize-space(.), 'telefony')][1]"));
    }

}