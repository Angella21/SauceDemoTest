package com.saucedemo.tests.negative;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.saucedemo.helpers.AssertHelper;
import com.saucedemo.utils.TestBase;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

@UsePlaywright
public class UnauthorizedInventoryAccessTest extends TestBase {

    @Test
    void shouldRedirectToLogin(Page page){
        page.navigate("https://www.saucedemo.com/inventory.html");
        AssertHelper.verifyVisible(page.locator(".login_logo"), "Login page is visible after redirect");
        String screenshotPath = "screenshots/LoginPageRedirected.jpg";
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));


    }
}
