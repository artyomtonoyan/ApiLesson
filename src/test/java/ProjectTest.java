import api.ApiHelper;
import base.BaseTest;
import com.google.gson.JsonObject;
import json.JsonHelper;

import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.NotFoundPage;
import pageobjects.ProjectPage;

import static org.testng.Assert.*;

public class ProjectTest extends BaseTest {
    @Test
    public void projectCreation() {
        JsonObject projectResponse = ApiHelper.createProject(JsonHelper.createProjectJson("ProjectOne", "MyProjectOne", "false"));
        ProjectPage projectPage = (ProjectPage) new ProjectPage(projectResponse).get();
        assertEquals(projectPage.getProjectName().trim(), projectResponse.get("name").getAsString(), "Wrong project opened!");
    }

    @Test
    public void editProject() {
        JsonObject projectResponse = ApiHelper.createProject(JsonHelper.createProjectJson("ProjectForEditing", "ProjectToEdit", "false"));
        String editedName = "EditedProjectName";
        ApiHelper.editProject(projectResponse.get("id").getAsString(), JsonHelper.createProjectJson(editedName, "EditedProjectDescription", "false"));
        ProjectPage projectPage = new ProjectPage(projectResponse);
        assertEquals(projectPage.getProjectName().trim(), editedName, "Projects are not same");
    }

    @Test
    public void getProject() {
        JsonObject projectResponse = ApiHelper.createProject(JsonHelper.createProjectJson("ProjectForGetting", "ProjectToGet", "false"));
        JsonObject getObjectResponse = ApiHelper.getProject(projectResponse.get("id").getAsString());
        assertFalse(getObjectResponse.get("id").getAsString().isEmpty(), "Get project didn't work!");

    }

    @Test
    public void deleteProject() {
        JsonObject projectResponse = ApiHelper.createProject(JsonHelper.createProjectJson("ProjectForEditing", "ProjectToEdit", "false"));
        ApiHelper.deleteProject(projectResponse.get("id").getAsString());
        new ProjectPage(projectResponse);
        assertTrue(new NotFoundPage().isNotFoundPageOpened(), "The project is not deleted!");
    }

    @Test
    public void createColorTag() {
        JsonObject projectResponse = ApiHelper.createProject(JsonHelper.createProjectJson("ProjectForEditing", "ProjectToEdit", "false"));
        String tagColorResponseString = ApiHelper.createTagColor(projectResponse.get("id").getAsString(), "ColorTag", "#4287f5");
        assertTrue(tagColorResponseString.contains("200"));
    }

    @Test
    public void likeProject() {
        JsonObject projectResponse = ApiHelper.createProject(JsonHelper.createProjectJson("ProjectForEditing", "ProjectToEdit", "false"));
        String likeResponse = ApiHelper.likeProject(projectResponse.get("id").getAsString());
        assertTrue(likeResponse.contains("200"));
    }
    @Test
    public void watchProject() {
        JsonObject projectResponse = ApiHelper.createProject(JsonHelper.createProjectJson("ProjectForEditing", "ProjectToEdit", "false"));
        String watchResponse = ApiHelper.likeProject(projectResponse.get("id").getAsString());
        assertTrue(watchResponse.contains("200"));
    }
}