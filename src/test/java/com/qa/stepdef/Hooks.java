package com.qa.stepdef;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class Hooks {
    public static AndroidDriver driver;
    protected static Properties properties;
    private InputStream propStream;

    @Before
    public void initialize() throws Exception {
//        try {
        properties = new Properties();
        String propertiesFileName = "config.properties";
        propStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
        properties.load(propStream);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("androidDeviceName"));
        caps.setCapability("newCommandTimeout", 300);
        URL url = new URL(properties.getProperty("appiumURL"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, properties.getProperty("androidAutomationName"));
        caps.setCapability("avd", properties.getProperty("androidDeviceName"));
        caps.setCapability("appActivity", properties.getProperty("androidAppActivity"));
        caps.setCapability("appPackage", properties.getProperty("androidAppName"));
        caps.setCapability("noReset", "true");
        caps.setCapability("autoGrantPermissions", true);
        driver = new AndroidDriver(url, caps);
        SharedDriverContext.setDriver(driver);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
    }

    @After
    public void quit() throws IOException {
        if (propStream != null) propStream.close();
        if (driver != null) driver.quit();
    }
}
