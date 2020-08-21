package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Booking_FlightAndHotel_NumberOfPassengers extends PageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(Booking_FlightBookingPage.class);

    @FindBy(css = ".guestsText.lmn-sw-responsive-form-field.lmn-sw-tooltip-responsive__text")
    private WebElement NumberOfPassengersTab;

    @FindBy(xpath = "//div[@class='guestsSelectionControls' and @data-type='adults']/div/span[1]")
    private WebElement DecreaseNumberOfAdults;

    @FindBy(xpath = "//div[@class='lmn-sw-selectionControls__control lmn-sw-selectionControls__control-plus']/span[1]")
    private WebElement IncreaseNumberOfAdults;

    @FindBy(xpath = "//div[@class='lmn-sw-selectionControls__control lmn-sw-selectionControls__control-minus disabled-control']/span[1]")
    private WebElement DecreaseNumberOfChildren;

    @FindBy(xpath = "(//div[@class='lmn-sw-selectionControls__control lmn-sw-selectionControls__control-plus'])[2]")
    private WebElement IncreaseNumberOfChildren;

    @FindBy(css = ".addRoom")
    private WebElement AddRoom;

    @FindBy(xpath = "(//div[@class='childrenAgeResp'])[1]")
            private WebElement FirstRoomChildAge;

    @FindBy(xpath = "(//div[@class='childrenAgeResp'])[2]")
    private WebElement SecondRoomChildAge;


    Booking_FlightAndHotel_NumberOfPassengers(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
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
        return driver.findElement(By.cssSelector(".roomTitle")).isDisplayed();
    }

    public Booking_FlightAndHotel_NumberOfPassengers setNumberOfPassengers(){
        NumberOfPassengersTab.click();
        return this;
    }

    public Booking_FlightAndHotel_NumberOfPassengers increaseNumberOfAdults() {
        IncreaseNumberOfAdults.click();
        return this;
    }

    public Booking_FlightAndHotel_NumberOfPassengers increaseNumberOfChildren() {
        IncreaseNumberOfChildren.click();
        return this;
    }

    public Booking_FlightAndHotel_NumberOfPassengers increaseNumberOfRooms() {
        AddRoom.click();
        return this;
    }

    public Booking_FlightAndHotel_NumberOfPassengers setChildAgeFirstRoom(){
        FirstRoomChildAge.click();
        driver.findElement(By.xpath("(//div[@class='childrenAgesResp-select']/select/option[5])[1]")).click();
        return this;
    }
    public Booking_FlightAndHotel_SelectClassOfJourney setChildAgeSecondRoom(){
        SecondRoomChildAge.click();
        driver.findElement(By.xpath("(//div[@class='childrenAgesResp-select']/select)[2]/option[8]")).click();
        return new Booking_FlightAndHotel_SelectClassOfJourney(driver,EXTENT_TEST_LOGGER,helper);
    }

}
