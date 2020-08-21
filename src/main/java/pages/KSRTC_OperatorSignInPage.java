package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KSRTC_OperatorSignInPage extends PageBase {

    @FindBy(xpath = "//div[@class='formheadercolor']")
    private WebElement FormHeader;


    KSRTC_OperatorSignInPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        try {
            //change here
            return waitUntilPageLoaded();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitUntilPageLoaded() {
        return driver.findElement(By.xpath("//div[@class='loginHeader']")).isDisplayed();
    }

    public String verifyTextOfFormHeader() {
        return FormHeader.getText();

    }
}
