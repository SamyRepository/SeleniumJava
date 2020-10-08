package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Booking_CalenderPage extends PageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(Booking_BannerInfoPage.class);

    @FindBy(xpath = "//input[@class='css-mbm5ok-SearchboxInput' and @placeholder='When?']")
    private WebElement DateOfFlight;

    @FindBy(css = ".DayPicker-Day--today")
    private WebElement CurrentDate;

    @FindBy(xpath = "//div[@class='css-1ws0i0d']")
    private WebElement DayHeader;

    Booking_CalenderPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        WebElement pageHeader = (new WebDriverWait(driver, 5000))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("DayPicker-wrapper")));
        if (pageHeader.isDisplayed()) {
            System.out.println("Page is loaded");
        } else {
            System.out.println("Page is not loaded");
        }
        PageFactory.initElements(driver, this);
    }
    public Booking_NumberOfPassengers selectCurrentDate() throws InterruptedException {
        Thread.sleep(3000);
        CurrentDate.click();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO,"Current date is selected as travel date" );
        return new Booking_NumberOfPassengers(driver, EXTENT_TEST_LOGGER, helper);
    }
    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        try {
            return waitUntilPageLoaded();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean waitUntilPageLoaded() {
        return DayHeader.isDisplayed();
    }
}
