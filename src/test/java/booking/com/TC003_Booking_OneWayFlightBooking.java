package booking.com;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.Booking_FlightBookingPage;
import utils.Report;

public class TC003_Booking_OneWayFlightBooking extends TestBase {
    private SoftAssertions softly = new SoftAssertions();
    private static Booking_FlightBookingPage booking_flightBookingPage;
    private static Logger LOGGER = LogManager.getLogger(TC003_Booking_OneWayFlightBooking.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("To verify one-way flight booking")
    @Story("Flight Booking")
    @Step("Start TC003_Booking_OneWayFlightBooking")

    public void verifyOneWayFlightJourneyBooking() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());

        try {
            CreateExtentReport(this.getClass().getName(), "Booking one-way journey");
            getBookingHomepage().clickOnFlightLinkGoToFlightBookingPage().selectOneWayJourney().enterSourceOfJourney()
                    .enterDestinationOfJourney().selectCurrentDate().selectNoOfPassengers().setNumberOfAdultPassenger().clickOnSearchButton();
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Result page is displayed ", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
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