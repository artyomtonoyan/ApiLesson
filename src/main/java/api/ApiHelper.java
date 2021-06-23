package api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static json.JsonHelper.*;

public class ApiHelper {

    private static String accessToken = null;
    private static String userId = null;
    private static final ArrayList<String> projectsIds = new ArrayList<>();
    private static final String BASE_URL = "https://api.taiga.io/api/v1/";

    public static JsonObject login(String username, String password) {
        Response response = ApiClient.post(BASE_URL + "auth", createUserJson(username, password));
        String jsonString = null;
        try {
            jsonString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonObject responseBody = JsonParser.parseString(Objects.requireNonNull(jsonString)).getAsJsonObject();
        accessToken = responseBody.get("auth_token").getAsString();
        userId = responseBody.get("id").getAsString();
        return responseBody;
    }

    public static JsonObject createProject(JsonObject projectBody) {
        Response response = ApiClient.post(BASE_URL + "projects", projectBody, addHeaders());
        String jsonString = null;
        try {
            jsonString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParser.parseString(Objects.requireNonNull(jsonString)).getAsJsonObject();
    }

    public static JsonArray getAllProjects() {
        Response response = ApiClient.get(BASE_URL + "projects?member=" + userId, addHeaders());
        String jsonString = null;
        try {
            jsonString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParser.parseString(Objects.requireNonNull(jsonString)).getAsJsonArray();
    }


    /**
     * public static String deleteRandomProjectFromExistingProjectsList() {
     * setProjectIds();
     * if (projectsIds.size() == 0) {
     * throw new Error("Projects are empty, can't delete!");
     * }
     * Response response = ApiClient.delete(BASE_URL + "projects/" + projectsIds.get((int) (Math.random() * projectsIds.size())), addHeaders());
     * return response.code() + ", " + response.message();
     * }
     **/

    public static String deleteProject(String projectId) {
        Response response = ApiClient.delete(BASE_URL + "projects/" + projectId, addHeaders());
        return response.code() + ", " + response.message();
    }

    public static JsonObject editProject(String projectId, JsonObject editedProjectBody) {
        Response response = ApiClient.put(BASE_URL + "projects/" + projectId, editedProjectBody, addHeaders());
        String jsonString = null;
        try {
            jsonString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParser.parseString(Objects.requireNonNull(jsonString)).getAsJsonObject();
    }


    public static JsonObject getProject(String id) {
        Response response = ApiClient.get(BASE_URL + "projects/" + id, addHeaders());
        String jsonString = null;
        try {
            jsonString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParser.parseString(Objects.requireNonNull(jsonString)).getAsJsonObject();
    }


    public static String createTagColor(String projectId, String tagName, String tagHexColor) {
        Response response = ApiClient.post(BASE_URL + "projects/" + projectId + "/create_tag", createTagJson(tagHexColor, tagName), addHeaders());
        return response.code() + ", " + response.message();
    }

    public static JsonArray getAllIssues(String projectId) {
        Response response = ApiClient.get(BASE_URL + "issues?project=" + projectId, addHeaders());
        String jsonString = null;
        try {
            jsonString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParser.parseString(Objects.requireNonNull(jsonString)).getAsJsonArray();
    }

    public static String likeProject(String projectId) {
        Response response = ApiClient.post(BASE_URL +"projects/" + projectId + "/like", new JsonObject(), addHeaders());
        return response.code() + ", " + response.message();
    }

    public static String watchProject(String projectId) {
        Response response = ApiClient.post(BASE_URL +"projects/" + projectId + "/watch", new JsonObject(), addHeaders());
        return response.code() + ", " + response.message();
    }

    public static JsonObject createIssue(JsonObject issueBody) {
        Response response = ApiClient.post(BASE_URL + "issues", issueBody, addHeaders());
        String jsonString = null;
        try {
            jsonString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParser.parseString(Objects.requireNonNull(jsonString)).getAsJsonObject();
    }

    public static JsonObject editIssue(String issueId, JsonObject editedIssueBody) {
        Response response = ApiClient.patch(BASE_URL + "issues/" + issueId, editedIssueBody, addHeaders());
        String jsonString = null;
        try {
            jsonString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParser.parseString(Objects.requireNonNull(jsonString)).getAsJsonObject();
    }

    public static String deleteIssue(String issueId) {
        Response response = ApiClient.delete(BASE_URL + "issues/" + issueId, addHeaders());
        return response.code() + ", " + response.message();
    }

    private static void setProjectIds() {
        for (int i = 0; i < getAllProjects().size(); i++) {
            projectsIds.add(getAllProjects().get(i).getAsJsonObject().get("id").getAsString());
        }
    }

    private static Map<String, String> addHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + accessToken);
        return headers;
    }

    public static ArrayList<String> getCurrentProjectsIds() {
        setProjectIds();
        return projectsIds;
    }

    public static void main(String[] args) throws IOException {
        login("artyomtest", "artyomtest");
        System.out.println(likeProject("413072"));
    }
}