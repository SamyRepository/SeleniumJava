package booking.com;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.Booking_OneWayBookingResultPage;
import utils.Report;

public class TC018_Booking_ToSelectSpecificPageOfSearchResult extends TestBase {
    SoftAssertions softly = new SoftAssertions();
    private static Logger LOGGER = LogManager.getLogger(TC017_Booking_VerifyCostOfChildPassengerTicket.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("To go to any page of the search result ")
    @Story("Flight Booking Result")
    @Step("Start TC018_Booking_ToSelectSpecificPageOfSearchResult")

    public void toGoToSpecificPageOfSearchResult() throws InterruptedException {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            CreateExtentReport(this.getClass().getName(), "To open any specific page of the search result");
            getBookingHomepage().clickOnFlightLinkGoToFlightBookingPage().selectOneWayJourney().enterSourceOfJourney()
                    .enterDestinationOfJourney().selectCurrentDate().clickOnSearchButton().clickOnParticularPage(Booking_OneWayBookingResultPage.GetPageOfSearchResult.PAGE2);
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