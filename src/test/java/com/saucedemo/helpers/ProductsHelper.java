package com.saucedemo.helpers;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.options.AriaRole.BUTTON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductsHelper {
    public static void addProductToCart(Page page, String productId) {
        page.locator(productId).click();
    }

    public static void addProductsToCart(Page page, String... productIds) {
        for (String id : productIds) {


            page.locator(id).click();
        }
    }
    // Navigate to the cart page
    public static void goToCart(Page page) {
        page.locator(".shopping_cart_link").click();
    }

    // Verify product price in cart
    public static void verifyProductPrices(Page page, String[] expectedPrices) {
        var priceLocators = page.locator(".inventory_item_price");
        int itemCount = priceLocators.count();

        if (itemCount != expectedPrices.length) {
            throw new IllegalArgumentException("Number of products on page (" + itemCount +
                    ") does not match number of expected prices (" + expectedPrices.length + ")");
        }

        for (int i = 0; i < itemCount; i++) {
            String actualPrice = priceLocators.nth(i).textContent().trim();
            String expectedPrice = expectedPrices[i];

            assertEquals(expectedPrice, actualPrice,
                    "Price mismatch for product " + (i + 1) + ". Expected: " + expectedPrice + " but was: " + actualPrice);

            System.out.println("Product " + (i + 1) + " price verified: " + actualPrice);
        }
    }


    // Verify cart badge count
    public static void verifyCartBadge(Page page, int expectedCount) {
        String cartBadge = page.locator(".shopping_cart_badge").innerText();
        if (!cartBadge.equals(String.valueOf(expectedCount))) {
            throw new AssertionError("Expected cart badge to show " + expectedCount + " but was " + cartBadge);
        }
        else{
            System.out.println("Expected cart number products is correct " + expectedCount);
        }
    }

    // Check if the cart is empty
    public static boolean isCartEmpty(Page page) {
        return page.locator(".inventory_item_name").count() == 0;

    }

    // Print cart status
    public static void printCartStatus(Page page) {
        System.out.println(isCartEmpty(page) ? "The cart is empty!" : "There are items in the cart.");
    }

    // Remove a product from the cart by its remove button selector
    public static void removeProduct(Page page, String removeButtonSelector) {
        page.locator(removeButtonSelector).click();
    }


    public static void checkout(Page page, String firstName, String lastName, String postalCode) {
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Checkout")).click();
        page.locator("#first-name").fill(firstName);
        page.locator("#last-name").fill(lastName);
        page.locator("#postal-code").fill(postalCode);
        page.locator("#continue").click();
    }

    public static void countProducts(Page page, int expectedCount){

        Locator products = page.locator(".inventory_item");

        int actualCount = products.count();

        assertEquals(expectedCount,actualCount,"Expected 6 products but found" + actualCount);

        System.out.println("Verified that " + expectedCount + " of products are displayed");


    }

    public static void verifyPriceSorting(Page page, String sortOption, boolean ascending) {
        // Select sorting option
        page.locator(".product_sort_container").selectOption(sortOption);

        // Get first two product prices
        String firstPriceText = page.locator(".inventory_item_price").nth(0).innerText();
        String secondPriceText = page.locator(".inventory_item_price").nth(1).innerText();

        double firstPrice = Double.parseDouble(firstPriceText.replace("$", ""));
        double secondPrice = Double.parseDouble(secondPriceText.replace("$", ""));

        if (ascending) {
            assertTrue(firstPrice <= secondPrice,
                    "Expected ascending order, but got " + firstPrice + " vs " + secondPrice);
            System.out.println("Verified price sorting: low → high");
        } else {
            assertTrue(firstPrice >= secondPrice,
                    "Expected descending order, but got " + firstPrice + " vs " + secondPrice);
            System.out.println("Verified price sorting: high → low");
        }
    }


    public static void cancelCheckout(Page page) {
        // Start checkout

        page.locator("#cancel").click();

        // Go back to shopping
        //page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Continue Shopping")).click();

        assertTrue(page.locator(".app_logo").isVisible(), "Expected to be back on Products page");
        System.out.println("Verified: back on Products page after cancel.");
    }

    public static void checkoutWithMissingFields(Page page, boolean enterFirst, boolean enterLast, boolean enterPostal) {
        // Start checkout
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Checkout")).click();

        // Conditionally fill inputs
        if (enterFirst) {
            page.locator("#first-name").fill("Angela");
        }
        if (enterLast) {
            page.locator("#last-name").fill("Shalievska");
        }
        if (enterPostal) {
            page.locator("#postal-code").fill("1000");
        }

        // Try to continue
        page.locator("#continue").click();

        // Capture error text
        String errorText = page.locator("[data-test='error']").textContent().trim();

        // Verify correct error
        if (!enterFirst) {
            assertTrue(errorText.contains("First Name is required"),
                    "Expected missing first name error, but got: " + errorText);
        } else if (!enterLast) {
            assertTrue(errorText.contains("Last Name is required"),
                    "Expected missing last name error, but got: " + errorText);
        } else if (!enterPostal) {
            assertTrue(errorText.contains("Postal Code is required"),
                    "Expected missing postal code error, but got: " + errorText);
        }

        System.out.println("Verified error displayed correctly → " + errorText);
    }


    public static void verifyCheckoutTotal(Page page) {
        // Get all item prices in the checkout
        var priceLocators = page.locator(".inventory_item_price");
        int itemCount = priceLocators.count();

        double sum = 0.0;

        for (int i = 0; i < itemCount; i++) {
            String priceText = priceLocators.nth(i).textContent().trim(); // e.g. "$29.99"
            double price = Double.parseDouble(priceText.replace("$", ""));
            sum += price;
        }

        // Get subtotal from summary
        String subtotalText = page.locator(".summary_subtotal_label").textContent().trim();
        String subtotalValueText = subtotalText.contains(":")
                ? subtotalText.split(":")[1].trim()
                : subtotalText;

        double subtotalValue = Double.parseDouble(subtotalValueText.replace("$", ""));

        // Compare sum of individual prices with subtotal
        assertEquals(sum, subtotalValue,
                0.01, // allow tiny floating point differences
                "Expected subtotal to match sum of item prices, but got: " + subtotalValue + " vs " + sum);

        System.out.println("Subtotal matches sum of all item prices: $" + subtotalValue);
    }


    public static void finishOrderAndVerifyConfirmation(Page page) {
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Finish")).click();

        boolean isThankYouVisible = page.getByText("Thank you for your order!").isVisible();
        assertTrue(isThankYouVisible, "Expected thank you message to be visible after finishing order");

        System.out.println("Order confirmation message is visible");
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Back Home")).click();
        assertTrue(page.locator(".app_logo").isVisible(), "Expected to be back on Products page");
        System.out.println("Expected to be back on Products page.");
    }

    public static void verifyProductDetailsConsistency(Page page, String productName) {
        // Get description and price from inventory list
        String listDescription = page.locator(".inventory_item_desc").nth(0).innerText();
        String listPrice = page.locator(".inventory_item_price").nth(0).textContent().trim();

        // Open product details
        page.getByText(productName).click();

        // Get description and price from product details page
        String detailsDescription = page.locator(".inventory_details_desc.large_size").nth(0).innerText();
        String detailsPrice = page.locator(".inventory_details_price").nth(0).textContent().trim();

        // Assertions
        assertEquals(listDescription, detailsDescription,
                "Product description mismatch: list='" + listDescription + "', details='" + detailsDescription + "'");
        assertEquals(listPrice, detailsPrice,
                "Product price mismatch: list='" + listPrice + "', details='" + detailsPrice + "'");

        System.out.println("Verified: Description and price are consistent for " + productName);
    }
}