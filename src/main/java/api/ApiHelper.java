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

import static json.JsonHelper.createTagJson;
import static json.JsonHelper.createUserJson;

public class ApiHelper {

    private static String accessToken = null;
    private static String userId = null;
    private static final ArrayList<String> projectIds = new ArrayList<>();
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

    public static JsonObject createProject(JsonObject body) {
        Response response = ApiClient.post(BASE_URL + "projects", body, addHeaders());
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


    public static String deleteRandomProjectFromExistingProjectsList() {
        setProjectIds();
        if (projectIds.size() == 0) {
            throw new Error("Projects are empty, can't delete!");
        }
        Response response = ApiClient.delete(BASE_URL + "projects/" + projectIds.get((int) (Math.random() * projectIds.size())), addHeaders());
        return response.code() + ", " + response.message();
    }

    public static String deleteProject(String id) {
        Response response = ApiClient.delete(BASE_URL + "projects/" + id, addHeaders());
        return response.code() + ", " + response.message();
    }

    public static JsonObject editProject(String id, JsonObject body) {
        Response response = ApiClient.put(BASE_URL + "projects/" + id, body, addHeaders());
        String jsonString = null;
        try {
            jsonString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParser.parseString(Objects.requireNonNull(jsonString)).getAsJsonObject();
    }

    public static JsonObject editRandomProjectFromExistingProjectList(JsonObject body) {
        setProjectIds();
        if (projectIds.size() == 0) {
            throw new Error("Projects are empty, can't edit any!");
        }
        Response response = ApiClient.put(BASE_URL + "projects/" + projectIds.get((int) (Math.random() * projectIds.size())), body, addHeaders());
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

    public static String createTagColor(String id, String tagName, String tagHexColor) {
        Response response = ApiClient.post(BASE_URL + "projects/" + id + "/create_tag", createTagJson(tagHexColor, tagName), addHeaders());
        return response.code() + ", " + response.message();
    }

    public static JsonArray getAllIssues(String id) {
        Response response = ApiClient.get(BASE_URL + "issues?project=" + id, addHeaders());
        String jsonString = null;
        try {
            jsonString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParser.parseString(Objects.requireNonNull(jsonString)).getAsJsonArray();
    }

    public static JsonObject createIssue(JsonObject body) {
        Response response = ApiClient.post(BASE_URL + "issues", body, addHeaders());
        String jsonString = null;
        try {
            jsonString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParser.parseString(Objects.requireNonNull(jsonString)).getAsJsonObject();
    }

    private static void setProjectIds() {
        for (int i = 0; i < getAllProjects().size(); i++) {
            projectIds.add(getAllProjects().get(i).getAsJsonObject().get("id").getAsString());
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
        return projectIds;
    }
}