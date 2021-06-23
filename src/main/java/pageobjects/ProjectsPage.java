package pageobjects;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import wait.WaitHelper;

import java.util.ArrayList;
import java.util.List;

public class ProjectsPage extends BasePage {

    @FindBy(css = ".list-itemtype-data-title a")
    private List<WebElement> projectsTitles;

    @FindBy(className = "project-list-title")
    private WebElement projectListTitle;

    public ProjectsPage() {
        open(getURL());
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        WaitHelper.getInstance().waitForElementToDisplayed(projectListTitle);
    }

    public ArrayList<String> getProjectNamesList() {
        ArrayList<String> projectNamesList = new ArrayList<>();
        for (WebElement webElement : projectsTitles) {
            projectNamesList.add(webElement.getText());
        }
        return projectNamesList;
    }

    @Override
    public String getURL() {
        return BASE_URL + "/projects/";
    }

}