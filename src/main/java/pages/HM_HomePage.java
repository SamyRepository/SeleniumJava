package pages;

import com.relevantcodes.extentreports.ExtentTest;
import helper.SeleniumHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HM_HomePage extends PageBase {

    @FindBy(css = "button.modalclose")
    private WebElement CloseModal;

    @FindBy(css = "div.account.parbase")
    private WebElement SignInIcon;

    @FindBy(xpath = "//ul[@class='menu__session']/li[3]")
    private WebElement FavouriteButton;

    @FindBy(xpath = "(//div[@class='list']/div)[1]")
    private WebElement WomenTab;

    @FindBy(css = "#main-search")
    private WebElement SearchButton;


    public HM_HomePage(RemoteWebDriver driver, ExtentTest EXTENT_TEST_LOGGER, SeleniumHelper helper) {
        super(driver, EXTENT_TEST_LOGGER, helper);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verifyPageIsLoaded() throws Exception {
        return false;
    }

    public HM_SignInPage clickOnSignInIconToOpenSignInPage(){
        SignInIcon.click();
        return new HM_SignInPage(driver,EXTENT_TEST_LOGGER,helper);
    }

    public HM_FavouritePage clickOnFavouriteButton(){
        FavouriteButton.click();
        return new HM_FavouritePage(driver,EXTENT_TEST_LOGGER,helper);
    }

    public HM_WomenFashionMenuList clickOnWomenTabToOpenWomenFashionMenuList(){
        WomenTab.click();
        return new HM_WomenFashionMenuList(driver,EXTENT_TEST_LOGGER,helper);
    }

    public HM_WomenJacketFashionPage clickOnSearchWomenJacketToOpenWomenJacketFashionPage(){
        SearchButton.click();
        SearchButton.clear();
        SearchButton.sendKeys("Women Jacket");
        SearchButton.sendKeys(Keys.ENTER);
       return new HM_WomenJacketFashionPage(driver, EXTENT_TEST_LOGGER, helper);
    }
}
