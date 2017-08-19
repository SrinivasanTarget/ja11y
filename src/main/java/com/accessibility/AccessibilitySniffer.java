package com.accessibility;

import org.jsoup.Jsoup;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.io.IOException;
import java.util.Date;

public class AccessibilitySniffer implements IAccessibilityManager {

    private WebDriver driver;
    private JavascriptExecutor js;

    public AccessibilitySniffer(WebDriver driver) throws IOException {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public void runCodeSniffer() throws IOException {
        String jquery_content = Jsoup.connect("http://squizlabs.github.io/HTML_CodeSniffer/build/HTMLCS.js").ignoreContentType(true).execute().body();
        js.executeScript(jquery_content);
        js.executeScript("window.HTMLCS_RUNNER.run('WCAG2AA');");
        LogEntries logs = driver.manage().logs().get("browser");
        for (LogEntry entry : logs) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
    }


    public String getAccessibilityErrors() {
        return null;
    }

    public String getAccessibilityWarnings() {
        return null;
    }

    public String getAccessibilityNotices() {
        return null;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
