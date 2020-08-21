package ksrtc;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.KSRTC_HomePage;
import pages.KSRTC_LoginPage;
import utils.Report;

public class TC002_KSRTC_SignInWithoutValidCredentials extends TestBase {
    private SoftAssertions softly = new SoftAssertions();
    private static KSRTC_LoginPage ksrtc_loginPage;
    private static KSRTC_HomePage ksrtc_homePage;
    private static final Logger LOGGER = LogManager.getLogger(TC002_KSRTC_SignInWithoutValidCredentials.class);

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Login with invalid username and password")
    @Story("Valid Username and Password test")
    @Step("Start ksrtc.TC002_KSRTC_SignInWithoutValidCredentials")

    public void getLoginWithoutValidCredentials() {
        TestBase.EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            TestBase.CreateExtentReport(this.getClass().getName(), "Login to KSRTC Website");
            ksrtc_loginPage = getKSRTCLaunchPage().clickOnSignIn().enterUsername(KSRTC_LoginPage.LoginPageCredentials.INVALID_USERNAME.getLabel())
                    .enterPassword(KSRTC_LoginPage.LoginPageCredentials.INVALID_PASSWORD.getLabel()).clickOnLoginWithInvalidCredentials();
            softly.assertThat(ksrtc_loginPage.getInvalidLoginDetailsNotification().contains("Login incorrect. Please try again")).isTrue();
            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Invalid login credentials message is displayed", TestBase.EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(TestBase.driver)));
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
