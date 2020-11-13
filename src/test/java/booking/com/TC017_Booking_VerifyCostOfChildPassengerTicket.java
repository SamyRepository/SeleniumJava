package booking.com;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.Booking_NumberOfPassengers;
import pages.Booking_OneWayBookingResultPage;
import utils.Report;

public class TC017_Booking_VerifyCostOfChildPassengerTicket extends TestBase {
    SoftAssertions softly = new SoftAssertions();
    private static Logger LOGGER = LogManager.getLogger(TC017_Booking_VerifyCostOfChildPassengerTicket.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("To verify cost of child passenger ticket ")
    @Story("Flight Booking")
    @Step("Start TC017_Booking_VerifyCostOfChildPassengerTicket")

    public void verifyCostOfChildPassengerTicket() throws InterruptedException {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            CreateExtentReport(this.getClass().getName(), "Verify cost of child passenger ticket");
            Booking_NumberOfPassengers booking_numberOfPassengers = getBookingHomepage().clickOnFlightLinkGoToFlightBookingPage().selectOneWayJourney().enterSourceOfJourney()
                    .enterDestinationOfJourney().selectCurrentDate();

            Booking_OneWayBookingResultPage booking_oneWayBookingResultPage = booking_numberOfPassengers.selectNoOfPassengers().setNumberOfChildPassengers().
                    setChildAge(Booking_NumberOfPassengers.SetAgeOfChildPassenger.SET_AGE_OF_CHILD_PASSENGER_0.getLabel()).clickOnSearchButton().selectCheapestFlights();

            Double flightRateWithAge0 = booking_oneWayBookingResultPage.selectCostOfFirstFlightOfCheapest();
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Flight price with one adult and one infant with age less than one year: " + flightRateWithAge0);

            booking_oneWayBookingResultPage = booking_numberOfPassengers.selectNoOfPassengers().setChildAge(Booking_NumberOfPassengers.SetAgeOfChildPassenger.SET_AGE_OF_CHILD_PASSENGER_1.getLabel()).clickOnSearchButton().selectCheapestFlights();

            Double flightRateWithAge1 = booking_oneWayBookingResultPage.selectCostOfFirstFlightOfCheapest();
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Flight price with one adult and one infant with age one year: " + flightRateWithAge1);

            booking_oneWayBookingResultPage = booking_numberOfPassengers.selectNoOfPassengers().setChildAge(Booking_NumberOfPassengers.SetAgeOfChildPassenger.SET_AGE_OF_CHILD_PASSENGER_2.getLabel()).clickOnSearchButton().selectCheapestFlights();

            Double flightRateWithAge2 = booking_oneWayBookingResultPage.selectCostOfFirstFlightOfCheapest();
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Flight price with one adult and one infant with age two years: " + flightRateWithAge2);

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



