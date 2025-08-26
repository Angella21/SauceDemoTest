package com.saucedemo.tests.positive;

import com.saucedemo.helpers.LoginHelper;
import com.saucedemo.helpers.ProductsHelper;
import com.saucedemo.utils.TestBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortingByPriceTest extends TestBase
{

    @Test
        void sortByPriceLow() {
                LoginHelper.loginValidUser(page);

                ProductsHelper.verifyPriceSorting(page,"Price (low to high)", true);
                page.locator(".product_sort_container").selectOption("Price (low to high)");

            }


    @Test
    void sortByPriceHigh() {
            LoginHelper.loginValidUser(page);

        ProductsHelper.verifyPriceSorting(page,"Price (high to low)", false);

        }
    }
