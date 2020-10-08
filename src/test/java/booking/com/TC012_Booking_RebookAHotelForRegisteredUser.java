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


public class TC012_Booking_RebookAHotelForRegisteredUser extends TestBase {
    SoftAssertions softly = new SoftAssertions();
    private static Logger LOGGER = LogManager.getLogger(TC012_Booking_RebookAHotelForRegisteredUser.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Booking Details of  registered user")
    @Description("To validate booking details of the registered user")
    @Step("To run TC012_Booking_RebookHotelForRegisteredUser")

    public void rebookHotelForRegisteredUser() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            CreateExtentReport(this.getClass().getName(), "Verify price of previously booked hotel");
            getBookingHomepage().clickOnSignIn().setUsername().setSignInPassword().selectUserIcon().
                    clickOnBookingLinkOnRegisteredUserIcon().getHotelModernoPreviousPrice().selectUserIcon().clickOnSignOut();

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
