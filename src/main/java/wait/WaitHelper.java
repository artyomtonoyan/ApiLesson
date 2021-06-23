package wait;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.DriverHelper;


public class WaitHelper {

    private static final Logger LOGGER = Logger.getLogger(WaitHelper.class);
    private final static int DEFAULT_TIMEOUT = 10;

    public static WaitHelper getInstance() {
        return new WaitHelper();
    }

    public void waitForElementToDisplayed(WebElement element) {
        String message = "Waiting for the element: " + element.toString();
        LOGGER.info(message);
        try {
            new WebDriverWait(DriverHelper.get().getDriver(), DEFAULT_TIMEOUT)
                    .until(ExpectedConditions.visibilityOf((element)));
        } catch (WebDriverException e) {
            throw new Error("Element was not found: " + element.toString());
        }
    }
    public void waitForElementsToDisplayed(By location) {
        String message = "Waiting for the element by location: " + location.toString();
        LOGGER.info(message);
        try {
            new WebDriverWait(DriverHelper.get().getDriver(), DEFAULT_TIMEOUT)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy((location)));
        } catch (WebDriverException e) {
            throw new Error("Element was not found by location: " + location.toString());
        }
    }
}