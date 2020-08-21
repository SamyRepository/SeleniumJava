package ksrtc;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.KSRTC_HomePageAfterLogin;
import utils.Report;
import pages.KSRTC_LoginPage.LoginPageCredentials;

import java.util.List;

public class TC001_KSRTC_SignIn extends TestBase {
    private SoftAssertions softly = new SoftAssertions();
    private static KSRTC_HomePageAfterLogin ksrtc_homePageAfterLogin;


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("To click on the SignIn and Logout button on Home page")
    @Story("SignIn")
    @Step("Start ksrtc.TC001_KSRTC_SignIn")

    public void clickOnSignUp() {
        TestBase.EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            TestBase.CreateExtentReport(this.getClass().getName(), "Enter Username and Password on Login Page");
            ksrtc_homePageAfterLogin = getKSRTCLaunchPage().clickOnSignIn().enterUsername(LoginPageCredentials.USERNAME.getLabel())
                    .enterPassword(LoginPageCredentials.PASSWORD.getLabel()).clickOnLoginAndGoToAfterLoginPage();

            softly.assertThat(ksrtc_homePageAfterLogin.getTextOfWelcomeMessage().contains("Welcome SEEMA CHAURASIA")).isTrue();
            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Registered Home Page is open", TestBase.EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(TestBase.driver)));
            ksrtc_homePageAfterLogin.mouseHoverOnLoggedInUSerAndGoToMenuListPage().clickOnLogoutButton();
            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Logout button is clicked", TestBase.EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(TestBase.driver)));

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

    public static class TC006_KSRTC_VerifyFooterSection extends TestBase {
        private SoftAssertions softly = new SoftAssertions();

        @Test
        @Severity(SeverityLevel.MINOR)
        @Description("To display related links options on footer")
        @Story("SignIn")
        @Step("Start TC006_VerifyFooterSection")

        public void getElementsOfFooterSection() {
            TestBase.EXTENT_REPORTS = Report.Instance(this.getClass().getName());
            try {
                TestBase.CreateExtentReport(this.getClass().getName(), "To verify footer section of homepage");
                List<String> relatedLinksOptionsFromApp = getKSRTCLaunchPage().getRelatedLinksOptions();
                TestBase.EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Home Page is successfully loaded", TestBase.EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(TestBase.driver)));
                System.out.println(relatedLinksOptionsFromApp.toString());
                TestBase.EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Related links Values are:" + relatedLinksOptionsFromApp.toString());
            } catch (Exception exp) {
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
}
