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

import java.util.List;

public class Booking_BannerInfoPage extends PageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(Booking_BannerInfoPage.class);

    @FindBy(xpath = "//ul[@class='bui-accordion bui-u-bleed@small']/li")
    private List<WebElement> CountryRestrictionAdvisoryInfo;

    @FindBy(xpath = "//a[@class='bui-link bui-link--primary close_warning']")
    private WebElement CloseWarning;

    @FindBy(linkText = " Help Centre")
    private WebElement HelpCentreLink;

    Booking_BannerInfoPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
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
        return driver.findElement(By.xpath("//h1[@class='bui-f-font-display_three']")).isDisplayed();
    }

    public List<String> getCountryRestrictionAdvisoryInfo() {
        if (helper.isDisplayed(CloseWarning)) {
            CloseWarning.click();
        }
        return helper.getElementsTextValues(CountryRestrictionAdvisoryInfo);
    }

    public void clickOnHelpCentreLink() {
        HelpCentreLink.click();
    }

    public enum Information {

        SUPPORTED_LANGUAGES_IN_GERMANY("[Most often used by people in Germany\n" + "Deutsch\n" + "English (UK)\n" + "English (US)\n" + "简体中文\n" + "Русский\n" + "Español]"),
        BANNER_MESSAGE("Please check for travel restrictions. Travel may be permitted only for certain purposes and in particular, touristic travel may not be allowed.");

        private final String label;

        Information(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}



