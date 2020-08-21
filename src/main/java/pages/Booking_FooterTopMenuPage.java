package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Booking_FooterTopMenuPage extends PageBase {

    @FindBy(xpath = "//*[contains(text(), 'Mobile version')]")
    private WebElement MobileVersionLink;

    @FindBy(xpath = "//*[contains(text(), 'Your account')]")
    private WebElement YourAccountLink;

    @FindBy(xpath = "//*[contains(text(), 'Make changes to your booking online')]")
    private WebElement ChangeBookingOnlineLink;

    @FindBy(xpath = "//*[contains(text(), 'Customer Service help')]")
    private WebElement CusterServiceHelpLink;

    @FindBy(xpath = "//*[contains(text(), 'Become an affiliate')]")
    private WebElement BecomeAffiliateHelpLink;

    @FindBy(xpath = "//*[contains(text(), 'Booking.com for Business')]")
    private WebElement BookingForBusinessLink;

    Booking_FooterTopMenuPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver,this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        return false;
    }
    
}
