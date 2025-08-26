package com.saucedemo.helpers;


import com.microsoft.playwright.Locator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertHelper {
    public static void verifyVisible(Locator locator, String successMessage) {
        boolean visible = locator.isVisible();
        assertTrue(visible, "Element should be visible");
        System.out.println(successMessage);
    }

    public static void verifyNotVisible(Locator locator, String successMessage) {
        boolean notVisible = locator.isVisible();
        assertFalse(notVisible, "Element should NOT be visible");
        System.out.println(successMessage);
    }
    public static void verifyTrue(boolean condition, String successMessage) {
        assertTrue(condition, successMessage);
        System.out.println(successMessage);
    }
}
