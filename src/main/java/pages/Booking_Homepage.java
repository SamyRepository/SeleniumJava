package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Report;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Booking_Homepage extends PageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(Booking_Homepage.class);

    @FindBy(xpath = "//li[@class='user_center_option uc_language js-uc-language ' and @data-id='language_selector']")
    private WebElement LanguageSelector;

    @FindBy(xpath = "//div[@class='select_foldout_wrap'][1]")
    private List<WebElement> langSupportedInGermany;

    @FindBy(xpath = "//p[@class='bui-alert__text'][1]")
    private List<WebElement> BannerMessage;

    @FindBy(xpath = "//a[@class='bui-link bui-link--primary']")
    private WebElement BannerReadMoreLink;

    @FindBy(xpath = "//a[@class='xpb__link' and @data-decider-header='flights']")
    private WebElement FlightLink;

    @FindBy(xpath = "//*[contains(text(), 'Flight + Hotel')]")
    private WebElement FlightAndHotelLink;

    @FindBy(xpath = "//div[@class='cross-product-bar__wrapper ']")
    private List<WebElement> CrossbarWrapper;

    @FindBy(xpath = "//div[@class='header-wrapper']")
    private List<WebElement> TopHeader;

    @FindBy(xpath = "//*[contains(text(), 'Register')]")
    private WebElement RegisterButton;

    @FindBy(css = ".user_avatar.user_avatar--circle.user_avatar--normalised.user_avatar--initial.ge-no-yellow-img_border")
    private WebElement UserIcon;

    @FindBy(xpath = "(//div[@class='sign_in_wrapper'])[2]")
    private WebElement SignInButton;

    @FindBy(xpath = "//li[@class='ia_section active']/ul")
    private List<WebElement> LovedDestination;

    @FindBy(xpath = "(//div[@class='footerconstraint-inner clearfix'])[2]")
    private List<WebElement> FooterTopMenu;

    @FindBy(css = "#footer_links")
    private List<WebElement> FooterNavigationLinks;


    public Booking_Homepage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
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
        return driver.findElement(By.xpath("//img[@id='logo_no_globe_new_logo']")).isDisplayed();
    }

    public Booking_Homepage clickOnLanguageSelector() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        LanguageSelector.click();
        EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Language dropdown is clicked", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        return this;
    }

    public List<String> getLanguageSupportedInGermany() {
        return helper.getElementsTextValues(langSupportedInGermany);
    }

    public List<String> getBannerMessage() {
        return helper.getElementsTextValues(BannerMessage);
    }

    public Booking_BannerInfoPage clickOnReadMoreToGetBannerInfoPage() {
        BannerReadMoreLink.click();
        return new Booking_BannerInfoPage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public Booking_FlightBookingPage clickOnFlightLinkGoToFlightBookingPage() {
        FlightLink.click();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Flight link on Booking homepage is clicked: ", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        return new Booking_FlightBookingPage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public Booking_FlightAndHotelPage clickOnFlightAndHotelLink() {
        FlightAndHotelLink.click();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Flight+Hotel link on Booking homepage is clicked: ", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        return new Booking_FlightAndHotelPage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public List<String> getCrossbarItems() {
        return helper.getElementsTextValues(CrossbarWrapper);
    }

    public List<String> getTopHeaderItems() {
        return helper.getElementsTextValues(TopHeader);
    }

    public Booking_RegisteredUserProfileMenuPage selectUserIcon() {
        UserIcon.click();
        return new Booking_RegisteredUserProfileMenuPage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public Booking_SignInPage clickOnSignIn() {
        SignInButton.click();
        return new Booking_SignInPage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public List<String> getLovedDestinationItems() {
        return helper.getElementsTextValues(LovedDestination);
    }

    public List<String> getFooterTopMenu() {
        return helper.getElementsTextValues(FooterTopMenu);
    }

    public List<String> getFooterNavigationLinks(){
        return helper.getElementsTextValues(FooterNavigationLinks);
    }

}


