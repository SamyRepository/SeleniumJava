package booking.com;

import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import ksrtc.TestBase;
import utils.Report;

import java.util.List;

public class TC010_Booking_VerifyFooterNavigationLinks extends TestBase {
    SoftAssertions softly = new SoftAssertions();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Booking Homepage Footer navigation links")
    @Description("To get all the footer navigation links")
    @Step("To run TC010_Booking_VerifyFooterNavigationLinks")

    public void getFooterNavigationLinks() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());

        List<String> FooterNavigationLinks = getBookingHomepage().getFooterNavigationLinks();
        System.out.println(FooterNavigationLinks);


    }
}
