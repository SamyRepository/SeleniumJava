package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Booking_FlightAndHotel_SelectClassOfJourney extends PageBase {

    @FindBy(xpath = "//div[@data-value='Y']")
    private WebElement Economy;

    @FindBy(xpath = "//div[@data-value='P']")
    private WebElement PremiumEconomy;

    @FindBy(xpath = "//div[@data-value='C']")
    private WebElement Business;

    @FindBy(xpath = "//div[@data-value='F']")
    private WebElement FirstClass;


    Booking_FlightAndHotel_SelectClassOfJourney(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        try {
            return waitUntilPageLoaded();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean waitUntilPageLoaded() {
        return driver.findElement(By.cssSelector("same-as-selected")).isDisplayed();
    }

    public Booking_FlightAndHotel_SelectClassOfJourney selectEconomyClass() {
        Economy.click();
        return this;
    }
    public Booking_FlightAndHotel_SelectClassOfJourney selectPremiumEconomyClass(){
        PremiumEconomy.click();
        return this;
    }
    public Booking_FlightAndHotelPage selectBusinessClass(){
        Business.click();
        return new Booking_FlightAndHotelPage(driver,EXTENT_TEST_LOGGER,helper);
    }
    public Booking_FlightAndHotel_SelectClassOfJourney selectFirstClass(){
        FirstClass.click();
        return this;
    }
}

