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

    @FindBy(xpath = "//div[@class='iux-file-upload']")
    private WebElement BrowsePic;

    @FindBy(xpath = "//span[@class='btn btn-primary submit-avatar']")
    private WebElement SaveNewPic;

    @FindBy(xpath = "//select[@name='user_title']")
    private WebElement Title;

    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement Firstname;

    @FindBy(xpath = "//input[@name='lastname']")
    private WebElement Lastname;

    @FindBy(xpath = "//input[@name='phone']")
    private WebElement PhoneNumber;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement Email;

    Booking_SettingsPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
    }
    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        return false;
    }
    public Booking_SettingsPage changeProfilePicture() {
        ChangeYourPicButton.click();
        return this;
    }


    public Booking_SettingsPage uploadNewProfilePic() {
        BrowsePic.sendKeys("D:\\IdeaProjects\\Selenium\\digitalCommunication\\src\\main\\resources\\Anand2.JPG");
        return this;
    }
    public Booking_Homepage saveProfilePic() {
        SaveNewPic.click();
        helper.waitUntilElementNotVisible(By.xpath("//div[@class='user-avatar-upload']"));
        return new Booking_Homepage(driver,EXTENT_TEST_LOGGER,helper);
    }

    public String getRegisteredUserTitle() {
        return Title.getAttribute("value");
    }
    public String getRegisteredUserFirstName() {
        return Firstname.getAttribute("value");
    }
    public String getRegisteredUserLastName() {
        return Lastname.getAttribute("value");
    }
    public String getRegisteredUserEmail() {
        return Email.getAttribute("value");
    }

    public String getRegisteredUserPhone() {
        return PhoneNumber.getAttribute("value");
    }
    public enum RegisteredUserProfileDetails {
        TITLE("Mr"),
        FIRSTNAME("Anand"),
        LASTNAME("Chaurasia"),
        PHONE("+4917619981230"),
        EMAIL("chaurasia.anand@gmail.com");

        private final String label;

        RegisteredUserProfileDetails(String label) {
            this.label = label;
        }
        public String getLabel() {
            return label;
        }
    }
}
