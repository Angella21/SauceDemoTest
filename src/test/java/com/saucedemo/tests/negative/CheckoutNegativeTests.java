package com.saucedemo.tests.negative;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.saucedemo.helpers.LoginHelper;
import com.saucedemo.helpers.ProductsHelper;
import com.saucedemo.utils.TestBase;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;

@UsePlaywright
public class CheckoutNegativeTests extends TestBase {
    @Test

    public void shouldNotAllowCheckoutWhenFieldsAreEmpty() {

            LoginHelper.loginValidUser(page);
            // Add product first
            ProductsHelper.addProductToCart(page,"#add-to-cart-sauce-labs-backpack");
            ProductsHelper.goToCart(page);
            ProductsHelper.checkoutWithMissingFields(page,true,false,true); //setting second values as false-to get error message for LastName

        String screenshotPath = "screenshots/MissingFieldsError.jpg";
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));
        }

    }
