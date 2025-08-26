package com.saucedemo.tests.negative;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.saucedemo.helpers.AssertHelper;
import com.saucedemo.utils.Constants;
import com.saucedemo.helpers.LoginHelper;
import com.saucedemo.utils.TestBase;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

@UsePlaywright
public class LoginNegativeTest extends TestBase {

    @Test

    void invalidLogin(Playwright pw) {
        LoginHelper.loginInvalidUser(page, Constants.STANDARD_USER, "wrong_password");

        AssertHelper.verifyVisible(page.getByText("Epic sadface: Username and password do not match any user in this service"),
                "Invalid login - error message is visible");

        String screenshotPath = "screenshots/InvalidLogin.jpg";
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));

        //if provided correct credentials
        LoginHelper.loginInvalidUser(page, Constants.STANDARD_USER, "secret_sauce");  //provided correct password in this case
        AssertHelper.verifyNotVisible(page.getByText("Epic sadface: Username and password do not match any user in this service"),"Error message is not visible!");

    }

    @Test
    void missingPasswordField(){
        page.navigate(Constants.BASE_URL);
        page.locator("#user-name").fill(Constants.STANDARD_USER);
        page.locator("#login-button").click();

        AssertHelper.verifyVisible(page.getByText("Epic sadface: Password is required"),"Error correctly displayed for missing password");
        String screenshotPath = "screenshots/MissingPassword.jpg";
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));

    }

    @Test
    void missingUsernameField(){
        page.navigate(Constants.BASE_URL);
        page.locator("#password").fill(Constants.PASSWORD);
        page.locator("#login-button").click();

        AssertHelper.verifyVisible(page.getByText("Epic sadface: Username is required"),"Error correctly displayed for missing username");
        String screenshotPath = "screenshots/MissingUsername.jpg";
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));


    }

}
