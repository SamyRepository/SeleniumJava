package pages;


import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PageBase implements IPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageBase.class);
    protected SeleniumHelper helper;
    protected RemoteWebDriver driver;
    protected ExtentTest EXTENT_TEST_LOGGER;

    PageBase(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        this.helper=helper;
        this.driver=driver;
        this.EXTENT_TEST_LOGGER=EXTENT_TEST_LOGGER;
        try {
            LOGGER.info("Initializing {}", this.getClass().getName());
            if (!this.verifyPageIsLoaded()) {
                throw new RuntimeException("Could not verify page " + this.getClass().getName());
            }
        } catch (Exception exc) {
            LOGGER.error("Failed in loading Page", exc.getStackTrace());

        }

    }



}
