package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Booking_RegisteredUserDashBoardPage extends PageBase {

    @FindBy(xpath = "//*[contains(text(), 'Edit your profile')]")
            private WebElement EditYourProfile;

    Booking_RegisteredUserDashBoardPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver,this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        return false;
    }

    public Booking_SettingsPage clickEditYourProfileToGoToSettingsPage(){
        EditYourProfile.click();
        return new Booking_SettingsPage(driver,EXTENT_TEST_LOGGER,helper);
    }
}
