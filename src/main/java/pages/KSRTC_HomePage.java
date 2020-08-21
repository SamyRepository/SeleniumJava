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

public class KSRTC_HomePage extends PageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(KSRTC_HomePage.class);

    @FindBy(xpath = "//a[contains(text(),'Sign In')]")
    private WebElement SignIn;

    @FindBy(xpath = "//div[@class='col-md-2 mb-1 booking-input']")
    private WebElement SearchForBus;

    @FindBy(xpath = "//button[@class='close']")
    private WebElement closeAlert;

    @FindBy(xpath = "//*[contains(text(),'Operator / Franchisee')]")
    private WebElement operatorLink;

    @FindBy(xpath = "//*[contains(text(),'CRM')]")
    private WebElement HelpLinePortal;

    @FindBy(xpath ="//div[@class='main-frs']//a")
    private  List<WebElement> ConnectedApplications;

    @FindBy(xpath="//div[@class='col-md-4 footer-info']")
    private List<WebElement> RelatedLinks;

    public KSRTC_HomePage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
    }
    @Override
    public boolean verifyPageIsLoaded() {
        try {
            //change here
            return waitUntilPageLoaded();
        } catch (Exception e) {

            return false;
        }
    }

    public boolean waitUntilPageLoaded() {
        return driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).isDisplayed();
    }

    public KSRTC_LoginPage clickOnSignIn() {
        if (helper.isDisplayed(closeAlert)) {
            closeAlert.click();
        }
        SignIn.click();
        EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Sign In button is clicked", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        return new KSRTC_LoginPage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public KSRTC_OperatorSignInPage clickOnOperatorLink() {
        if (helper.isDisplayed(closeAlert)) {
            closeAlert.click();
        }
        operatorLink.click();
        //Get total windows handle
        helper.switchToSecondWindow();
        EXTENT_TEST_LOGGER.log(LogStatus.PASS, "Operator/Franchisee option is selected", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));

        return new KSRTC_OperatorSignInPage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public KSRTC_HelpLinePortalPage clickOnHelpLinePortalOnHomePage(){
        if (helper.isDisplayed(closeAlert)) {
            closeAlert.click();
        }
        helper.clickElementByIndex(ConnectedApplications,2);
        helper.switchToSecondWindow();
        EXTENT_TEST_LOGGER.log(LogStatus.PASS, "CRM Helpline Portal option is selected", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        return  new  KSRTC_HelpLinePortalPage(driver,EXTENT_TEST_LOGGER,helper);

    }
    public List<String> getRelatedLinksOptions(){
        if (helper.isDisplayed(closeAlert)) {
            closeAlert.click();
        }
        return helper.getElementsTextValues(RelatedLinks);
    }
}

