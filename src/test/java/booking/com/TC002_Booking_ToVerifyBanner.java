package booking.com;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import ksrtc.TestBase;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.Booking_BannerInfoPage;
import pages.Booking_Homepage;
import utils.Report;
import java.util.List;

public class TC002_Booking_ToVerifyBanner extends TestBase {
    private SoftAssertions softly = new SoftAssertions();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("To verify banner message")
    @Story("Banner Message")
    @Step("Start TC002_Booking_ToVerifyBanner")

    public void getBannerMessage() {
        EXTENT_REPORTS = Report.Instance("Booking Website");
        try {
            CreateExtentReport(this.getClass().getName(), "To verify text of Banner");
            List<String> bannerMessage = getBookingHomepage().getBannerMessage();
            System.out.println("Banner message is displayed as:\n" + bannerMessage);
            softly.assertThat(bannerMessage.contains(Booking_BannerInfoPage.Information.BANNER_MESSAGE.getLabel()));
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Banner Message: " + bannerMessage.toString(), EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
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
            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.ERROR, Error.getLocalizedMessage(), TestBase.EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(TestBase.driver)));
            PostConditionWithQuitDriver();
        }
    }
}