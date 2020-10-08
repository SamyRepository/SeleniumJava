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

public class TC008_Booking_VerifyFooter_DestinationWeLove extends TestBase {
    SoftAssertions softly = new SoftAssertions();
    private static Logger LOGGER = LogManager.getLogger(TC008_Booking_VerifyFooter_DestinationWeLove.class);

    @Test
    @Story("Booking Homepage")
    @Severity(SeverityLevel.MINOR)
    @Step("Run TC008_VerifyFooter_DestinationWeLove")
    @Description("Destination we love on the footer of Booking site")

    public void getDestinationWeLoveItemsFromTheFooter() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            CreateExtentReport(this.getClass().getName(), "Verify destination we love items on Booking website");
            List<String> destinationWeLoveItems = getBookingHomepage().getLovedDestinationItems();
            System.out.println(destinationWeLoveItems);
            String[] destinationWeLoveItemsArray = destinationWeLoveItems.get(0).split("\\r?\\n");
            System.out.println(Arrays.asList(destinationWeLoveItemsArray));
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "'Destination we love' items are" + destinationWeLoveItems.toString());

            List<String> expectedDestinationsWeLoveItems = Arrays.asList("Zanzibar", "834 properties", "Bihar", "761 properties", "Phuket Province",
                    "5,495 properties", "Tenerife", "9,605 properties", "Bora Bora", "59 properties", "England", "73,969 properties",
                    "Santorini", "1,762 properties", "Ibiza", "1,642 properties", "Bali", "12,680 properties", "Isle of Wight",
                    "933 properties", "Uttar Pradesh", "4,381 properties", "Ras Al Khaimah", "112 properties", "Hawaii",
                    "5,515 properties", "Mykonos", "1,455 properties", "Jersey", "89 properties", "Lake District", "2,403 properties",
                    "Guernsey", "61 properties", "Texel", "413 properties", "Cornwall", "5,274 properties");
            softly.assertThat(expectedDestinationsWeLoveItems.containsAll(Arrays.asList(destinationWeLoveItemsArray)));

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
