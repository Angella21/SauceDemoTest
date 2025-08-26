package com.saucedemo.tests.positive;

import com.microsoft.playwright.junit.UsePlaywright;
import com.saucedemo.helpers.LoginHelper;
import com.saucedemo.helpers.ProductsHelper;
import com.saucedemo.utils.TestBase;
import org.junit.jupiter.api.Test;
@UsePlaywright
public class AddMultipleProducts extends TestBase {

    @Test
    void sortByPriceHigh() {

            LoginHelper.loginValidUser(page);

            ProductsHelper.addProductsToCart(page,"#add-to-cart-sauce-labs-backpack",
                    "#add-to-cart-sauce-labs-bike-light",
                    "#add-to-cart-sauce-labs-fleece-jacket",
                    "#add-to-cart-sauce-labs-bolt-t-shirt");

            ProductsHelper.goToCart(page);

           ProductsHelper.verifyCartBadge(page,4);
        }
    }