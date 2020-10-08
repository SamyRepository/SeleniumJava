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

import java.util.List;

public class TC011_Booking_VerifyRegisteredUserBookedTrips extends TestBase {
    SoftAssertions softly = new SoftAssertions();
    private static Logger LOGGER = LogManager.getLogger(TC011_Booking_VerifyRegisteredUserBookedTrips.class);

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verifying items of Register button")
    @Story("Registered user booked trips")
    @Step("Start TC011_Booking_VerifyRegisteredUserBookedTrips")

    public void getBookedTripsForRegisteredUser() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            CreateExtentReport(this.getClass().getName(), "Verify all hotels booked in the year 2012");
            getBookingHomepage().clickOnSignIn().setUsername().setSignInPassword().selectUserIcon()
                    .clickOnBookingLinkOnRegisteredUserIcon().getBookedTripsDetail().selectUserIcon().clickOnSignOut();

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
