package com.saucedemo.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.TestWatcher;


import java.io.ByteArrayInputStream;

import static com.saucedemo.utils.Constants.BASE_URL;


public class TestBase {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    @BeforeEach
    void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false) // set true if you don’t want UI
        );
        page = browser.newPage();

        // Use BASE_URL from Constants
        page.navigate(BASE_URL);
    }

    @AfterEach
    void tearDown() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    /**
     * Inner class to capture screenshot only when test fails
     */
    public static class ScreenshotOnFailure implements TestWatcher {
        @Override
        public void testFailed(org.junit.jupiter.api.extension.ExtensionContext context, Throwable cause) {
            Object testInstance = context.getRequiredTestInstance();
            if (testInstance instanceof TestBase) {
                TestBase base = (TestBase) testInstance;
                if (base.page != null) {
                    try {
                        // optional short wait before screenshot
                        base.page.waitForTimeout(1000); // 1 second

                        // take full-page screenshot with 60s timeout
                        byte[] screenshot = base.page.screenshot(
                                new Page.ScreenshotOptions()
                                        .setFullPage(true)
                                        .setTimeout(60000) // 60 seconds
                        );

                        Allure.addAttachment(context.getDisplayName() + " - Failure Screenshot",
                                new ByteArrayInputStream(screenshot));
                    } catch (Exception e) {
                        System.out.println("Failed to take screenshot: " + e.getMessage());
                    }
                }
            }
        }
    }


}


