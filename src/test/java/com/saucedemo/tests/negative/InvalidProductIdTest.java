package com.saucedemo.tests.negative;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.saucedemo.helpers.LoginHelper;
import com.saucedemo.utils.TestBase;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;

@UsePlaywright
public class InvalidProductIdTest extends TestBase {

    @Test
    void shouldNotAddProductWhenIdIsInvalid(){
        LoginHelper.loginValidUser(page);

        String invalidProductId = "#add-to-cart-sauce-labs-backpack1";
        Locator productLocator = page.locator(invalidProductId);

        if (productLocator.count() == 0) {
            System.out.println("Product with ID " + invalidProductId + " not found.");
            // Ensure the "screenshots" directory exists or create it
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Take a screenshot before failing the test
            String screenshotPath = "screenshots/InvalidProductIdTest_Failure.jpg";
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));
            fail("Product should not exist with invalid ID: " + invalidProductId);

        } else {

            productLocator.click();
            System.out.println("Product was found and added.");
        }
    }

}
