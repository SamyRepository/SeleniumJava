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

    @FindBy(xpath = "//input[@class='css-7b3jr6-SearchboxInput' and @value='1 adult']")
    private WebElement NoOfPassengersTab;

    @FindBy(xpath = "//button[@class='css-bzt01u wide']")
    private WebElement SearchButton;

    @FindBy(xpath = "(//div[@class='css-qx6f9e']/button)[2]")
    private WebElement IncreaseNumberOfAdultPassenger;

    @FindBy(xpath = "((//button[@class='css-1vzgvp0'])[2]")
    private WebElement IncreaseNumberOfChildPassenger;

    @FindBy(xpath = "//button[@class='css-bzt01u']")
    private WebElement DoneNoOfPassengers;

    @FindBy(xpath = "((//select[@class='css-pa74wc'])[2]")
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

    public Booking_NumberOfPassengers setNumberOfAdultPassenger() {
        IncreaseNumberOfAdultPassenger.click();
        return this;
    }

    public Booking_NumberOfPassengers setNumberOfChildPassengers() {
        IncreaseNumberOfChildPassenger.click();
        return this;
    }

    public Booking_NumberOfPassengers setChildAge() {
        ChildAgeTab.click();
        driver.findElement(By.xpath("//select[@class='css-pa74wc']/option[11]")).click();
        //above is dynamic
        DoneNoOfPassengers.click();
        return this;
    }

    public Booking_OneWayBookingResultPage clickOnSearchButton(){
        SearchButton.click();
        return new Booking_OneWayBookingResultPage(driver,EXTENT_TEST_LOGGER,helper);
    }
}
