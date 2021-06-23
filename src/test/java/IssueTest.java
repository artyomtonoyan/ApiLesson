import api.ApiHelper;
import base.BaseTest;
import com.google.gson.JsonObject;
import json.JsonHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.IssuePage;
import pageobjects.IssuesPage;
import pageobjects.NotFoundPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class IssueTest extends BaseTest {
    private String createdProjectId;
    private JsonObject createdProjectResponse;

    @BeforeMethod
    public void createProject() {
        JsonObject projectResponse = ApiHelper.createProject(JsonHelper.createProjectJson("ProjectForIssueCreationAndDeletionTest", "MyProjectOne", "false"));
        createdProjectResponse = projectResponse;
        createdProjectId = projectResponse.get("id").getAsString();
    }

    @Test
    public void createIssue() {
        ApiHelper.createIssue(JsonHelper.createIssueJson(createdProjectId, "issueOne"));
        IssuesPage issuesPage = (IssuesPage) new IssuesPage(createdProjectResponse).get();
        assertTrue(issuesPage.getIssuesNamesList().contains("issueOne"), "The issue is not created!");
    }

    @Test
    public void editIssue() {
        JsonObject createdIssueResponse = ApiHelper.createIssue(JsonHelper.createIssueJson(createdProjectId, "IssueForEditing"));
        JsonObject editedIssueResponse = ApiHelper.editIssue(createdIssueResponse.get("id").getAsString(), JsonHelper.createEditedIssueJson("EditedIssue", 1));
        IssuePage issuePage = new IssuePage(editedIssueResponse);
        assertEquals(issuePage.getNameOfIssue(), "EditedIssue", "The issue not properly edited!");
    }


    @Test
    public void deleteIssue() {
        JsonObject createdIssueResponse = ApiHelper.createIssue(JsonHelper.createIssueJson(createdProjectId, "IssueForEditing"));
        ApiHelper.deleteIssue(createdIssueResponse.get("id").getAsString());
        new IssuePage(createdIssueResponse);
        assertTrue(new NotFoundPage().isNotFoundPageOpened(), "The issue isn't deleted!");
    }

}