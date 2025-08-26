package com.saucedemo.helpers;

import com.microsoft.playwright.Page;
import com.saucedemo.utils.Constants;
import io.qameta.allure.Step;

import static com.microsoft.playwright.options.AriaRole.BUTTON;

public class LoginHelper {
    @Step("Login with valid user")
    public static void loginValidUser(Page page) {
        page.navigate(Constants.BASE_URL);
        page.locator("#user-name").fill(Constants.STANDARD_USER);
        page.locator("#password").fill(Constants.PASSWORD);
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
    }

    @Step("Login with invalid credentials: username={username}, password={password}")
    public static void loginInvalidUser(Page page, String username, String password) {
        page.locator("#user-name").fill(username);
        page.locator("#password").fill(password);
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
    }

    @Step("Logout user")
    public static void logout(Page page) {
        page.getByText("Open Menu").click();
        page.getByText("Logout").click();
    }
}
