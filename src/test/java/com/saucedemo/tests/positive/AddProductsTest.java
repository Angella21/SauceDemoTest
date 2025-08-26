package com.saucedemo.tests.positive;

import com.microsoft.playwright.junit.UsePlaywright;
import com.saucedemo.helpers.LoginHelper;
import com.saucedemo.helpers.ProductsHelper;
import com.saucedemo.utils.TestBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@UsePlaywright
public class AddProductsTest extends TestBase {
    @Test
    void AddProduct() {

        LoginHelper.loginValidUser(page);

        ProductsHelper.addProductToCart(page, "#add-to-cart-sauce-labs-backpack");

        ProductsHelper.goToCart(page);
        String [] expectedPrices = {"$29.99"};
        ProductsHelper.verifyProductPrices(page, expectedPrices);

        ProductsHelper.verifyCartBadge(page,1);
        ProductsHelper.printCartStatus(page);
    }



}