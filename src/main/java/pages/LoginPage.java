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

public class LoginPage extends PageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(xpath = "//*[contains(text(),'Login & registration')]/ancestor-or-self::div[contains(@class, 'navigation-topbar-desktop__right')]")
    private WebElement LoginAndRegisterButton;

    @FindBy(xpath = "//div[@class='page-header']")
    private WebElement WelcomeMessage;

    @FindBy(xpath = "//div[@class='header__logo']")
    private WebElement Logo;

    @FindBy(xpath = "//*[@class='loader__image']")
    private WebElement LogerImage;

    @FindBy(css = "div .pidx-tooltip-content__text")
    private WebElement LoginWithOutPasswordApplicationNotification;

    @FindBy(css = "span .icon__tooltip")
    private WebElement InfoTooltip;

    @FindBy(css = "span.tabs-access__button")
    private WebElement LoginTab;

    @FindBy(xpath = "//input[@placeholder='Porsche ID (email address)']")
    private WebElement PorscheID_Input;

    @FindBy(className = "//span[contains(text(),'Password')]")
    private WebElement PasswordInput;

    @FindBy(xpath = "//span[@class='button-primary__label']")
    private WebElement LoginButton;

    @FindBy(id = "eye-icon")
    private WebElement EyeIcon;

    @FindBy(className = "form__messages")
    private WebElement InvalidLoginDetailsNotification;


    public LoginPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded() {
        try {
            waitUntilLoadersNotVisible();
            driver.findElement(By.xpath("//*[contains(text(),'Login & registration')]/ancestor-or-self::div[contains(@class, 'navigation-topbar-desktop__right')]")).isDisplayed();
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Login Page is loaded", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            return true;
        } catch (Exception e) {
            EXTENT_TEST_LOGGER.log(LogStatus.FAIL, "Problem in login page loading", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            return false;
        }
    }

    public LoginPage setPorscheId(final String loginCredentials) {
        PorscheID_Input.clear();
        PorscheID_Input.sendKeys(loginCredentials);
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "PorscheId is set: ", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        return this;
    }

    public LoginPage setPassword(final String password) {
        EyeIcon.click();
        helper.sendKeysUsingAction(password);
        EyeIcon.click();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "PorscheId and password is set: ", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        return this;
    }

    public MessagingPage clickLoginButtonAndGoToMessagingPage() throws Exception {
        LoginButton.click();
        return new MessagingPage(driver, EXTENT_TEST_LOGGER, helper);
    }


    public LoginPage clickLoginButton() {
        LoginButton.click();
        return this;
    }

    public LoginPage waitUntilLoadersNotVisible() {
        helper.waitUntilElementNotVisible(By.className("loader__image"));
        return this;
    }

    public boolean verifyLoginAndRegisterButton() {
        return helper.isDisplayed(LoginAndRegisterButton);
    }

    public String getInvalidLoginDetailsNotification() {
        String invalidLoginDetailsNotification = helper.ElementGetText(InvalidLoginDetailsNotification);
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Invalid credentials login message is displayed as: " + invalidLoginDetailsNotification);
        return invalidLoginDetailsNotification;
    }

    public String getWelcomeMessage() {
        String welcomeMsg = helper.ElementGetText(WelcomeMessage);
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Welcome message displayed: " + welcomeMsg);
        return welcomeMsg;
    }

    public String getLoginWithOutPasswordApplicationNotification() throws InterruptedException {
        final String loginWithOutPasswordApplicationNotification;
        helper.scrollElementToVerticalCenter(InfoTooltip);
        loginWithOutPasswordApplicationNotification = helper.hoverOnElementAndGetText(InfoTooltip, LoginWithOutPasswordApplicationNotification);
        return loginWithOutPasswordApplicationNotification;
    }



}
