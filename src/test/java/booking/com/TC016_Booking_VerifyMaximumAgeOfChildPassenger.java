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

public class TC016_Booking_VerifyMaximumAgeOfChildPassenger extends TestBase {
    private SoftAssertions softly = new SoftAssertions();
    private static Logger LOGGER = LogManager.getLogger(TC016_Booking_VerifyMaximumAgeOfChildPassenger.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("To verify maximum allowed age of child passenger ")
    @Story("Flight Booking")
    @Step("Start TC016_Booking_VerifyMaximumAgeOfChildPassenger")

    public void verifyMaximumAgeOfChildPassenger() throws InterruptedException {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            CreateExtentReport(this.getClass().getName(), "Verify maximum age of the child passenger");
           getBookingHomepage().clickOnFlightLinkGoToFlightBookingPage().selectOneWayJourney().enterSourceOfJourney().enterDestinationOfJourney()
                    .selectCurrentDate().selectNoOfPassengers().setNumberOfAdultPassenger().setNumberOfChildPassengers().getAllowedAgesOfChildPassengers();
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
