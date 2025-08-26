package com.saucedemo.tests.positive;

import com.microsoft.playwright.junit.UsePlaywright;
import com.saucedemo.helpers.LoginHelper;
import com.saucedemo.helpers.ProductsHelper;
import com.saucedemo.utils.TestBase;
import org.junit.jupiter.api.Test;

@UsePlaywright
public class CompleteProcessTest extends TestBase {

    @Test
    void completeOrder(){
        LoginHelper.loginValidUser(page);
        ProductsHelper.addProductToCart(page,"#add-to-cart-sauce-labs-backpack");
        ProductsHelper.addProductToCart(page,"#add-to-cart-sauce-labs-onesie");
        ProductsHelper.goToCart(page);
        ProductsHelper.checkout(page,"Angela","Shalievska","1000");
        ProductsHelper.verifyCheckoutTotal(page);
        String [] expectedPrices ={"$29.99","$7.99"};
        ProductsHelper.verifyProductPrices(page, expectedPrices);
        ProductsHelper.finishOrderAndVerifyConfirmation(page);
    }
}
