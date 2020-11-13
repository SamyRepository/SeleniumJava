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

public class TC019_Booking_VerifyDirectFlightWithoutTransit extends TestBase {
    SoftAssertions softly = new SoftAssertions();
    private static Logger LOGGER = LogManager.getLogger(TC018_Booking_ToSelectSpecificPageOfSearchResult.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("To verify direct flights")
    @Story("Direct Flight Booking")
    @Step("To run TC018_Booking_VerifyDirectFlightWithoutTransit")

    public void verifyDirectFlight() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            CreateExtentReport(this.getClass().getName(), "To open any specific page of the search result");
           List<String> sourceDestinationAndTransitAirportNamesList=getBookingHomepage().clickOnFlightLinkGoToFlightBookingPage().selectOneWayJourney()
                   .enterSourceOfJourney().enterDestinationOfJourney().selectCurrentDate().clickOnSearchButton().selectDirectFlights()
                   .selectFirstFlightOfResultPage().getSourceTransitAndDestinationAirportNames();
            System.out.println(sourceDestinationAndTransitAirportNamesList);

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