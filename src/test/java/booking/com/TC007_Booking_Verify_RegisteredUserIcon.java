package booking.com;

import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import ksrtc.TestBase;
import utils.Report;

import java.util.Arrays;
import java.util.List;

public class TC007_Booking_Verify_RegisteredUserIcon extends TestBase {
    SoftAssertions softly = new SoftAssertions();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verifying items of Register button")
    @Story("Sign in and User icon")
    @Step("Start TC007_Booking_Verify_UserIcon")

    public void verifyRegisteredUserIconOnHomepage() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());

        List<String> userIconItems = getBookingHomepage().clickOnSignIn().setUsername1()
                                    .setSignInPassword1().selectUserIcon().getRegisteredUserProfileMenuList();
        System.out.println("Registered User Icon items are:" + userIconItems.toString());
        String userIconItemsArray[]=userIconItems.get(0).split("\\r?\\n");
        System.out.println(Arrays.asList(userIconItemsArray));
        List<String> expectedRegisteredUserIconItems=Arrays.asList("Your account menu", "My dashboard", "Bookings", "Genius Loyalty Programme",
                "My reviews", "My wish lists", "Get the app", "Customer Service help", "Settings", "Travel Communities", "Exit menu");
        softly.assertThat(expectedRegisteredUserIconItems.containsAll(Arrays.asList(userIconItemsArray)));
    }

}
