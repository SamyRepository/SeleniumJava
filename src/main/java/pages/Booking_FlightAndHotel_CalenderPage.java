package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Booking_FlightAndHotel_CalenderPage extends PageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(Booking_FlightAndHotel_CalenderPage.class);

    Booking_FlightAndHotel_CalenderPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
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
        return driver.findElement(By.cssSelector(".floatingCalendar")).isDisplayed();
    }

    public Booking_FlightAndHotel_CalenderPage setDepartureDate(){
        WebElement departureDate= driver.findElement(By.xpath("(//div[@class='calendarBoxText lmn-sw-responsive-form-field lmn-sw-tooltip-responsive__text'])[1]"));
//        departureDate.sendKeys(Keys.TAB);
//        WebDriverWait wait1 = new WebDriverWait(driver, 10);
//        WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='calendarBoxText lmn-sw-responsive-form-field lmn-sw-tooltip-responsive__text'])[1]")));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        departureDate.click();
        departureDate.sendKeys(Keys.TAB);
        return this;
    }

    public Booking_FlightAndHotel_NumberOfPassengers setReturnDate(){
     WebElement returnDate=driver.findElement(By.xpath("(//div[@class='calendarBoxText lmn-sw-responsive-form-field lmn-sw-tooltip-responsive__text'])[2]"));
     returnDate.sendKeys(Keys.TAB);
     return new Booking_FlightAndHotel_NumberOfPassengers(driver,EXTENT_TEST_LOGGER,helper);
    }
}
