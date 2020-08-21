package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KSRTC_HelpLinePortalPage extends PageBase {

    @FindBy(xpath = "//div[@class='text-center']")
    private WebElement HelplinePageHeaderText;


    KSRTC_HelpLinePortalPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
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

    private boolean waitUntilPageLoaded() {
        try{
            driver.findElement(By.xpath("//div[@class='col-lg-6 d-none d-lg-block text-centre']")).isDisplayed();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public String verifyTextOfHelpLinePortal(){
     return  HelplinePageHeaderText.getText();
    }


}
