import api.ApiHelper;
import base.BaseTest;
import com.google.gson.JsonObject;
import json.JsonHelper;
import org.testng.annotations.Test;
import pageobjects.ProjectPage;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class ProjectTest extends BaseTest {
    @Test
    public void projectCreationTest() {
        JsonObject projectResponse = ApiHelper.createProject(JsonHelper.createProjectJson("ProjectOne", "MyProjectOne", "false"));
        ProjectPage projectPage = new ProjectPage(projectResponse);
        assertEquals(projectPage.getProjectName(), projectResponse.get("name").getAsString(), "Wrong project opened!");
    }

    @Test
    public void editProjectTest() {
        ArrayList<String> projectIds = ApiHelper.getCurrentProjectsIds();
        JsonObject projectResponse = ApiHelper.getProject(projectIds.get(0));
        String editedName = "EditedProject";
        ApiHelper.editProject(projectIds.get(0), JsonHelper.createProjectJson(editedName, "EditedProjectDesc", "false"));
        ProjectPage projectPage = new ProjectPage(projectResponse);
        assertEquals(projectPage.getProjectName(), editedName, "Projects are not same");
    }

}