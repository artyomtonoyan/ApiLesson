package pageobjects;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import wait.WaitHelper;

public class HomePage extends BasePage {
    @FindBy(css = "[title='Projects']")
    private WebElement projectsIcon;

    public ProjectsPage clickProjectsIcon() {
        WaitHelper.getInstance().waitForElementToDisplayed(projectsIcon);
        click(projectsIcon);
        return new ProjectsPage();
    }

    @Override
    public String getURL() {
        return BASE_URL;
    }
}