package ksrtc;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.*;
import utils.Report;

import java.util.Arrays;
import java.util.List;

public class TC005_KSRTC_VerifyTableOfScreenReaderAccess extends TestBase {
    private SoftAssertions softly = new SoftAssertions();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("To click on the SignIn and Logout button on Home page")
    @Story("SignIn")
    @Step("Start TC005_VerifyTableOfScreenReaderAccess")

    public void clickOnScreenReaderOptionAfterLogin() {
        TestBase.EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            TestBase.CreateExtentReport(this.getClass().getName(), "Enter Username and Password on Login Page");
            KSRTC_HomePageAfterLogin ksrtc_homePageAfterLogin = getKSRTCLaunchPage().clickOnSignIn().enterUsername(KSRTC_LoginPage.LoginPageCredentials.USERNAME.getLabel())
                    .enterPassword(KSRTC_LoginPage.LoginPageCredentials.PASSWORD.getLabel()).clickOnLoginAndGoToAfterLoginPage();

            softly.assertThat(ksrtc_homePageAfterLogin.getTextOfWelcomeMessage().contains("Welcome SEEMA CHAURASIA")).isTrue();
            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Registered Home Page is open", TestBase.EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(TestBase.driver)));

            KSRTC_ScreenReaderAccessPage ksrtc_screenReaderAccessPage = ksrtc_homePageAfterLogin.mouseHoverOnLoggedInUSerAndGoToMenuListPage().clickOnScreenReaderButton();

            List<String> headersFromApp = ksrtc_screenReaderAccessPage.getHeaderOfTable();

            List<String> Expected = Arrays.asList(
                    "Screen Reader", "Website", "Free / Commercial");

            softly.assertThat(headersFromApp.containsAll(Expected)).isTrue();

            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Table header values are:" + headersFromApp.toString());

            List<String> RowValuesFromApp = ksrtc_screenReaderAccessPage.getRowDataOfTable(1);

            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.INFO, "All row values are:" + RowValuesFromApp.toString());

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
