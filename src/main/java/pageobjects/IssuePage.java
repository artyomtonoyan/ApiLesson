package pageobjects;

import base.BasePage;
import com.google.gson.JsonObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import wait.WaitHelper;

public class IssuePage extends BasePage {
    @FindBy(css = ".detail-subject")
    private WebElement nameOfIssue;

    private final JsonObject myIssue;

    public IssuePage(JsonObject myIssue) {
        this.myIssue = myIssue;
        open(getURL());
        PageFactory.initElements(driver, this);
    }

    public String getNameOfIssue() {
        WaitHelper.getInstance().waitForElementToDisplayed(nameOfIssue);
        return nameOfIssue.getText();
    }

    @Override
    public String getURL() {
        return BASE_URL + "/project/" + myIssue.get("project_extra_info").getAsJsonObject().get("slug").getAsString() + "/issue/" + myIssue.get("ref").getAsString();
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        WaitHelper.getInstance().waitForElementToDisplayed(nameOfIssue);
    }
}
