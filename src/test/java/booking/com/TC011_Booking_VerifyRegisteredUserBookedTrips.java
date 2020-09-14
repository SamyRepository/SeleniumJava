package booking.com;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ksrtc.TestBase;
import utils.Report;

import java.util.List;

public class TC011_Booking_VerifyRegisteredUserBookedTrips extends TestBase {
    SoftAssertions softly = new SoftAssertions();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verifying items of Register button")
    @Story("Registered user booked trips")
    @Step("Start TC011_Booking_VerifyRegisteredUserBookedTrips")

    public void getBookedTripsForRegisteredUser() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            CreateExtentReport(this.getClass().getName(), "Verify all hotels booked in the year 2012");
            List<String> allBookedTripsList = getBookingHomepage().clickOnSignIn().setUsername().setSignInPassword().selectUserIcon().clickOnBookingLinkOnRegisteredUserIcon().getBookedTripsDetail();
            System.out.println("All booked hotels for the registered user are:\n" + allBookedTripsList);
            for (int i = 0; i < allBookedTripsList.size(); i++) {
                String individualTripDetailIn2012 = allBookedTripsList.get(i);
                if (individualTripDetailIn2012.contains("2012"))
                    System.out.println(individualTripDetailIn2012);
                EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Booked hotel is 2012: " + individualTripDetailIn2012);
            }
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
