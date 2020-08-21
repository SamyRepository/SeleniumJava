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

public class BookMyShow_HomePage extends PageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(KSRTC_HomePage.class);

    @FindBy(xpath="//div[@class='lang-select']")
   private WebElement LanguageDropdown;


    public BookMyShow_HomePage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
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
        return driver.findElement(By.xpath("//a[@class='logo']")).isDisplayed();

    }
    public BookMyShow_HomePage clickOnLanguageDropdown(){
        dismissAlert();
        LanguageDropdown.click();
        EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Language dropdown is clicked", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        return this;

    }
    public BookMyShow_HomePage dismissAlert(){
        driver.switchTo().alert().dismiss();
        return this;
    }
}
