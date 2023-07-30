package com.qa.stepdef;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleMapsStepDef {

    public AndroidDriver driver = SharedDriverContext.getDriver();
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @AndroidFindBy(accessibility = "Search for Restaurants")
    public WebElement restaurantNearMe;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/mylocation_button")
    public WebElement mylocationBtn;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/search_list_layout")
    public WebElement restaurantSearchList;

    @AndroidFindBy(accessibility = "More filters")
    public WebElement restaurantFilterBtn;

    @AndroidFindBy(accessibility = "Open now")
    public WebElement restaurantOpenNow;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Apply\"]")
    public WebElement applyFilter;

    @AndroidFindBy(accessibility = "Clear")
    public WebElement clearFilter;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/search_omnibox_text_box")
    public WebElement searchBox;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/search_omnibox_edit_text")
    public WebElement searchBoxEdit;

    @AndroidFindBy(accessibility = "Marina Beach")
    public WebElement marinaBeach;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Directions to Marina Beach\"]")
    public WebElement directionsToBeach;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[2]")
    public WebElement distanceToBeach;


    public GoogleMapsStepDef() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Given("Open Google Maps")
    public void openGoogleMaps() {
        wait.until(ExpectedConditions.visibilityOf(restaurantNearMe));
        Assert.assertEquals("Google Map Opened", "Enter compass mode", mylocationBtn.getAttribute("contentDescription"));
    }

    @Then("Search for Nearby Restaurants")
    public void searchForNearbyRestaurants() {
        restaurantNearMe.click();
        wait.until(ExpectedConditions.visibilityOf(restaurantSearchList));
    }

    @Then("Open only opened restaurants nearby")
    public void openOnlyOpenedRestaurantsNearby() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(restaurantFilterBtn));
        restaurantFilterBtn.click();
        Thread.sleep(300);
        wait.until(ExpectedConditions.visibilityOf(restaurantOpenNow));
        restaurantOpenNow.click();
        applyFilter.click();
    }

    @And("Confirm it is open")
    public void confirmItIsOpen() {
        Assert.assertTrue("Only Open Now restaurants are displayed", restaurantOpenNow.isDisplayed());
    }

    @And("Get back to Map view")
    public void getBackToMapView() {
        wait.until(ExpectedConditions.visibilityOf(clearFilter));
        clearFilter.click();
        wait.until(ExpectedConditions.visibilityOf(mylocationBtn));
        mylocationBtn.click();
    }

    @Given("Search for Marina Beach")
    public void searchForMarinaBeach() {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.click();
        searchBoxEdit.sendKeys("Marina Beach");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        wait.until(ExpectedConditions.visibilityOf(marinaBeach));
    }

    @And("Get Directions")
    public void getDirections() {
        wait.until(ExpectedConditions.visibilityOf(directionsToBeach));
        directionsToBeach.click();
    }

    @And("Get the Distance")
    public void getDistance() {
        wait.until(ExpectedConditions.visibilityOf(distanceToBeach));
        Assert.assertEquals("Shortest distance to Beach is available", "(4.8 km)", distanceToBeach.getText());
    }

}
