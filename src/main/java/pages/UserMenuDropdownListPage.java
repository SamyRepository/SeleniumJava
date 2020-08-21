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

public class UserMenuDropdownListPage extends PageBase {
    private SeleniumHelper helper;
    private RemoteWebDriver driver;
    private ExtentTest EXTENT_TEST_LOGGER;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserMenuDropdownListPage.class);

    @FindBy(className = "page-header__title")
    private WebElement LogOutPageHeaderTitle;

    @FindBy(xpath = "//a[contains(text(),'Log in again')]")
    private WebElement LoginLinkElement;


    UserMenuDropdownListPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver,EXTENT_TEST_LOGGER,helper);
        this.helper = helper;
        this.driver = driver;
        this.EXTENT_TEST_LOGGER = EXTENT_TEST_LOGGER;
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        try {
            waitUntilLoadersNotVisible();
            driver.findElement(By.className("page-header__title"));
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "User Menu dropdown list Page is loaded", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            return true;
        } catch (Exception e) {
            EXTENT_TEST_LOGGER.log(LogStatus.FAIL, "Problem in User Menu dropdown list page loading", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            return false;
        }
    }

    public void clickOnLogOutButton() throws InterruptedException {
        final String userMenuItems = "//*[@class='navigation-topbar-desktop__flyout-menu']//li";
        WebElement logOutElement = helper.findChildElementSearchByText(userMenuItems, "Logout");
        logOutElement.click();
        waitUntilLoadersNotVisible();
        LoginLinkElement.click();

    }

    public UserMenuDropdownListPage waitUntilLoadersNotVisible() {
        helper.waitUntilElementNotVisible(By.className("loader__image"));
        return this;
    }
}

