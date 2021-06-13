import api.ApiHelper;
import base.BaseTest;
import com.google.gson.JsonObject;
import json.JsonHelper;
import org.testng.annotations.Test;
import pageobjects.IssuesPage;
import java.util.ArrayList;

import static org.testng.Assert.assertTrue;

public class IssueTest extends BaseTest {

    @Test
    public void createIssue() {
        ArrayList<String> projectIds = ApiHelper.getCurrentProjectsIds();
        String projectId = projectIds.get(0);
        JsonObject projectResponse = ApiHelper.getProject(projectId);
        ApiHelper.createIssue(JsonHelper.createIssueJson(projectId, "issueOne"));
        IssuesPage issuesPage = new IssuesPage(projectResponse);
        assertTrue(issuesPage.getIssuesNamesList().contains("issueOne"), "Issue is not created!");
    }
}
