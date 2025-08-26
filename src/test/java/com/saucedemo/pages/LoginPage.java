package com.saucedemo.pages;

import com.microsoft.playwright.Page;
import com.saucedemo.utils.Constants;

public class LoginPage {
    private final Page page;

    // Locators
    private final String usernameInput = "#user-name";
    private final String passwordInput = "#password";
    private final String loginButton = "#login-button";

    // Constructor
    public LoginPage(Page page) {
        this.page = page;
    }

    // Actions
    public void navigateToLogin() {
        page.navigate(Constants.BASE_URL);
    }

    public void login(String username, String password) {
        page.locator(usernameInput).fill(username);
        page.locator(passwordInput).fill(password);
        page.locator(loginButton).click();
    }

    public void loginValidUser() {
        login(Constants.STANDARD_USER, Constants.PASSWORD);
    }
}
