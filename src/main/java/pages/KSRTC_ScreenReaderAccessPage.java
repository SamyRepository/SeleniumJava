package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class KSRTC_ScreenReaderAccessPage extends PageBase {

    @FindBy(xpath = "//div[@class='ksrtc-list']//tr")
    private List<WebElement> tableLines;

    @FindBy(xpath = "//div[@class='ksrtc-list']//thead//tr")
    private List<WebElement> tableHeader;

    @FindBy(xpath = "//div[@class='table table-hover table_morecondensed table-sm facilityfont']")
    private WebElement Table;

    @FindBy(xpath = "//div[@class='ksrtc-list']//thead//tr/th")
    private List<WebElement> ScreenReaderTableHeader;

    @FindBy(xpath = "//*[@id='content']/div[1]/table/tbody/tr/td")
    private List<WebElement> ScreenReaderTableRowValues;

    @FindBy(xpath = "//a[@title='Home']")
    private WebElement HomeButton;

    @FindBy(xpath = "//*[@id='content']//table/tbody/tr")
    private List<WebElement> TableRowValues;

    KSRTC_ScreenReaderAccessPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        try {
            waitUntilPageLoaded();
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Screen Reader Access page is successfully loaded", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));

            return true;
        } catch (Exception exp) {
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Problem in loading Screen Reader Access Page", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            return false;

        }
    }

    private boolean waitUntilPageLoaded() {

        return driver.findElement(By.xpath("//div[@id='content']/h1")).isDisplayed();
    }

    public List<String> getHeaderOfTable() {
        return helper.getElementsTextValues(ScreenReaderTableHeader);
    }

    public List<String> getRowDataOfTable(int index) {
     List<WebElement> getElementOfEachRow=helper.getElementsByXpath("//*[@id='content']//table/tbody/tr["+index+"]//td");
     return helper.getElementsTextValues(getElementOfEachRow);
    }

    public KSRTC_HomePage clickOnHomeButtonAndGoToHomePage() {
        HomeButton.click();
        return new KSRTC_HomePage(driver, EXTENT_TEST_LOGGER, helper);
    }

}
