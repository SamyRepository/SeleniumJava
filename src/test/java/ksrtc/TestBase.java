package ksrtc;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import helper.SeleniumHelper;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.*;
import utils.Report;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class TestBase {

    public static ExtentReports EXTENT_REPORTS;
    public static ExtentTest EXTENT_TEST_LOGGER;
    public static RemoteWebDriver driver;
    public static LoginPage loginPage;
    public static KSRTC_HomePage ksrtc_homePage;
    public static BookMyShow_HomePage bookMyShow_homePage;
    public static Booking_Homepage booking_homepage;
    public static String baseWindowHandle;
    public static KSRTC_LoginPage ksrtc_loginPage;
    public static SessionId session_id = null;
    private static final Logger LOGGER = LogManager.getLogger(TestBase.class);


    static String driverPath = System.getProperty("user.dir") + "\\src\\main\\resources\\";

    public RemoteWebDriver getDriver() {
        return driver;
    }


    @Step("Select driver to run")
    private RemoteWebDriver setDriver(String browserType, String appURL) throws IOException {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver(appURL);
                break;
            case "firefox":
                driver = initFirefoxDriver(appURL);
                break;
            case "IE":
                driver = initIEDriver(appURL);
                break;
            default:
                System.out.println("browser : " + browserType
                        + " is invalid, Launching chrome as browser of choice..");
                driver = initChromeDriver(appURL);
        }
        return driver;
    }

    private static RemoteWebDriver initIEDriver(String appURL) {
        LOGGER.info("Launching IE with new profile..");
        System.setProperty("webdriver.ie.driver", driverPath
                + "IEDriverServer.exe");

        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        RemoteWebDriver driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to(appURL);

        return driver;
    }


    /**
     * This Method initiates the Chrome browser and
     *
     * @param appURL
     * @return
     * @throws IOException
     */
    @Step("Launch Chrome driver.")
    public RemoteWebDriver initChromeDriver(String appURL) {

        try {

            LOGGER.info("Launching google chrome with new profile..");
            System.setProperty("webdriver.chrome.driver", driverPath
                    + "chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            LoggingPreferences logPrefs = new LoggingPreferences();
            capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
             driver = new ChromeDriver(capabilities);
            LOGGER.info("Opening chrome..");

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
            driver.navigate().to(appURL);
            LOGGER.info("open Url ...");
           // return driver;
        }catch(SessionNotCreatedException  e){
            System.out.println("Session is not created"+e);
        }
        return driver;
    }


    /**
     * This method initiates the Firefox browser
     *
     * @param appURL
     * @return
     */
    @Step("Launch Firefox driver")
    private static RemoteWebDriver initFirefoxDriver(String appURL) {
        LOGGER.info("Launching Firefox browser...");
        System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
        RemoteWebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.navigate().to(appURL);
        return driver;

    }


    public static void CreateExtentReport(String TestCaseName, String TestCaseDescription) {
        EXTENT_REPORTS = Report.Instance(TestCaseName);
       EXTENT_TEST_LOGGER = EXTENT_REPORTS.startTest(TestCaseName, TestCaseDescription);
    }


    @BeforeClass
    @Parameters({"browserType", "appURL"})
    public void initializeTestBaseSetup(String browserType, String appURL) {

        try {

            if (driver != null) {
                session_id = driver.getSessionId();
                baseWindowHandle = driver.getWindowHandle();
            }
            if (session_id == null) {
                System.out.println("Creating Driver");
                setDriver(browserType, appURL);
                baseWindowHandle = driver.getWindowHandle();
            }
        } catch (Exception e) {
            System.out.println("Error....." + e.getStackTrace());
        }
    }

    protected LoginPage getAppLaunchPage() {
        setAppLaunchPage();
        return loginPage;
    }


    public void setAppLaunchPage() {
        //SeleniumHelper helper=new SeleniumHelper(driver,EXTENT_TEST_LOGGER);
        loginPage = new LoginPage(driver, EXTENT_TEST_LOGGER, new SeleniumHelper(driver));
    }


    protected KSRTC_HomePage getKSRTCLaunchPage() {
        setKSRTCHomePage();
        return ksrtc_homePage;
    }

    protected Booking_Homepage getBookingHomepage(){
        setBookingHomePage();
        return booking_homepage;
    }

    protected BookMyShow_HomePage getBookMyShowLaunchPage(){
        setBookMyShowHomePage();
        return bookMyShow_homePage;
    }

    public KSRTC_HomePage switchToHomePageAfterClosingCurrentWindow() {
        driver.close();
        driver.switchTo().window(baseWindowHandle);
        return new KSRTC_HomePage(driver, EXTENT_TEST_LOGGER, new SeleniumHelper(driver));
    }

    public void setKSRTCHomePage() {

        ksrtc_homePage = new KSRTC_HomePage(driver, EXTENT_TEST_LOGGER, new SeleniumHelper(driver));

    }

    public void setBookMyShowHomePage(){
        bookMyShow_homePage = new BookMyShow_HomePage(driver, EXTENT_TEST_LOGGER, new SeleniumHelper(driver));


    }

    public void setBookingHomePage(){
        booking_homepage = new Booking_Homepage(driver, EXTENT_TEST_LOGGER, new SeleniumHelper(driver));

    }


    public void PostConditionWithQuitDriver() {
        System.out.println("In report creation");
        EXTENT_TEST_LOGGER.log(LogStatus.FAIL, EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        //driver.quit();
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, "Browser is closed");
        EXTENT_REPORTS.endTest(EXTENT_TEST_LOGGER);
        EXTENT_REPORTS.flush();
        EXTENT_REPORTS.close();

    }

    public void PostCondition() {
        LOGGER.info("In report creation");
        EXTENT_TEST_LOGGER.log(LogStatus.INFO, EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        EXTENT_REPORTS.endTest(EXTENT_TEST_LOGGER);
        EXTENT_REPORTS.flush();
        LOGGER.info("Flush Extent Report");
        EXTENT_REPORTS.close();
    }
}
