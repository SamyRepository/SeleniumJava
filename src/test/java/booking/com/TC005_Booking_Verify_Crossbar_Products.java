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

public class TC005_Booking_Verify_Crossbar_Products extends TestBase {
    private SoftAssertions softly = new SoftAssertions();
    private static Logger LOGGER = LogManager.getLogger(TC005_Booking_Verify_Crossbar_Products.class);
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("To verify Crossbar products")
    @Story("Booking Homepage")
    @Step("Start TC005_Booking_Verify_Crossbar_Products")

    public void verifyCrossbarProducts() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());

        try {
            CreateExtentReport(this.getClass().getName(), "Verify crossbar of Booking homepage");
            List<String> crossbarItems = getBookingHomepage().getCrossbarItems();
            String[] crossbarItemsArray = crossbarItems.get(0).split("\\r?\\n");
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Booking homepage is loaded", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            System.out.println("Crossbar items are:" + crossbarItems);
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Crossbar products are:" + crossbarItems.toString());
            List<String> expectedItems = Arrays.asList("Stays", "Flights", "Flight + Hotel", "Car rentals", "Attractions", "Airport taxis");

            //ContainsAll checks for all objects(Collection of objects) while contain checks for only one object
            softly.assertThat(Arrays.asList(crossbarItemsArray).containsAll(expectedItems)).isTrue();
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
