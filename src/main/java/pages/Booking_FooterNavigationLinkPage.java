package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class Booking_FooterNavigationLinkPage extends PageBase {



    Booking_FooterNavigationLinkPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver,this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        return false;
    }
}
