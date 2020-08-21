package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Booking_RegisteredUserBookedTripsPage extends PageBase {

    @FindBy(css = ".mtr-trip-reservations")
    private List<WebElement> AllBookedTripsContainer;

    @FindBy(xpath = "//div[@class='bui-f-font-heading']")
    private List<WebElement> BookedHotelNames;

    @FindBy(css = ".bui-f-font-body.mtr-trip__subtitle")
    private List<WebElement> BookedHotelDateDuration;

    @FindBy(css = ".mtr-product-details__content")
    private List<WebElement> BookedTripDetailsContainer;

    @FindBy(xpath = "((//div[@class='bui-dropdown mtr-reservation-context bui-dropdown--end'])[3]")
    private WebElement HotelModernoDropdown;

    @FindBy(xpath = "(//span[@class='bui-dropdown-menu__text'])[5]")
    private WebElement HotelModernoBookAgainTab;

    @FindBy(xpath = "(//div[@class='bui-f-font-display_one mtr-price mtr-price--past'])[3]")
    private WebElement HotelModernoPreviousPrice;

    @FindBy(xpath = "//a[@class='bui-link bui-link--primary close_warning']")
    private WebElement closeAlert;

    Booking_RegisteredUserBookedTripsPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        return false;
    }

    public Booking_RegisteredUserBookedTripsPage getAllBookedHotels() {
        helper.getElementsTextValues(BookedHotelNames);
        return this;
    }

    public List<String> getBookedTripsDetail() {
        return helper.getElementsTextValues(BookedTripDetailsContainer);
    }

    public Booking_RegisteredUserBookedTripsPage clickOnDropdownOfHotelModerno() {
        closeAlert.click();
        HotelModernoDropdown.click();
        return this;
    }

    public Booking_HotelModernoRebookPage clickOnBookAgainTab() {
        HotelModernoBookAgainTab.click();
        return new Booking_HotelModernoRebookPage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public String getHotelModernoPreviousPrice() {
        return helper.ElementGetText(HotelModernoPreviousPrice);
    }

}