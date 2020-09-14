package booking.com;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ksrtc.TestBase;
import utils.Report;


public class TC012_Booking_RebookAHotelForRegisteredUser extends TestBase {
    SoftAssertions softly = new SoftAssertions();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Booking Details of  registered user")
    @Description("To validate booking details of the registered user")
    @Step("To run TC012_Booking_RebookHotelForRegisteredUser")

    public void rebookHotelForRegisteredUser() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            CreateExtentReport(this.getClass().getName(), "Verify price of previously booked hotel");
            String previousPrice = getBookingHomepage().clickOnSignIn().setUsername().setSignInPassword().selectUserIcon().
                    clickOnBookingLinkOnRegisteredUserIcon().getHotelModernoPreviousPrice();
            System.out.println("Hotel Moderno is previously booked at a price of :" + previousPrice);
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Hotel Moderno is previously booked at a price of :" +previousPrice);

        } catch (Exception e) {
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
