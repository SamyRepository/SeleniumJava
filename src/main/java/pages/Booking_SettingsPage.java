package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Booking_SettingsPage extends PageBase {

    @FindBy(xpath = "//*[contains(text(), 'Change photo')]")
            private WebElement ChangeYourPicButton;

    @FindBy(css = "#avatar-upload-file")
            private WebElement BrowsePic;

    @FindBy(xpath = "//span[@class='btn btn-primary submit-avatar']")
            private WebElement SaveNewPic;

    Booking_SettingsPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver,this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        return false;
    }

    public Booking_SettingsPage changeProfilePicture(){
        ChangeYourPicButton.click();
        return this;
    }

    public Booking_SettingsPage uploadNewProfilePic(){
        BrowsePic.sendKeys("D:\\IdeaProjects\\Selenium\\digitalCommunication\\src\\main\\resources\\Anand2.JPG");
        return this;
    }

    public Booking_SettingsPage saveProfilePic(){
        SaveNewPic.click();
        helper.waitUntilElementNotVisible(By.xpath("//div[@class='user-avatar-upload']"));
        return this;
    }
}
