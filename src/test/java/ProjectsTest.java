import api.ApiHelper;
import base.BaseTest;
import org.testng.annotations.Test;
import pageobjects.ProjectsPage;

import java.util.ArrayList;

import static org.testng.Assert.assertTrue;

public class ProjectsTest extends BaseTest {
    @Test
    public void checkAllProjectsTest() {
        ArrayList<String> projectIds = ApiHelper.getCurrentProjectsIds();
        ArrayList<String> projectNames = new ArrayList<>();
        for (String projectId : projectIds) {
            projectNames.add(ApiHelper.getProject(projectId).get("name").getAsString());
        }
        ProjectsPage projectsPage = new ProjectsPage();
        ArrayList<String> projectNamesFromUI = projectsPage.getProjectNamesList();
        assertTrue(projectNames.containsAll(projectNamesFromUI) && projectNamesFromUI.containsAll(projectNames), "Wrong Projects!");
    }

    @Test
    public void deleteAllProjectsTest() {
        ArrayList<String> projectIds = ApiHelper.getCurrentProjectsIds();
        for (String projectId : projectIds) {
            ApiHelper.deleteProject(projectId);
        }
        ProjectsPage projectsPage = new ProjectsPage();
        ArrayList<String> projectNamesFromUI = projectsPage.getProjectNamesList();
        assertTrue(projectNamesFromUI.isEmpty(), "Projects aren't deleted fully!");

    }
}
