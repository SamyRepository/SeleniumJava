package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Report;

public class KSRTC_LoginPage extends PageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(KSRTC_HomePage.class);

    @FindBy(xpath = "//label[contains(text(),'User Name')]")
    private WebElement UserName;

    @FindBy(xpath = "//label[contains(text(),'Password')]")
    private WebElement Password;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement Login;

    @FindBy(id = "errorMsg")
    private WebElement InvalidCredentialsMessage;

    @FindBy(xpath="//button[@class='close']")
    private WebElement closeAlert;

    public KSRTC_LoginPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        try {

            //change here
            return waitUntilPageLoaded();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean waitUntilPageLoaded() {
        return driver.findElement(By.xpath("//*[contains(text(),'Login Here')]")).isDisplayed();
    }

    public KSRTC_LoginPage enterUsername(final String username) {
        UserName.click();
        helper.sendKeysUsingAction(username);
        EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Username is entered: " +username , EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        return this;
    }

    public KSRTC_LoginPage enterPassword(final String password) {
        Password.click();
        helper.sendKeysUsingAction(password);
        EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Password is entered", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        return this;
    }

    public KSRTC_LoginPage clickOnLogin() {
        Login.click();
        EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Login button is clicked", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        return this;
    }
    public KSRTC_HomePageAfterLogin clickOnLoginAndGoToAfterLoginPage() {
        Login.click();
        EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Login button is clicked", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        if (helper.isDisplayed(closeAlert)){
            closeAlert.click();
        }
        return new KSRTC_HomePageAfterLogin(driver,EXTENT_TEST_LOGGER,helper);
    }

    public KSRTC_LoginPage clickOnLoginWithInvalidCredentials(){
        Login.click();
        EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Login button is clicked with Invalid credentials", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        return new KSRTC_LoginPage(driver,EXTENT_TEST_LOGGER,helper);
    }

    public String getInvalidLoginDetailsNotification() {
        String invalidLoginDetailsNotification = helper.ElementGetText(InvalidCredentialsMessage);
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Invalid credentials login message is displayed as: " + invalidLoginDetailsNotification);
        return invalidLoginDetailsNotification;
    }


    public enum LoginPageCredentials {
        USERNAME("anand.sogetitest@gmail.com"),
        INVALID_USERNAME("SHARANYA"),
        PASSWORD("Sogeti@323"),
        INVALID_PASSWORD("123456");

        private final String label;

        LoginPageCredentials(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

}
