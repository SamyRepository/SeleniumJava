package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Report;

public class KSRTC_HomePageAfterLogin extends PageBase {

    @FindBy(xpath = "//div[@class='dropdown' and contains(text(),'Welcome')]")
    private WebElement WelcomeMessage;



    KSRTC_HomePageAfterLogin(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        try {
            waitUntilPageLoaded();
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Home Page after Login is displayed", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            return true;
        } catch (Exception e) {
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Problem in loading Home page after login", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            return false;
        }
    }

    private boolean waitUntilPageLoaded() {
        return driver.findElement(By.xpath("//div[@class='dropdown' and contains(text(),'Welcome')]")).isDisplayed();
    }

    public String getTextOfWelcomeMessage() {
        return helper.ElementGetText(WelcomeMessage);
    }

    public KSRTC_UserMenuDropdownListPage mouseHoverOnLoggedInUSerAndGoToMenuListPage() {
        helper.moveToElement(WelcomeMessage);
        return new KSRTC_UserMenuDropdownListPage(driver, EXTENT_TEST_LOGGER, helper);
    }


}
