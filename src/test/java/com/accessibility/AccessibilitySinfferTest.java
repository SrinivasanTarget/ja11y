package com.accessibility;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by ssekar on 8/19/17.
 */
public class AccessibilitySinfferTest {

    private WebDriver driver;
    private AccessibilitySniffer accessibilitySniffer;

    @Before
    public void beforeTest() throws IOException {
        ChromeDriverManager.getInstance().setup();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(desiredCapabilities);
        accessibilitySniffer = new AccessibilitySniffer(driver);

    }

    @Test
    public void runSnifferTest() throws IOException {
        driver.get("http://www.google.com");
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.MILLISECONDS);
        accessibilitySniffer.runCodeSniffer();



    }
}
