package ksrtc;

import com.relevantcodes.extentreports.LogStatus;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.KSRTC_HelpLinePortalPage;
import pages.KSRTC_HomePage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import pages.KSRTC_OperatorSignInPage;
import pages.KSRTC_ScreenReaderAccessPage;
import utils.Report;

public class TC004_KSRTC_VerifyAllConnectedApplications extends TestBase {
    private SoftAssertions softly = new SoftAssertions();
    private static KSRTC_HomePage ksrtc_homePage;
    private static KSRTC_OperatorSignInPage ksrtc_operatorSignInPage;
    private static KSRTC_HelpLinePortalPage ksrtc_helpLinePortalPage;
    private static KSRTC_ScreenReaderAccessPage ksrtc_screenReaderAccessPage;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("To verify whether Operator sign In page is loaded")
    @Story("OperatorSignIn")
    @Step("Start TCOO4_KSRTC_VerifyOperatorSignInPageLoaded")

    public void verifyOperatorSignInPageIsLoaded() {
        TestBase.EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            TestBase.CreateExtentReport(this.getClass().getName(), "Verify whether Operator SignIn page is loaded after clicking on Operator link");
            String headerFromApp = getKSRTCLaunchPage().clickOnOperatorLink().verifyTextOfFormHeader();
            softly.assertThat(headerFromApp.contains("Sign in to FRANCHISEE/OPR/ADMIN")).isTrue();
            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Operator SignIn page is successfully loaded", TestBase.EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(TestBase.driver)));

            String headerOfHelpLinePortalFromApp= switchToHomePageAfterClosingCurrentWindow().clickOnHelpLinePortalOnHomePage().verifyTextOfHelpLinePortal();
            softly.assertThat(headerOfHelpLinePortalFromApp.contains("Welcome to the KSRTC Helpline Portal")).isTrue();
            TestBase.EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Helpline Portal Page is successfully loaded", TestBase.EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(TestBase.driver)));


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
