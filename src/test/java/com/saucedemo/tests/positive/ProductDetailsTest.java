package com.saucedemo.tests.positive;

import com.microsoft.playwright.junit.UsePlaywright;
import com.saucedemo.helpers.LoginHelper;
import com.saucedemo.helpers.ProductsHelper;
import com.saucedemo.utils.TestBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@UsePlaywright
public class ProductDetailsTest extends TestBase {
    @Test
    void productDetails() {

            LoginHelper.loginValidUser(page);

            ProductsHelper.verifyProductDetailsConsistency(page,"Sauce Labs Backpack");
    }}

