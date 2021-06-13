package pageobjects;

import base.BasePage;
import com.google.gson.JsonObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
}
