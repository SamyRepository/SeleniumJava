package booking.com;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import utils.Report;

import java.util.Arrays;
import java.util.List;

public class TC009_Booking_VerifyFooterTopMenu extends TestBase {
    SoftAssertions softly = new SoftAssertions();
    private static Logger LOGGER = LogManager.getLogger(TC009_Booking_VerifyFooterTopMenu.class);

    @Test
    @Description("To get all links present at the Footer")
    @Story("Booking Homepage")
    @Severity(SeverityLevel.BLOCKER)
    @Step("To run TC009_Booking_VerifyFooterTopMenu.java")

    public void toGetFooterTopMenu() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            CreateExtentReport(this.getClass().getName(), "Verify footer top menu items of Booking homepage");
            SoftAssertions softly = new SoftAssertions();
            List<String> footerTopMenu = getBookingHomepage().getFooterTopMenu();
            String footerTopMenuArray[] = footerTopMenu.get(0).split("\\n?\\r");
            System.out.println(Arrays.asList(footerTopMenuArray));
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Footer top menu items are: " +footerTopMenu.toString());
            List<String> expectedFooterNavigationLinks = Arrays.asList("Mobile version", "Your account", "Make changes to your booking online",
                    "Customer Service help", "Become an affiliate", "Booking.com for Business");
            softly.assertThat(expectedFooterNavigationLinks.containsAll(Arrays.asList(footerTopMenuArray)));
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
