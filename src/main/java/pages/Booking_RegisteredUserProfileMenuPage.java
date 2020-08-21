package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Booking_RegisteredUserProfileMenuPage extends PageBase {

    @FindBy(css = ".fly-dropdown.fly-dropdown--profile-menu.fly-dropdown_bottom.fly-dropdown_arrow-right")
    private List<WebElement> RegisteredUserProfileMenu;

    @FindBy(xpath = "//div[@class='profile-menu__item profile_menu__item--myreservations']")
    private WebElement BookingLink;

    @FindBy(xpath = "//div[@class='profile-menu__item profile_menu__item--mydashboard']")
    private WebElement MyDashBoardLink;


    Booking_RegisteredUserProfileMenuPage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        return false;
    }

    public List<String> getRegisteredUserProfileMenuList() {
        return helper.getElementsTextValues(RegisteredUserProfileMenu);
    }

    public Booking_RegisteredUserBookedTripsPage clickOnBookingLinkOnRegisteredUserIcon() {
        BookingLink.click();
        return new Booking_RegisteredUserBookedTripsPage(driver, EXTENT_TEST_LOGGER, helper);
    }

    public Booking_RegisteredUserDashBoardPage clickOnDashBoardLinkToGoToDashboardPage() {
        MyDashBoardLink.click();
        return new Booking_RegisteredUserDashBoardPage(driver, EXTENT_TEST_LOGGER, helper);
    }

}
