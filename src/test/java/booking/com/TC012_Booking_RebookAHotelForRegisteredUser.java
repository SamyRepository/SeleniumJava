package booking.com;

import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import ksrtc.TestBase;
import utils.Report;



public class TC012_Booking_RebookAHotelForRegisteredUser extends TestBase {
    SoftAssertions softly = new SoftAssertions();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Booking Details of  registered user")
    @Description("To validate booking details of the registered user")
    @Step("T run TC012_Booking_RebookHotelForRegisteredUser")

    public void rebookHotelForRegisteredUser() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());

        String previousPrice = getBookingHomepage().clickOnSignIn().setUsername2().setSignInPassword2().selectUserIcon().
                clickOnBookingLinkOnRegisteredUserIcon().getHotelModernoPreviousPrice();
        System.out.println("Hotel Moderno is previously booked at a price of :" + previousPrice);

    }
}
