package booking.com;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ksrtc.TestBase;
import utils.Report;

public class TC013_Booking_ChangeProfilePictureOfRegisteredUser extends TestBase {
    SoftAssertions softly = new SoftAssertions();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("My Profile")
    @Description("To change the profile picture of registered user")
    @Step("To run TC013_Booking_ChangeProfilePictureOfRegisteredUser")

    public void changeUserProfilePic() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            CreateExtentReport(this.getClass().getName(), "Change profile picture of the registered user");
            getBookingHomepage().clickOnSignIn().setUsername().setSignInPassword().selectUserIcon()
                    .clickOnDashBoardLinkToGoToDashboardPage().clickEditYourProfileToGoToSettingsPage()
                    .changeProfilePicture().uploadNewProfilePic().saveProfilePic();
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Profile picture of the registered user is changed", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));

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
