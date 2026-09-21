package com.example.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public final class WebDriverFactory {
    private static final Logger log = LoggerFactory.getLogger(WebDriverFactory.class);
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
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments(" --disable-notifications");

        log.info("Chrome driver created with options: {}", options);
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("signon.rememberSignons", false);
        options.addArguments("-headless");
        log.info("Firefox driver created with options: {}", options);
        return new FirefoxDriver(options);
    }

}
