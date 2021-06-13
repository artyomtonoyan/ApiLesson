package json;

import com.google.gson.JsonObject;


public class JsonHelper {
    public static JsonObject createUserJson(String username, String password) {
        JsonObject user = new JsonObject();
        user.addProperty("password", password);
        user.addProperty("type", "normal");
        user.addProperty("username", username);
        return user;
    }

    public static JsonObject createProjectJson(String name, String description, String privacy) {
        JsonObject project = new JsonObject();
        project.addProperty("description", description);
        project.addProperty("name", name);
        project.addProperty("is_private", privacy);
        return project;
    }

    public static JsonObject createTagJson(String colorHEX, String tagName) {
        JsonObject tag = new JsonObject();
        tag.addProperty("color", colorHEX);
        tag.addProperty("tag", tagName);
        return tag;
    }

    public static JsonObject createIssueJson(String projectId, String subject) {
        JsonObject issue = new JsonObject();
        issue.addProperty("project", projectId);
        issue.addProperty("subject", subject);
        return issue;
    }
}
