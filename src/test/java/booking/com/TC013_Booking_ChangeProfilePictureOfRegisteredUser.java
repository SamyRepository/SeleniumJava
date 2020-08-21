package booking.com;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import ksrtc.TestBase;
import utils.Report;

public class TC013_Booking_ChangeProfilePictureOfRegisteredUser extends TestBase {
    SoftAssertions softly = new SoftAssertions();

    @Test
    @Story("My Profile")
    @Description("To change the profile picture of registered user")
    @Step("To run TC013_Booking_ChangeprofilePicture")

    public void changeUserProfilePic() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());

        getBookingHomepage().clickOnSignIn().setUsername2().setSignInPassword2().selectUserIcon()
                .clickOnDashBoardLinkToGoToDashboardPage().clickEditYourProfileToGoToSettingsPage()
                .changeProfilePicture().uploadNewProfilePic().saveProfilePic();
//Added comment for git
    }
}
