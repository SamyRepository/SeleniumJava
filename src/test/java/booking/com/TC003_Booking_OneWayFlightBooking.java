package booking.com;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.Booking_FlightBookingPage;
import ksrtc.TestBase;
import utils.Report;

public class TC003_Booking_OneWayFlightBooking extends TestBase {
    private SoftAssertions softly = new SoftAssertions();
    private static Booking_FlightBookingPage booking_flightBookingPage;

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
            PostConditionWithQuitDriver();
        } finally {
            softly.assertAll();
        }
    }
    @AfterClass
    void tearDown() {
        try {
            softly.assertAll();
            PostCondition();
        } catch (AssertionError Error) {
            EXTENT_TEST_LOGGER.log(LogStatus.ERROR, Error.getLocalizedMessage(), EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            PostConditionWithQuitDriver();
        }
    }
}