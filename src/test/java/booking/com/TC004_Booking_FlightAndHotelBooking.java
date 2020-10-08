package booking.com;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import utils.Report;

public class TC004_Booking_FlightAndHotelBooking extends TestBase {
    private SoftAssertions softly= new SoftAssertions();
    private static Logger LOGGER = LogManager.getLogger(TC004_Booking_FlightAndHotelBooking.class);
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("To verify flight and Hotel booking")
    @Story("Flight and Hotel Booking")
    @Step("Start TC004_Booking_FlightAndHotelBooking")

    public void verifyFlightAndHotelBooking() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            CreateExtentReport(this.getClass().getName(), "Booking Flight and Hotel together");
            getBookingHomepage().clickOnFlightAndHotelLink().setSource().setDestination().setDepartureDate()
                    .setReturnDate().setNumberOfPassengers().increaseNumberOfAdults().increaseNumberOfChildren().increaseNumberOfRooms()
                    .setChildAgeFirstRoom().setChildAgeSecondRoom().selectBusinessClass().clickOnSearchButton();
        } catch (Exception exc) {
            LOGGER.error("failure reason is" + exc.getMessage());
            PostConditionWithQuitDriver(exc);
            Assert.fail("failure reason is" + exc.getMessage());
        }
    }
    @AfterClass
    void tearDown() {
        try {
            softly.assertAll();
            PostCondition();
        } catch (AssertionError Error) {
            EXTENT_TEST_LOGGER.log(LogStatus.ERROR, Error.getLocalizedMessage(), EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            PostConditionWithQuitDriver(Error);
        }
    }
}
