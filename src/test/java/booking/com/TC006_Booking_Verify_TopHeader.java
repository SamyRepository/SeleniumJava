package booking.com;

import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import ksrtc.TestBase;
import utils.Report;

import java.util.Arrays;
import java.util.List;

public class TC006_Booking_Verify_TopHeader extends TestBase {
    private SoftAssertions softly = new SoftAssertions();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifying Top header of Booking homepage ")
    @Story("Booking Homepage")
    @Step(" Start TC006_Booking_Verify_TopHeader")

    public void verifyTopHeaderOfHomepage() {
        EXTENT_REPORTS = Report.Instance(this.getClass().getName());
        try {
            List<String> topHeaderItems = getBookingHomepage().getTopHeaderItems();
            System.out.println("Top header Items" + topHeaderItems);
            String[] topHeaderItemsArray = topHeaderItems.get(0).split("\\r?\\n");
            System.out.println(Arrays.asList(topHeaderItemsArray));
            List<String> expectedTopHeader=Arrays.asList("€","List your Property","Register" ,"Sign in");
            softly.assertThat(expectedTopHeader.containsAll(Arrays.asList(topHeaderItemsArray)));

        } catch (Exception e) {
            PostConditionWithQuitDriver();
        } finally {
            softly.assertAll();
        }
    }

}
