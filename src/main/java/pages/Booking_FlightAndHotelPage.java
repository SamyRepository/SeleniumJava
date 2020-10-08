package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Report;

public class Booking_FlightAndHotelPage extends PageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(Booking_FlightBookingPage.class);


    @FindBy(css = ".as-oil__btn-optin.as-js-optin")
    private WebElement AcceptAlert;

    @FindBy(xpath = "//input[@placeholder='Stadt oder Flughafen']")
    private WebElement Source;

    @FindBy(xpath = "//input[@placeholder='Stadt, Küste, Region, Insel...']")
    private WebElement Destination;

    @FindBy(css = ".guestsText.lmn-sw-responsive-form-field.lmn-sw-tooltip-responsive__text")
    private WebElement SetNumberOfPassengersTab;

    @FindBy(css = ".select-selected")
    private WebElement SetClass;

    @FindBy(css = ".btn.btn-cta.btn-block.lmn-sw-submitHotel")
    private WebElement SearchButton;

    @FindBy(css = ".css-178x4z3.ezsypn71")
    private WebElement FlightAndHotelPageHeader;

    Booking_FlightAndHotelPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
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
        return FlightAndHotelPageHeader.isDisplayed();
    }

    public Booking_FlightAndHotelPage setSource() {
        AcceptAlert.click();
        Source.click();
        String source = "FRA";
        Source.sendKeys(source);
        driver.findElement(By.xpath("//div[@data-value='FRA' and @class='optionGroup-select-item']")).click();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Source is set as" + source);
        Source.sendKeys(Keys.TAB);
        return this;
    }

    public Booking_FlightAndHotel_CalenderPage setDestination() {
        Destination.click();
        String destination = "Mumbai, Indien";
        Destination.sendKeys(destination);
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Destination is set as" + destination);
        Destination.sendKeys(Keys.TAB);
        return new Booking_FlightAndHotel_CalenderPage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public Booking_FlightAndHotel_NumberOfPassengers setNumberOfPassengers() {
        SetNumberOfPassengersTab.click();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "click to set number of passengers");
        return new Booking_FlightAndHotel_NumberOfPassengers(driver, EXTENT_TEST_LOGGER, helper);
    }

    public Booking_FlightAndHotel_SelectClassOfJourney setClassOfJourney() {
        SetClass.click();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Click to set class of the journey");
        return new Booking_FlightAndHotel_SelectClassOfJourney(driver, EXTENT_TEST_LOGGER, helper);
    }

    public Booking_FlightAndHotelSearchResult clickOnSearchButton() {
        SearchButton.click();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Click on the search button");
        return new Booking_FlightAndHotelSearchResult(driver, EXTENT_TEST_LOGGER, helper);
    }
}
