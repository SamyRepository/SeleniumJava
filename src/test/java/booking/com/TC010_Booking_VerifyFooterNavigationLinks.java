package booking.com;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ksrtc.TestBase;
import utils.Report;

import java.util.List;

public class TC010_Booking_VerifyFooterNavigationLinks extends TestBase {
    SoftAssertions softly = new SoftAssertions();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Booking Homepage Footer navigation links")
    @Description("To get all the footer navigation links")
    @Step("To run TC010_Booking_VerifyFooterNavigationLinks")

    public void getFooterNavigationLinks() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            CreateExtentReport(this.getClass().getName(), "Verify footer top menu items of Booking homepage");
            List<String> FooterNavigationLinks = getBookingHomepage().getFooterNavigationLinks();
            System.out.println(FooterNavigationLinks);
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Footer navigation links are: " + FooterNavigationLinks.toString());
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
