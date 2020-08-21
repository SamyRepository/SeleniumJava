package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Booking_HotelModernoRebookPage extends PageBase {

    @FindBy(xpath = ".bui-price-display__value.prco-text-nowrap-helper.prco-font16-helper")
    private List<WebElement> HotelModernoNewPriceList;


    Booking_HotelModernoRebookPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver,this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        return false;
    }

}
