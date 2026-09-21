package com.example.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Locale;

public final class WebDriverFactory {

    private WebDriverFactory() {
    }

    public static WebDriver createDriver(String browserName) {
        String browser = (browserName == null || browserName.isBlank())
                ? System.getProperty("browser", "firefox")
                : browserName;

        return switch (browser.toLowerCase(Locale.ROOT)) {
            case "firefox" -> createFirefoxDriver();
            default -> createChromeDriver();
        };
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=false");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("-- --disable-notifications");

        System.out.println("Chrome driver created with options: " + options);
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("signon.rememberSignons", false);
        //options.addArguments("--headless=false");
        System.out.println("Firefox driver created with options: " + options);
        return new FirefoxDriver(options);
    }

}
