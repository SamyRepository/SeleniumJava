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

public class TC007_Booking_Verify_RegisteredUserIcon extends TestBase {
    SoftAssertions softly = new SoftAssertions();
    private static Logger LOGGER = LogManager.getLogger(TC007_Booking_Verify_RegisteredUserIcon.class);
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verifying items of Register button")
    @Story("Sign in and User icon")
    @Step("Start TC007_Booking_Verify_UserIcon")

    public void verifyRegisteredUserIconOnHomepage() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            CreateExtentReport(this.getClass().getName(), "Verify registered user icon items");
            List<String> userIconItems = getBookingHomepage().clickOnSignIn().setUsername().setSignInPassword().selectUserIcon().getRegisteredUserProfileMenuList();
            System.out.println("Registered User Icon items are:" + userIconItems.toString());
            String userIconItemsArray[] = userIconItems.get(0).split("\\r?\\n");
            System.out.println(Arrays.asList(userIconItemsArray));
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Registered user items are" + userIconItems.toString(), EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
           /* List<String> expectedRegisteredUserIconItems = Arrays.asList("Your account menu", "My dashboard", "Bookings", "Genius Loyalty Programme",
                    "My reviews", "My wish lists", "Get the app", "Customer Service help", "Settings", "Travel Communities", "Exit menu");*/
           List<String> expectedRegisteredUserIconItems = Arrays.asList("My dashboard", "Bookings", "Genius Loyalty Programme",
                    "My reviews", "Saved Properties", "Get the app", "Contact Customer Service", "Settings", "Travel Communities", "Sign out");
            /*List<String> expectedRegisteredUserIconItems = Arrays.asList("Manage account", "Trips", "Genius", "Reviews","Wish lists", "Sign out");
            softly.assertThat(expectedRegisteredUserIconItems.containsAll(Arrays.asList(userIconItemsArray)));*/

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
