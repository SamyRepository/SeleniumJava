package helper;

import com.google.common.base.Predicate;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;


public class SeleniumHelper {

    private static final int TIMEOUT_MS = 2000;
    private static final int TIMEOUT_SECONDS = 40;
    private static final int POLL_EVERY_MS = 50;
    public static int DRIVER_WAIT = 10;
    private RemoteWebDriver driver;
    private static final Logger LOGGER = LoggerFactory.getLogger(SeleniumHelper.class);

    public SeleniumHelper(final RemoteWebDriver driver) {
        this.driver = driver;

    }

    public void sendKeysUsingAction(String keys) {
        Actions action = new Actions(driver);
        action.sendKeys(keys).build().perform();
    }


    public WebDriverWait newWait() {
        return new WebDriverWait(driver, TIMEOUT_SECONDS);
    }


    public String getSelectedOptionFromDropdown(final WebElement webElement) {
        final String text = new Select(webElement).getFirstSelectedOption().getText();
        return text;
    }

    public void selectedOptionFromDropdown(final WebElement webElement, final String value) {
        webElement.click();
        Select dropdown = new Select(webElement);
        dropdown.selectByVisibleText(value);
    }

    public WebElement findChildElementSearchByText(String MainMenu, String value) {
        return driver.findElements(By.xpath(MainMenu)).stream().filter(webElement -> webElement.getText().contains(value))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Could not find menu item with id'%s'", value)));

    }

    public WebElement findChildElementSearchByText(List<WebElement> MainMenu, String value) {
        return MainMenu.stream().filter(webElement -> webElement.getText().contains(value))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Could not find menu item with id'%s'", value)));

    }


    public List<String> getElementsAttributeValue(final List<WebElement> MainMenu, String AttributeName) {

        final List<String> elementValues = new ArrayList<>();
        MainMenu.stream()
                .forEach(element -> elementValues.add(element.getAttribute(AttributeName)));
        return elementValues;


    }

    public List<WebElement> getElementsByXpath(String locator) {
        return driver.findElements(By.xpath(locator));
    }


    public List<String> getElementsTextValues(final List<WebElement> Elements) {
        final List<String> elementValues = new ArrayList<String>();
        Elements.stream()
                .forEach(element -> {
                    elementValues.add(element.getText());
                });
        return elementValues;
    }


    public String switchToSecondWindow() {
        final String baseWindowId = getActiveWindowId();
        String secondWindowId = null;
        final Set<String> allWindows = getListOfOpenWindows();
        for (String window : allWindows) {
            if (!window.equals(baseWindowId)) {
                driver.switchTo().window(window);
                secondWindowId = window;
            }
        }
        return secondWindowId;
    }

    public void closeCurrentWindow() {
        driver.close();
    }

    public String getActiveWindowId() {
        return driver.getWindowHandle();
    }

    public Set<String> getListOfOpenWindows() {
        return driver.getWindowHandles();
    }

    public void switchToWindowWithGivenId(final String windowId) {
        driver.switchTo().window(windowId);
    }


    public String ElementGetText(WebElement Element) {
        String ElementText = "";
        try {
            WebDriverWait wait = new WebDriverWait(driver, DRIVER_WAIT);
            wait.until(ExpectedConditions.visibilityOf(Element));
            wait.until(ExpectedConditions.elementToBeClickable(Element));
            boolean isElementDisplayed = Element.isDisplayed();
            if (isElementDisplayed) {
                ElementText = Element.getText();
                if (!ElementText.isEmpty()) {
                    return ElementText;
                }
            }
        } catch (Exception e) {
            return ElementText;
        }
        return ElementText;
    }

    public void moveToElement(final WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    public void moveToElement(final By by) {
        moveToElement(driver.findElement(by));
    }


    public void scrollElementToVerticalCenter(final WebElement element) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center'});",
                element);
        Thread.sleep(TIMEOUT_MS);
    }

    public boolean isDisplayed(final WebElement element) {
        return isDisplayed(element, 5);
    }

    public boolean isDisplayed(final WebElement element, final long timeOutInSeconds) {
        try {
            new WebDriverWait(driver, timeOutInSeconds).pollingEvery(200, TimeUnit.MILLISECONDS)
                    .ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {

            return false;
        }
    }

    public boolean isDisplayed(final String Locator, final long timeOutInSeconds) {
        try {
            new WebDriverWait(driver, timeOutInSeconds).pollingEvery(200, TimeUnit.MILLISECONDS)
                    .ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(Locator))));
            return true;
        } catch (TimeoutException e) {

            return false;
        }
    }



    public void waitUntilElementNotVisible(final By by) {
        waitUntil(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public <T> T waitUntil(Function<WebDriver, ?> function) {
        return waitUntil(POLL_EVERY_MS, function);
    }

    public <T> T waitUntil(int pollEveryMs, Function<WebDriver, ?> function) {
        return waitUntil(pollEveryMs, TIMEOUT_SECONDS, function);
    }

    public <T> T waitUntil(int pollEveryMs, long timeOutAfterSeconds, Function<WebDriver, ?> function) {
        final WebDriverWait wait = new WebDriverWait(driver, timeOutAfterSeconds);
        wait.pollingEvery(pollEveryMs, TimeUnit.MILLISECONDS);
        return (T) wait.until(function);
    }


    public String hoverOnElementAndGetText(WebElement MainMenu, WebElement SubMenu,
                                           String... description) {
        String myString = "";
        try {
            WebDriverWait wait = new WebDriverWait(driver, DRIVER_WAIT);
            wait.until(ExpectedConditions.elementToBeClickable(MainMenu));
            WebElement mainMenu = MainMenu;
            Actions actions = new Actions(driver);
            actions.moveToElement(mainMenu).build().perform();
            myString = SubMenu.getText();
            int WhileCntr = 1;
            while (myString.isEmpty()) {
                actions.moveToElement(mainMenu).build().perform();
                Thread.sleep(TIMEOUT_MS);
                myString = SubMenu.getText();
                Thread.sleep(TIMEOUT_MS);
                if (WhileCntr > 3) {
                    LOGGER.error("Element not found" + SubMenu);
                }
                WhileCntr = WhileCntr + 1;

            }
        } catch (Exception e) {
            LOGGER.error("Element not found" + SubMenu);
        }
        return myString;
    }

    public WebElement waitUntilElementVisible(final By by) {
        return waitUntil(ExpectedConditions.visibilityOfElementLocated(by));
    }


    public WebElement waitUntilElementToBeClickable(final WebElement we) {
        return waitUntil(ExpectedConditions.elementToBeClickable(we));
    }

    public WebElement waitUntilElementToBeClickableIgnoringStalenessException(final WebElement we) {
        return new WebDriverWait(driver, TIMEOUT_SECONDS).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(we));
    }

    public WebElement waitUntilElementToBeClickable(final By by) {
        return waitUntil(ExpectedConditions.elementToBeClickable(by));
    }


    public void waitUntilElementNotVisible(final long timeOutInSeconds, final By by) {
        waitUntil((int) timeOutInSeconds, ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitUntilNumberOfElementsWithLocator(final By locator, final int count) {
        waitUntil(ExpectedConditions.numberOfElementsToBe(locator, count));
    }

    public void clickElementByIndex(final List<WebElement> elementList, final int index) {
        elementList.get(index).click();
    }

    public void click(final WebElement element) {
        newWait().ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(WebDriverException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.and(ExpectedConditions.elementToBeClickable(element),
                        ExpectedConditions.visibilityOf(element)));
        element.click();
    }

    public WebElement waitUntilElementVisible(final WebElement webElement) {
        return waitUntil(ExpectedConditions.visibilityOf(webElement));
    }

    public void clearAutoFillInputContainer(final WebElement element) {
        waitUntilElementVisible(element);
        element.sendKeys(Keys.BACK_SPACE);
    }


}
