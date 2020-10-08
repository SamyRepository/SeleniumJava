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

import java.util.Arrays;
import java.util.List;

public class TC015_Booking_VerifyClassesOfFlightJourney extends TestBase {
    private SoftAssertions softly = new SoftAssertions();
    private static Logger LOGGER = LogManager.getLogger(TC015_Booking_VerifyClassesOfFlightJourney.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("To verify different classes of flight tickets ")
    @Story("Flight Booking")
    @Step("Start TC015_Booking_VerifyClassesOfFlightJourney")
    public void verifyClassesOfFlight() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());

        try {
            CreateExtentReport(this.getClass().getName(), "Verify classes of flight tickets");
            List<String> classesOfJourney = getBookingHomepage().clickOnFlightLinkGoToFlightBookingPage().getClassesOfFlightTickets();
            String[] classesOfJourneyArray = classesOfJourney.get(0).split("\\r?\\n");
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Different classes of flight tickets are:" + classesOfJourney);
            List<String> ExpectedClassesOfJourney = Arrays.asList("Economy1", "Premium economy", "Business", "First class");
            softly.assertThat(Arrays.asList(classesOfJourneyArray).equals(ExpectedClassesOfJourney)).isTrue();

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


