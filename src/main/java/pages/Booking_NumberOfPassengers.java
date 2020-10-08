package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Booking_NumberOfPassengers extends PageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(Booking_Homepage.class);

    @FindBy(xpath = "//input[@class='css-7b3jr6-SearchboxInput' and @value='1 adult']")
    private WebElement NoOfPassengersTab;

    @FindBy(xpath = "//button[@class='css-bzt01u wide']")
    private WebElement SearchButton;

    @FindBy(xpath = "(//button[@class='css-1vzgvp0'])[1]")
    private WebElement IncreaseNumberOfAdultPassenger;

    @FindBy(xpath = "(//button[@class='css-1vzgvp0'])[2]")
    private WebElement IncreaseNumberOfChildPassenger;

    @FindBy(xpath = "//button[@class='css-bzt01u']")
    private WebElement DoneNoOfPassengers;

    @FindBy(xpath = "(//select[@class='css-1k0jlfl'])[2]")
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
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Set number of passengers");
        return this;
    }

    public Booking_NumberOfPassengers setNumberOfAdultPassenger() {
        IncreaseNumberOfAdultPassenger.click();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Increase number of adult passengers");
        return this;
    }

    public Booking_NumberOfPassengers setNumberOfChildPassengers() {
        IncreaseNumberOfChildPassenger.click();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Increase number of child passengers");
        return this;
    }

    public Booking_NumberOfPassengers getAllowedAgesOfChildPassengers() {
        Select dropDownSelectAge = new Select(driver.findElement(By.xpath("(//select[@class='css-1k0jlfl'])[2]")));
        List<WebElement> dropDownActualValues = dropDownSelectAge.getOptions();
        List<Integer> wholeListOfChildAgeText=new ArrayList<>();
        for (WebElement element : dropDownActualValues) {
            if (element.getText().equalsIgnoreCase("Select age at time of flying")){
        }else{
            wholeListOfChildAgeText.add(Integer.parseInt(element.getText()));
        }
        }
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Allowed ages for child passenger:" + wholeListOfChildAgeText);
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Minimum permissible age of child: " + Collections.min(wholeListOfChildAgeText));
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Maximum permissible age of child: " + Collections.max(wholeListOfChildAgeText));
        return this;

    }

    public Booking_NumberOfPassengers setChildAge() {
        ChildAgeTab.click();
//        driver.findElement(By.xpath("//select[@class='css-1k0jlfl']/option[11]")).click();
        driver.findElement(By.xpath(SetAgeOfChildPassenger.SET_AGE_OF_CHILD_PASSENGER_0.getLabel())).click();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Set age of the child passenger 0", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        //above is dynamic
        helper.waitUntilElementToBeClickable(DoneNoOfPassengers);
        DoneNoOfPassengers.click();
        return this;
    }

    public Booking_OneWayBookingResultPage clickOnSearchButton() {
        SearchButton.click();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Search button is clicked");
        return new Booking_OneWayBookingResultPage(driver,EXTENT_TEST_LOGGER,helper);
    }
    public enum SetAgeOfChildPassenger {
        SET_AGE_OF_CHILD_PASSENGER_0("(//select[@class='css-1k0jlfl'])[2]/option[2]"),
        SET_AGE_OF_CHILD_PASSENGER_1("(//select[@class='css-1k0jlfl'])[2]/option[3]"),
        SET_AGE_OF_CHILD_PASSENGER_2("(//select[@class='css-1k0jlfl'])[2]/option[4]"),
        SET_AGE_OF_CHILD_PASSENGER_3("(//select[@class='css-1k0jlfl'])[2]/option[5]");

        private final String label;
        SetAgeOfChildPassenger(String label) {
            this.label = label;
        }
        public String getLabel() {
            return label;
        }
    }
}
