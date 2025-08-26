package com.saucedemo.tests.positive;

import com.microsoft.playwright.junit.UsePlaywright;
import com.saucedemo.helpers.AssertHelper;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.TestBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@UsePlaywright
public class LoginPagePositiveTest extends TestBase {

    @Test
    void validLogin() {
        LoginPage loginPage = new LoginPage(page);

        // Navigate & login
        loginPage.navigateToLogin();
        loginPage.loginValidUser();

        // Assert successful login
        AssertHelper.verifyTrue(page.url().contains("inventory.html"),"User should be redirected to inventory after login");


        System.out.println("Valid login successful");
    }
}