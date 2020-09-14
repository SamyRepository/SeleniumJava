package booking.com;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.Booking_BannerInfoPage;
import pages.Booking_Homepage;
import ksrtc.TestBase;
import utils.Report;
import java.util.List;

public class TC001_Booking_AllSupportedLanguagesByGermany extends TestBase {
    private SoftAssertions softly = new SoftAssertions();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("To click on language dropdown to get all supported languages in Germany")
    @Story("Language Dropdown")
    @Step("Start TC001_Booking_AllSupportedLanguages")

    public void getAllSupportedLanguages() {
        EXTENT_REPORTS = Report.Instance("Booking Website");
        try {
            CreateExtentReport(this.getClass().getName(), "To get all the languages supported by Germany");
            Booking_Homepage booking_homepage = getBookingHomepage().clickOnLanguageSelector();
            List<String> langSupportedByGermany = booking_homepage.getLanguageSupportedInGermany();
            softly.assertThat(langSupportedByGermany.contains(Booking_BannerInfoPage.Information.SUPPORTED_LANGUAGES_IN_GERMANY.getLabel()));
            System.out.println("All languages supported by Germany" + langSupportedByGermany.toString());
            EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Languages supported by Germany: " + langSupportedByGermany.toString(), EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
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