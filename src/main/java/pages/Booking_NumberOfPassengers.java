package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Booking_NumberOfPassengers extends PageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(Booking_Homepage.class);

    @FindBy(xpath = "//input[@class='css-mbm5ok-SearchboxInput' and @value='1 adult']")
    private WebElement NoOfPassengersTab;
    @FindBy(xpath = "//button[@class='css-bzt01u wide']")
    private WebElement SearchButton;

    @FindBy(xpath = "//div[@class='css-qx6f9e']/button[2]")
    private WebElement AdultPassenger;

    @FindBy(xpath = "(//button[@class='css-1vzgvp0'])[2]")
    private WebElement ChildPassenger;

    @FindBy(xpath = "//button[@class='css-bzt01u']")
    private WebElement DoneNoOfPassengers;

    @FindBy(css = ".css-glra3l")
    private WebElement ChildAgeTab;


    Booking_NumberOfPassengers(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
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
        return DoneNoOfPassengers.isDisplayed();
    }

    public Booking_NumberOfPassengers selectNoOfPassengers() {
        NoOfPassengersTab.click();
        return this;
    }

    public Booking_NumberOfPassengers setAdultPassenger() {
        AdultPassenger.click();
        return this;
    }

    public Booking_NumberOfPassengers setChildPassengers() {
        ChildPassenger.click();
        return this;
    }

    public Booking_FlightBookingPage setChildAge() {
        ChildAgeTab.click();
        driver.findElement(By.xpath("//select[@class='css-pa74wc']/option[11]")).click();
        //above is dynamic
        DoneNoOfPassengers.click();
        return new Booking_FlightBookingPage(driver,EXTENT_TEST_LOGGER,helper);
    }

    public enum NumberOfPassengers {

        BOOKING_ADULT("2"),
        BOOKING_CHILD("1"),
        BOOKING_CHILD_AGE("5");

        private final String label;

        NumberOfPassengers(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

}
