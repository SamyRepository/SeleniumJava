package booking.com;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ksrtc.TestBase;
import utils.Report;

import java.util.Arrays;
import java.util.List;

public class TC006_Booking_Verify_TopHeader extends TestBase {
    private SoftAssertions softly = new SoftAssertions();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifying Top header of Booking homepage ")
    @Story("Booking Homepage")
    @Step(" Start TC006_Booking_Verify_TopHeader")

    public void verifyTopHeaderOfHomepage() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            CreateExtentReport(this.getClass().getName(), "Verify top header items of Booking homepage");
            List<String> topHeaderItems = getBookingHomepage().getTopHeaderItems();
            System.out.println("Top header Items" + topHeaderItems);
            String[] topHeaderItemsArray = topHeaderItems.get(0).split("\\r?\\n");
            System.out.println(Arrays.asList(topHeaderItemsArray));
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Top header items are"+topHeaderItems, EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            List<String> expectedTopHeader=Arrays.asList("€","List your Property","Register" ,"Sign in");
            softly.assertThat(expectedTopHeader.containsAll(Arrays.asList(topHeaderItemsArray)));

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
