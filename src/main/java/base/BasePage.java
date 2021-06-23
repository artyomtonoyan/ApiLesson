package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.LoadableComponent;
import setup.DriverHelper;

public abstract class BasePage<T extends LoadableComponent<T>> extends LoadableComponent<T> {
    private static final Logger LOGGER = Logger.getLogger(BasePage.class);
    protected static final String BASE_URL = System.getProperty("selenium.url", "https://tree.taiga.io");
    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverHelper.get().getDriver();
        String message = "Setting Driver: " + driver.toString();
        LOGGER.info(message);
    }

    public abstract String getURL();

    public void open(String url) {
        String message = "Opening: " + url;
        LOGGER.info(message);
        driver.get(url);
    }

    public WebElement find(By location) {
        String message = "Finding the element by the following location: " + location.toString();
        LOGGER.info(message);
        return driver.findElement(location);
    }

    public void type(WebElement element, String input) {
        String message = "Writing: \"" + input + "\" in the element: " + element.toString();
        LOGGER.info(message);
        element.sendKeys(input);
    }

    public void type(By location, String input) {
        type(find(location), input);
    }

    public void click(WebElement element) {
        String message = "Clicking on the element: " + element.toString();
        LOGGER.info(message);
        element.click();
    }

    public void click(By location) {
        click(find(location));
    }

    public boolean isDisplayed(WebElement element) {
        String message = "Checking is the element displayed: " + element.toString();
        LOGGER.info(message);
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void setItemInLocalStorage(String value, String key) {
        ((JavascriptExecutor) DriverHelper.get().getDriver()).executeScript(String.format(
                "window.localStorage.setItem('%s','%s');", value, key));
    }

}