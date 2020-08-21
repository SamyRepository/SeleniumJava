package pages;

public interface IPage {
    // Returns true if application is in the page requested
    boolean verifyPageIsLoaded() throws Exception;
}
