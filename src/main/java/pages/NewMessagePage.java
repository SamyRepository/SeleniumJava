package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Report;

public class NewMessagePage extends PageBase {

    private SeleniumHelper helper;
    private RemoteWebDriver driver;
    private ExtentTest EXTENT_TEST_LOGGER;
    private static final Logger LOGGER = LoggerFactory.getLogger(NewMessagePage.class);

    @FindBy(css = "h3.modal-common__title")
    private WebElement NewMessageBodyLabel;

    @FindBy(id = "recipientSelect")
    private WebElement Recipient;

    @FindBy(id = "subjectSelect")
    private WebElement MessageSubject;

    @FindBy(id = "body")
    private WebElement MessageBody;

    @FindBy(xpath="//*[@data-e2e-id='send-message-button']/button")
    private WebElement SendMessageButton;

    @FindBy(xpath = "//*[@data-e2e-id='cancel-compose-message-button']/button")
    private WebElement CancelMessageButton;


    public NewMessagePage(RemoteWebDriver driver, ExtentTest extent_test_logger, SeleniumHelper helper) {
        super(driver,extent_test_logger,helper);
        this.helper = helper;
        this.driver = driver;
        this.EXTENT_TEST_LOGGER = extent_test_logger;
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded()  {
        try {
            driver.findElement(By.cssSelector("h3.modal-common__title")).isDisplayed();
            EXTENT_TEST_LOGGER.log(LogStatus.INFO, "New Message Page is loaded", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            return true;
        } catch (Exception e) {
            EXTENT_TEST_LOGGER.log(LogStatus.FAIL, "Problem in New Message page loading", EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
            return false;
        }
    }

    public NewMessagePage setRecipient(String recipient){
        Recipient.sendKeys(recipient);
        return this;
    }

    public NewMessagePage selectRecipient(String recipient){
        helper.selectedOptionFromDropdown(Recipient, recipient );
        return this;
    }

    public String getSelectRecipient(){
        return helper.getSelectedOptionFromDropdown(Recipient);

    }


    public NewMessagePage selectSubject(String subject){
        helper.selectedOptionFromDropdown(MessageSubject, subject );
        return this;
    }

    public String getSelectSubject(){
        return helper.getSelectedOptionFromDropdown(MessageSubject);

    }

    public NewMessagePage setMessageSubject(String subject){
        MessageSubject.sendKeys(subject);
        return this;
    }

    public NewMessagePage setMessageBody(String body){
        MessageBody.sendKeys(body);
        return this;
    }

    public MessagingPage clickOnCancelButton() throws Exception {
        CancelMessageButton.click();
        return new MessagingPage(driver,EXTENT_TEST_LOGGER,helper);

    }

    public MessagingPage clickOnSendButton() throws Exception {
        SendMessageButton.click();
        return new MessagingPage(driver,EXTENT_TEST_LOGGER,helper);
    }

    public boolean verifyStateOfSendButton(final String Logger){
        boolean buttonState=SendMessageButton.isEnabled();
        if(buttonState){
            EXTENT_TEST_LOGGER.log(LogStatus.INFO,"'SEND' button is enabled on Messaging Page "+ Logger,EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        }else {
            EXTENT_TEST_LOGGER.log(LogStatus.INFO,"'SEND' button is disabled on Messaging Page "+ Logger, EXTENT_TEST_LOGGER.addScreenCapture(Report.CaptureScreen(driver)));
        }
        return buttonState;
    }

    public enum MessagingData {
        // @formatter:off
        RECIPIENT("Porsche Connect Support Great Britain"),
        SUBJECT_MY_PORSCHE("My Porsche"),
        SUBJECT_CONNECT("Porsche Connect Services"),
        SUBJECT_TECHNICAL_PROBLEM("Technical problems"),
        SUBJECT_CONTRACT("Questions about contract"),
        SUBJECT_COMPLAINT("Complaint"),

        BODY("Test Body");
        // @formatter:on

        private final String label;

        MessagingData(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }


}
