package pageobjects;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import wait.WaitHelper;

public class NotFoundPage extends BasePage {

    @FindBy(css = "[translate='ERROR.NOT_FOUND']")
    private WebElement notFoundItem;

    public NotFoundPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean isNotFoundPageOpened() {
        try {
            WaitHelper.getInstance().waitForElementToDisplayed(notFoundItem);
            return true;
        } catch (Error error) {
            return false;
        }
    }

    @Override
    public String getURL() {
        return null;
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        WaitHelper.getInstance().waitForElementToDisplayed(notFoundItem);
    }
}
