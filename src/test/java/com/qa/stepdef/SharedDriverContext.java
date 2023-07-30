package com.qa.stepdef;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class SharedDriverContext {
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void setDriver(AndroidDriver driver) {
        SharedDriverContext.driver = driver;
    }
}

