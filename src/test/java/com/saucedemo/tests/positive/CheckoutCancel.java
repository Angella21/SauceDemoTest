package com.saucedemo.tests.positive;

import com.microsoft.playwright.junit.UsePlaywright;
import com.saucedemo.helpers.LoginHelper;
import com.saucedemo.helpers.ProductsHelper;
import com.saucedemo.utils.Constants;
import com.saucedemo.utils.TestBase;
import org.junit.jupiter.api.Test;
@UsePlaywright
public class CheckoutCancel extends TestBase {
    @Test
    public void checkoutCancel() {
        LoginHelper.loginValidUser(page);

        // Add product first
        ProductsHelper.addProductToCart(page,"#add-to-cart-sauce-labs-backpack");

        ProductsHelper.goToCart(page);

        ProductsHelper.checkout(page, Constants.FIRST_NAME,Constants.LAST_NAME, Constants.POSTAL_CODE);
        //page.locator("#cancel").click();
        // page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Continue Shopping")).click();
        ProductsHelper.cancelCheckout(page);


    }
}
