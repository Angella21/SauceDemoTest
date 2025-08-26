package com.saucedemo.tests.positive;

import com.microsoft.playwright.junit.UsePlaywright;
import com.saucedemo.helpers.LoginHelper;
import com.saucedemo.helpers.ProductsHelper;
import com.saucedemo.utils.TestBase;
import org.junit.jupiter.api.Test;

@UsePlaywright
public class RemoveProductTest extends TestBase {

    @Test
    void removeProduct() {

        LoginHelper.loginValidUser(page);

        ProductsHelper.addProductToCart(page, "#add-to-cart-sauce-labs-backpack");
        ProductsHelper.removeProduct(page, "#remove-sauce-labs-backpack");
        ProductsHelper.goToCart(page);
        ProductsHelper.printCartStatus(page);

    }
}
