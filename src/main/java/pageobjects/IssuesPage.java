package pageobjects;

import base.BasePage;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import wait.WaitHelper;

import java.util.ArrayList;
import java.util.List;

public class IssuesPage extends BasePage {
    @FindBy(css = "[tg-nav*='project-issues-detail']")
    private List<WebElement> titlesOfIssues;

    private final JsonObject myProject;

    public IssuesPage(JsonObject project) {
        myProject = project;
        open(getURL());
        PageFactory.initElements(driver, this);
    }

    public ArrayList<String> getIssuesNamesList() {
        WaitHelper.getInstance().waitForElementsToDisplayed(By.cssSelector("[tg-nav*='project-issues-detail']"));
        ArrayList<String> issuesNamesList = new ArrayList<>();
        for (WebElement webElement : titlesOfIssues) {
            String[] tempArray = String.valueOf(webElement.getText()).split("\n");
            issuesNamesList.add(tempArray[1]);
        }
        return issuesNamesList;
    }

    @Override
    public String getURL() {
        return BASE_URL + "/project/" + myProject.get("slug").getAsString() + "/issues";
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        WaitHelper.getInstance().waitForElementsToDisplayed(By.className("issue-top"));
    }
}
