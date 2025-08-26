package com.saucedemo.tests.positive;

import com.saucedemo.helpers.AssertHelper;
import com.saucedemo.helpers.LoginHelper;
import com.saucedemo.utils.TestBase;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.junit.UsePlaywright;

import static org.junit.jupiter.api.Assertions.assertTrue;



@UsePlaywright
public class LogoutPositiveTest extends TestBase {


   @Test
    void logout_fromMenu(){
        LoginHelper.loginValidUser(page);
        LoginHelper.logout(page);

       AssertHelper.verifyVisible(page.locator(".login_logo"), "Login page is correctly visible after logout");

       //if logout is not successful
       LoginHelper.loginValidUser(page);
       //LoginHelper.logout(page);
       AssertHelper.verifyNotVisible(page.locator(".login_logo"), "Login page is not visible after logout");

    }
}

