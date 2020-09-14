
package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Booking_SignInPage extends PageBase {

    @FindBy(css = "#username")
    private WebElement Username;

    @FindBy(css = ".bui-button.bui-button--large.bui-button--wide")
    private WebElement NextButton;

    @FindBy(xpath = "//span[@class='bui-button__text' and normalize-space(text()) = 'Sign in']")
    private WebElement SubmitSignInButton;

    @FindBy(css = "#password")
    private WebElement BookingSignInPassword;

    @FindBy(xpath = "//span[contains(@class, 'bui-traveller-header__selection-text') and normalize-space(text()) = 'English']")
    private WebElement SelectEnglishLanguage;

    Booking_SignInPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        return false;
    }

    public Booking_SignInPage setUsername() {
        Username.click();
        Username.clear();
        Username.sendKeys(SignInLoginCredentialLabels.USERNAME2.getLabel());
        NextButton.click();
        return new Booking_SignInPage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public Booking_Homepage setSignInPassword() {
        BookingSignInPassword.click();
        BookingSignInPassword.clear();
        BookingSignInPassword.sendKeys(SignInLoginCredentialLabels.PASSWORD2.getLabel());
        SubmitSignInButton.click();
        /*if (SelectEnglishLanguage.isDisplayed()) {
            SelectEnglishLanguage.click();
        }*/
        return new Booking_Homepage(driver,EXTENT_TEST_LOGGER,helper);
    }

    public Booking_Homepage submitLoginCredentials() {
        SubmitSignInButton.click();
        return new Booking_Homepage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public enum SignInLoginCredentialLabels {
        USERNAME1("chourasia.seema@gmail.com"),
        USERNAME2("chaurasia.anand@gmail.com"),
        PASSWORD1("seemac123"),
        PASSWORD2("Sharanya@323");

        private final String label;
        SignInLoginCredentialLabels(String label) {
            this.label = label;
        }
        public String getLabel() {
            return label;
        }
    }

}
