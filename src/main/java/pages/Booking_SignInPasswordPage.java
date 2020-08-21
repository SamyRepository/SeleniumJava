package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Booking_SignInPasswordPage extends PageBase {


    @FindBy(xpath = "//span[@class='bui-button__text']")
    private WebElement SubmitSignInButton;

    @FindBy(css = "#password")
    private WebElement BookingSignInPassword;

    Booking_SignInPasswordPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        return false;
    }

    public Booking_Homepage setSignInPassword1() {
        BookingSignInPassword.click();
        BookingSignInPassword.clear();
        BookingSignInPassword.sendKeys(SignInPasswordLabels.PASSWORD1.getLabel());
        SubmitSignInButton.click();
        return new Booking_Homepage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public Booking_Homepage setSignInPassword2() {
        BookingSignInPassword.click();
        BookingSignInPassword.clear();
        BookingSignInPassword.sendKeys(SignInPasswordLabels.PASSWORD2.getLabel());
        SubmitSignInButton.click();
        return new Booking_Homepage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public enum SignInPasswordLabels {
        PASSWORD1("seemac123"),
        PASSWORD2("Sharanya@323");

        private final String label;

        SignInPasswordLabels(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

}


