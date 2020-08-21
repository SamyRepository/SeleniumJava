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

public class Booking_FlightBookingPage extends PageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(Booking_Homepage.class);

    @FindBy(xpath = "//div[@class='css-1kmv57l-radio-input']")
    private WebElement OneWayJourneyLink;

    @FindBy(xpath = "//div[@class='bui-f-font-body css-lilgi0']")
    private WebElement SourceTab;

    @FindBy(xpath = "//input[@class='css-5ttzzv']")
    private WebElement SourceInput;

    @FindBy(xpath = "//input[@class='css-5ttzzv']")
    private WebElement Destination;

    @FindBy(xpath = "//button[@class='css-bzt01u wide']")
    private WebElement SearchButton;


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
        return this;
    }

    public Booking_FlightBookingPage enterSourceOfJourney() {
        SourceTab.click();
        String source = "MAD";
        SourceInput.sendKeys(source);
        //Dynamic Xpath for source and destination using concatenation of strings
        driver.findElement(By.xpath("//*[contains(text(),'" + source + "')]")).click();
        SourceInput.sendKeys(Keys.TAB);
        return this;
    }

    public Booking_CalenderPage enterDestinationOfJourney() {
        Destination.click();
        String destination = "BOM";
        Destination.sendKeys(destination);
        driver.findElement(By.xpath("//*[contains(text(),'" + destination + "')]")).click();
        return new Booking_CalenderPage(driver,EXTENT_TEST_LOGGER,helper);
    }

    public Booking_FlightBookingPage clickOnSearchButton() {
        SearchButton.click();
        return new Booking_FlightBookingPage(driver,EXTENT_TEST_LOGGER,helper);
    }
}
