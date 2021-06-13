package pageobjects;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class ProjectsPage extends BasePage {

    @FindBy(css = ".list-itemtype-data-title a")
    private List<WebElement> projectsTitles;

    public ProjectsPage() {
        open(getURL());
        PageFactory.initElements(driver, this);
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