package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Report;

import java.util.List;

public class KSRTC_UserMenuDropdownListPage extends PageBase {
    @FindBy(css = ".dropdown-content a")
    List<WebElement> menuList;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMenuDropdownListPage.class);
    private WebElement rootElement;


    KSRTC_UserMenuDropdownListPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        rootElement = helper.waitUntilElementVisible(By.className("dropdown-content"));
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);

    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        try {
            driver.findElement(By.xpath("//div[@class='dropdown-content']"));
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "User Menu dropdown appears on registered login page", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            return true;
        } catch (Exception e) {
            EXTENT_TEST_LOGGER.log(LogStatus.FAIL, "Problem in loading User Menu dropdown list", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            return false;
        }
    }

    public KSRTC_HomePage clickOnLogoutButton() throws InterruptedException {
        WebElement logOutElement = helper.findChildElementSearchByText(menuList, "Logout");
        logOutElement.click();
        return new KSRTC_HomePage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public KSRTC_ScreenReaderAccessPage clickOnMouseReaderButton() throws InterruptedException {
        WebElement logOutElement = helper.findChildElementSearchByText(menuList, "Screen Reader Access");
        logOutElement.click();
        return new KSRTC_ScreenReaderAccessPage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public KSRTC_ScreenReaderAccessPage clickOnScreenReaderButton() throws InterruptedException {
        WebElement screenReaderElement = helper.findChildElementSearchByText(menuList, "Screen Reader Access");
        screenReaderElement.click();
        return new KSRTC_ScreenReaderAccessPage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public List<String> clickOnWelcomeMessageToGetElementsOfDropdown() {

        return helper.getElementsTextValues(menuList);
    }


}

