package booking.com;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import ksrtc.TestBase;
import utils.Report;

import java.util.Arrays;
import java.util.List;

public class TC009_Booking_VerifyFooterTopMenu extends TestBase {
    SoftAssertions softly = new SoftAssertions();

    @Test
    @Description("To get all links present at the Footer")
    @Story("Booking Homepage")
    @Severity(SeverityLevel.CRITICAL)
    @Step("To run TC009_Booking_VerifyFooterTopMenu.java")

    public void toGetFooterNavigationLinksOnHomePage() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());

        SoftAssertions softly = new SoftAssertions();
        List<String> footerNavigationLinks = getBookingHomepage().getFooterTopMenu();
        String footerNavigationLinksArray[] = footerNavigationLinks.get(0).split("\\n?\\r");
        System.out.println(Arrays.asList(footerNavigationLinksArray));
        List<String> expectedFooterNavigationLinks = Arrays.asList("Mobile version", "Your account", "Make changes to your booking online",
                "Customer Service help", "Become an affiliate", "Booking.com for Business");
        softly.assertThat(expectedFooterNavigationLinks.containsAll(Arrays.asList(footerNavigationLinksArray)));
    }
}
