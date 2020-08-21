package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TestNGListenerAdapter extends TestListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestNGListenerAdapter.class);

    private int RepeatCount = 0;
    private List<ITestResult> failedTestsRemove = new ArrayList<ITestResult>();


    //--------------- for Allure Report ----------------
    // Image attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }
    //--------------------------------------------------

    @Override
    public void onTestSkipped(ITestResult result) {
        RetryTest retryTest = (RetryTest) result.getMethod().getRetryAnalyzer();
        if (retryTest != null) {
            if (retryTest.getRerun()) {
                failedTestsRemove.add(result);
                RepeatCount = retryTest.getCount();
          /*  File order = new File (System.getProperty ("user.dir") + STBHelpers.getRESULTPHAT ( ) + "\\" + result.getTestClass ( ).getName ( ));
            try {
                FileUtils.moveDirectory (order, new File (order.getPath ( ) + "_" + RepeatCount));
            } catch (IOException e) {
                e.printStackTrace ( );
            }*/
                if (RepeatCount == 2) {
                    String description = result.getTestContext().getCurrentXmlTest().getParameter("Description");
                    result.getTestContext().getCurrentXmlTest().addParameter("Description", description + "<br> (AlphaNumericStringArray was " + RepeatCount + " times repeated)");
                    LOGGER.info( description);
                }
            }
        }
    }

    @Override
    public void onFinish(ITestContext context) {

        for (ITestResult result : failedTestsRemove) {
            context.getSkippedTests().removeResult(result);
        }

    }
}
