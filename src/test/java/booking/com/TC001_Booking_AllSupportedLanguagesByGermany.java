package booking.com;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.Booking_Homepage;
import ksrtc.TestBase;
import utils.Report;

import java.util.List;

public class TC001_Booking_AllSupportedLanguagesByGermany extends TestBase {
    private SoftAssertions softly = new SoftAssertions();
    private static Booking_Homepage booking_homepage;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("To click on language dropdown")
    @Story("Language Dropdown")
    @Step("Start TC001_Booking_AllSupportedLanguages")

    public void getAllSupportedLanguages() {
        EXTENT_REPORTS = Report.Instance("Booking Website");
        try {
            CreateExtentReport(this.getClass().getName(), "Clicking on the language button");
            booking_homepage = getBookingHomepage().clickOnLanguageSelector();
            List<String> langSupportedByGermany = booking_homepage.getLanguageSupportedInGermany();
            System.out.println("All languages supported by Germany" + langSupportedByGermany.toString());
            EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Languages available for Germany: " + langSupportedByGermany.toString(), EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));

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