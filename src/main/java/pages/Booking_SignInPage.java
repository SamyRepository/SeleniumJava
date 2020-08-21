package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Booking_SignInPage extends PageBase {

    @FindBy(css = "#username")
    private WebElement Username;

    @FindBy(css = ".bui-button.bui-button--large.bui-button--wide")
    private WebElement NextButton;

    @FindBy(xpath = "//span[@class='bui-button__text']")
    private WebElement SubmitSignInButton;

    @FindBy(css = "#password")
    private WebElement BookingSignInPassword;

    Booking_SignInPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        return false;
    }


    public Booking_SignInPasswordPage setUsername1(){
        Username.click();
        Username.clear();
        Username.sendKeys(SignInUserNameLabels.USERNAME1.getLabel());
        NextButton.click();
        return new Booking_SignInPasswordPage(driver,EXTENT_TEST_LOGGER,helper);
    }

    public Booking_SignInPasswordPage setUsername2(){
        Username.click();
        Username.clear();
        Username.sendKeys(SignInUserNameLabels.USERNAME2.getLabel());
        NextButton.click();
        return new Booking_SignInPasswordPage(driver,EXTENT_TEST_LOGGER,helper);
    }


    public enum SignInUserNameLabels{

        USERNAME1("chourasia.seema@gmail.com"),
        USERNAME2("chaurasia.anand@gmail.com");
        private final String label;

        SignInUserNameLabels(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
        }

}
