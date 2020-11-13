package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Booking_OneWayBookingResultPage extends PageBase {

    @FindBy(xpath = "//div[@class='css-1icxbv3-tab']/div")
    private List<WebElement> ArrangeSearchResult;

    @FindBy(xpath = "(//div[@class='bui-text bui-text--variant-headline_3'])[1]")
    private WebElement FirstResultOfCheapest;

    @FindBy(xpath = "//span[@class='bui-checkbox__label']")
    private WebElement DirectFlightCheckbox;

    @FindBy(xpath = "(//button[@class='css-g7fgyv'])[1]")
    private WebElement FirstFlightOfSearchResult;


    Booking_OneWayBookingResultPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        return false;
    }

    public Booking_OneWayBookingResultPage selectCheapestFlights() {

        for (int i = 0; i < ArrangeSearchResult.size(); i++) {
            String textValue = ArrangeSearchResult.get(i).getText();
            if (textValue.equalsIgnoreCase("Cheapest")) {
                ArrangeSearchResult.get(i).click();
                int whileCntr = 0;
                while (ArrangeSearchResult.get(i).getAttribute("data-selected").equals("False") || whileCntr > 3) {
                    ArrangeSearchResult.get(i).click();
                    whileCntr++;
                }
            }
        }
        return this;
    }

    public Double selectCostOfFirstFlightOfCheapest() {
        String flightCost = FirstResultOfCheapest.getText();
        if (flightCost.contains(" ")) {
            flightCost = flightCost.substring(0, flightCost.indexOf(" "));
        }
        Double flightPrice = Double.valueOf(flightCost.replace(".", "").replace(",", "."));
        System.out.println("Price of cheapest flight=" + flightPrice);
        return flightPrice;
    }

    public Booking_OneWayBookingResultPage clickOnParticularPage(GetPageOfSearchResult page2) {
        List<WebElement> resultPageOptions = driver.findElements(By.xpath("//div[@class='css-1ctylyz']/div"));
        helper.selectChildElementSearchByText(resultPageOptions, "2");
        return this;
    }

    public Booking_OneWayBookingResultPage selectDirectFlights() {
        DirectFlightCheckbox.click();
        return this;
    }
    public Booking_FlightDetailPage selectFirstFlightOfResultPage(){
        helper.waitUntilElementToBeClickable(FirstFlightOfSearchResult);
        FirstFlightOfSearchResult.click();
        return new Booking_FlightDetailPage(driver,EXTENT_TEST_LOGGER,helper);
    }

    public enum GetPageOfSearchResult {
        PAGE1("1"),
        PAGE2("2"),
        PAGE3("3"),
        PAGE4("4");

        private final String label;

        GetPageOfSearchResult(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}
