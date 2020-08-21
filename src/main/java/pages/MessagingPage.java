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

public class MessagingPage extends PageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessagingPage.class);

    @FindBy(className = "navigation-topbar-desktop__link navigation-topbar-desktop__link--user-name")
    private WebElement DisplayedLoggedInUser;

    @FindBy(css = "div.menu-item.new-message-button-wrap")
    private WebElement NewMessage;

    @FindBy(xpath = "//span[@class='navigation-topbar-desktop__link-icon icon icon--contact']")
    private WebElement UserLoggedInIcon;

    @FindBy(xpath = "//a[contains(text(),' Messages')]")
    private WebElement MessagingLink;


    MessagingPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) throws Exception {
        super(driver,EXTENT_TEST_LOGGER,helper);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        try {
            waitUntilLoadersNotVisible();
            driver.findElement(By.xpath("//a[contains(text(),' Messages')]")).isDisplayed();
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Messaging page is loaded", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            return true;
        } catch (Exception e) {
            EXTENT_TEST_LOGGER.log(LogStatus.FAIL, "Problem in Messaging Page loading", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            return false;
        }
    }

    public MessagingPage waitUntilLoadersNotVisible() {
        helper.waitUntilElementNotVisible(By.className("loader__image"));
        return this;
    }

    public NewMessagePage clickOnNewMessage() {
        NewMessage.click();
        return new NewMessagePage(driver, EXTENT_TEST_LOGGER, helper);

    }

    public UserMenuDropdownListPage clickOnUserLoggedInIcon(){
        UserLoggedInIcon.click();
        EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Logged in Icon is clicked", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        return new UserMenuDropdownListPage(driver,EXTENT_TEST_LOGGER,helper);
    }

    public MessagingPage clickMessagingLink() throws Exception {
        MessagingLink.click();
        return this;
    }


}
