package ksrtc;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.KSRTC_HomePageAfterLogin;
import pages.KSRTC_LoginPage;
import utils.Report;

import java.util.Arrays;
import java.util.List;

public class TC003_KSRTC_VerifyMenuAvailableForRegisteredUser extends TestBase {
    private SoftAssertions softly = new SoftAssertions();
    private static KSRTC_HomePageAfterLogin ksrtc_homePageAfterLogin;
    List<String> expectedOptions = Arrays.asList("Change Password", "My Account", "Accessibility Options", "Screen Reader Access", "Logout");

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Elements of Dropdown")
    @Story("Welcome message of registered user")
    @Step("Start TC003_KSRTC_MyPorsche_GetAndVerifyElementsOfDropdown")

    public void verifyMenuItemsAvailableForRegisteredUser() {

        TestBase.EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            TestBase.CreateExtentReport(this.getClass().getName(), "Verify menu items available for registered user");
            ksrtc_homePageAfterLogin = getKSRTCLaunchPage().clickOnSignIn().enterUsername(KSRTC_LoginPage.LoginPageCredentials.USERNAME.getLabel())
                    .enterPassword(KSRTC_LoginPage.LoginPageCredentials.PASSWORD.getLabel()).clickOnLoginAndGoToAfterLoginPage();
            List<String> optionsFromApp = ksrtc_homePageAfterLogin.mouseHoverOnLoggedInUSerAndGoToMenuListPage().clickOnWelcomeMessageToGetElementsOfDropdown();
            softly.assertThat(optionsFromApp.containsAll(expectedOptions)).isTrue();
            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Menu items available for registered user are: " + optionsFromApp.toString(), TestBase.EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(TestBase.driver)));

            ksrtc_homePageAfterLogin.mouseHoverOnLoggedInUSerAndGoToMenuListPage().clickOnLogoutButton();
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
