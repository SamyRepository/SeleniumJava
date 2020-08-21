package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Booking_FlightAndHotelSearchResult extends PageBase {
    Booking_FlightAndHotelSearchResult(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        return false;
    }
}
