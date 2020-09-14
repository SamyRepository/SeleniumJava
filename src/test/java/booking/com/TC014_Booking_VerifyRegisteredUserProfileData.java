package booking.com;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import ksrtc.TestBase;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.Booking_SettingsPage;
import utils.Report;

public class TC014_Booking_VerifyRegisteredUserProfileData extends TestBase {
    SoftAssertions softly = new SoftAssertions();
    private static Booking_SettingsPage booking_settingsPage;

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("My Profile")
    @Description("To verify the the registered user personal data")
    @Step("To run TC014_Booking_VerifyRegisteredUserProfileData")

    public void verifyRegisteredUserPersonalData() {

        TestBase.EXTENT_REPORTS = Report.Instance("Booking website");
        try {
            TestBase.CreateExtentReport(this.getClass().getName(), "Verify details of registered user profile");
            booking_settingsPage = getBookingHomepage().clickOnSignIn().setUsername().setSignInPassword().selectUserIcon()
                                  .clickOnDashBoardLinkToGoToDashboardPage().clickEditYourProfileToGoToSettingsPage();

            String RegisteredUserTitle = booking_settingsPage.getRegisteredUserTitle();
            System.out.println(RegisteredUserTitle);
            softly.assertThat(RegisteredUserTitle.equals(Booking_SettingsPage.RegisteredUserProfileDetails.TITLE.getLabel()));
            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Title of the registered user: " + RegisteredUserTitle);

            String RegisteredUserFirstName = booking_settingsPage.getRegisteredUserFirstName();
            System.out.println(RegisteredUserFirstName);
            softly.assertThat(RegisteredUserFirstName.equals(Booking_SettingsPage.RegisteredUserProfileDetails.FIRSTNAME.getLabel()));
            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.PASS, "First name of the registered user: " + RegisteredUserFirstName);

            String RegisteredUserLastName = booking_settingsPage.getRegisteredUserLastName();
            System.out.println(RegisteredUserLastName);
            softly.assertThat(RegisteredUserLastName.equals(Booking_SettingsPage.RegisteredUserProfileDetails.LASTNAME.getLabel()));
            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Last name of the registered user: " + RegisteredUserLastName);

            String RegisteredUserEmail = booking_settingsPage.getRegisteredUserEmail();
            System.out.println(RegisteredUserEmail);
            softly.assertThat(RegisteredUserEmail.equals(Booking_SettingsPage.RegisteredUserProfileDetails.EMAIL.getLabel()));
            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.PASS, "E-mail of the registered user: " + RegisteredUserEmail);

            String RegisteredUserPhone = booking_settingsPage.getRegisteredUserPhone();
            System.out.println(RegisteredUserPhone);
            softly.assertThat(RegisteredUserPhone.equals(Booking_SettingsPage.RegisteredUserProfileDetails.PHONE.getLabel()));
            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Phone of the registered user: " + RegisteredUserPhone);

        } catch (Exception exc) {
            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.ERROR, exc.getStackTrace().toString(), TestBase.EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(TestBase.driver)));
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



