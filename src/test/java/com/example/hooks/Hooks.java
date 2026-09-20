package com.example.hooks;

import com.example.driver.WebDriverFactory;
import com.example.driver.WebDriverProvider;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class Hooks {

    @Before
    public void setUp() {
        WebDriver driver = WebDriverFactory.createDriver(System.getProperty("browser", "firefox"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        WebDriverProvider.setDriver(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            WebDriver driver = WebDriverProvider.getDriver();
            if (scenario.isFailed() && driver instanceof TakesScreenshot takesScreenshot) {
                byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "failure-screenshot");
                Allure.addAttachment("Failure screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
            }
        } catch (IllegalStateException ignored) {
            // no-op if driver was not initialized
        } finally {
            //WebDriverProvider.quitDriver();
        }
    }
}
