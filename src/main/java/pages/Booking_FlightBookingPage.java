package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Report;

import java.util.List;

public class Booking_FlightBookingPage extends PageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(Booking_Homepage.class);

    @FindBy(xpath = "//div[@class='css-mxqkpy-radio-input']")
    private WebElement OneWayJourneyLink;

    /* @FindBy(xpath = "//div[@class='bui-f-font-body css-lilgi0']")
     private WebElement SourceTab;
 */
    @FindBy(css = ".css-1wkb7ih-inputContainer .css-1wz0cgy")
    private WebElement SourceInputContainer;

    @FindBy(xpath = "//input[@class='css-5ttzzv' and @placeholder='Where else?']")
    private WebElement SourceInputWithDefaultValue;

    @FindBy(xpath = "//input[@class='css-5ttzzv' and @placeholder='Where from?']")
    private WebElement SourceInput;

    @FindBy(xpath = "//input[@class='css-5ttzzv' and @placeholder='Where to?']")
    private WebElement Destination;

    @FindBy(xpath = "//button[@class='css-bzt01u wide']")
    private WebElement SearchButton;

    @FindBy(xpath = "//select[@class='css-1k0jlfl']")
    private List<WebElement> FlightClassSelectContainer;

    public Booking_FlightBookingPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
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
        return driver.findElement(By.cssSelector("div.css-1g3l4nl")).isDisplayed();
    }

    public Booking_FlightBookingPage selectOneWayJourney() {
        OneWayJourneyLink.click();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO,"One-way journey option is selected");
        return this;
    }

    public Booking_FlightBookingPage enterSourceOfJourney() {
        String source = "MAD";
        helper.click(SourceInputContainer);
        helper.clearAutoFillInputContainer(SourceInputWithDefaultValue);
        SourceInput.sendKeys(source);
        //Dynamic Xpath for source and destination using concatenation of strings
        driver.findElement(By.xpath("//*[contains(text(),'" + source + "')]")).click();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO,"Source of Journey is selected as" +source);
        //SourceInput.sendKeys(Keys.TAB);
        return this;
    }

    public Booking_CalenderPage enterDestinationOfJourney() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        helper.waitUntilElementToBeClickable(Destination);
        //Destination.click();
        String destination = "CDG";
        Destination.sendKeys(destination);
        driver.findElement(By.xpath("//*[contains(text(),'" + destination + "')]")).click();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO,"Destination of Journey is selected as" +destination);
        return new Booking_CalenderPage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public List<String> getClassesOfFlightTickets() {
        driver.findElement(By.xpath("//select[@class='css-1k0jlfl']")).click();// use as web element
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Clicked on Economy(By default) to display all classes of flight tickets ", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        return helper.getElementsTextValues(FlightClassSelectContainer);
    }
}
