package api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;

public class ApiClient {
    private static final Logger LOGGER = Logger.getLogger(ApiClient.class);

    public static Response post(String url, JsonObject body, Map<String, String> headers) {
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, body.toString());
        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        Headers header = builder.build();
        Request request = new Request.Builder().url(url).post(requestBody).headers(header).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            LOGGER.error("POST METHOD cannot be executed due to cancellation, a connectivity problem or timeout!");
            e.printStackTrace();
        }
        if (response == null) {
            throw new Error("Response is Null");
        }
        if (!response.isSuccessful()) {
            try {
                throw new Error("HTTP Error Code: " + response.code() + ", Content: " + JsonParser.parseString((response.body().string())).getAsJsonObject());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public static Response post(String url, JsonObject body) {
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, body.toString());
        Request request = new Request.Builder().url(url).post(requestBody).addHeader("Content-Type", "application/json").build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            LOGGER.error("POST METHOD not be executed due to cancellation, a connectivity problem or timeout!");
            e.printStackTrace();
        }
        if (response == null) {
            throw new Error("Response is null");
        }
        if (!response.isSuccessful()) {
            try {
                throw new Error("HTTP Error Code: " + response.code() + ", Content: " + JsonParser.parseString((response.body().string())).getAsJsonObject());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public static Response get(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url).get()
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            LOGGER.error("GET METHOD cannot be executed due to cancellation, a connectivity problem or timeout!");
            e.printStackTrace();
        }
        if (response == null) {
            throw new Error("Response is Null");
        }
        if (!response.isSuccessful()) {
            try {
                throw new Error("HTTP Error Code: " + response.code() + ", Content: " + JsonParser.parseString((response.body().string())).getAsJsonObject());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public static Response get(String url, Map<String, String> headers) {
        OkHttpClient client = new OkHttpClient();
        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        Headers header = builder.build();
        Request request = new Request.Builder()
                .url(url).get()
                .headers(header)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            LOGGER.error("GET METHOD cannot be executed due to cancellation, a connectivity problem or timeout!");
            e.printStackTrace();
        }
        if (response == null) {
            throw new Error("Response: NULL");
        }
        if (!response.isSuccessful()) {
            try {
                throw new Error("HTTP Error Code: " + response.code() + ", Content: " + JsonParser.parseString((response.body().string())).getAsJsonObject());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public static Response delete(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url).delete()
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            LOGGER.error("DELETE METHOD cannot be executed due to cancellation, a connectivity problem or timeout!");
            e.printStackTrace();
        }
        if (response == null) {
            throw new Error("Response: NULL");
        }
        if (!response.isSuccessful()) {
            try {
                throw new Error("HTTP Error Code: " + response.code() + ", Content: " + JsonParser.parseString((response.body().string())).getAsJsonObject());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public static Response delete(String url, Map<String, String> headers) {
        OkHttpClient client = new OkHttpClient();
        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        Headers header = builder.build();
        Request request = new Request.Builder()
                .url(url).delete()
                .headers(header)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            LOGGER.error("DELETE METHOD cannot be executed due to cancellation, a connectivity problem or timeout!");
            e.printStackTrace();
        }
        if (response == null) {
            throw new Error("Response: NULL");
        }
        if (!response.isSuccessful()) {
            try {
                throw new Error("HTTP Error Code: " + response.code() + ", Content: " + JsonParser.parseString((response.body().string())).getAsJsonObject());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public static Response put(String url, JsonObject body, Map<String, String> headers) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, body.toString());
        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        Headers header = builder.build();
        Request request = new Request.Builder()
                .url(url).put(requestBody)
                .headers(header)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            LOGGER.error("PUT METHOD cannot be executed due to cancellation, a connectivity problem or timeout!");
            e.printStackTrace();
        }
        if (response == null) {
            throw new Error("Response is Null");
        }
        if (!response.isSuccessful()) {
            try {
                throw new Error("HTTP Error Code: " + response.code() + ", Content: " + JsonParser.parseString((response.body().string())).getAsJsonObject());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public static Response put(String url, JsonObject body) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, body.toString());
        Request request = new Request.Builder()
                .url(url).put(requestBody)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            LOGGER.error("PUT METHOD cannot be executed due to cancellation, a connectivity problem or timeout!");
            e.printStackTrace();
        }
        if (response == null) {
            throw new Error("Response is Null");
        }
        if (!response.isSuccessful()) {
            try {
                throw new Error("HTTP Error Code: " + response.code() + ", Content: " + JsonParser.parseString((response.body().string())).getAsJsonObject());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }


    public static Response patch(String url, JsonObject body, Map<String, String> headers) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, body.toString());
        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        Headers header = builder.build();
        Request request = new Request.Builder()
                .url(url).patch(requestBody)
                .headers(header)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            LOGGER.error("PUT METHOD cannot be executed due to cancellation, a connectivity problem or timeout!");
            e.printStackTrace();
        }
        if (response == null) {
            throw new Error("Response is Null");
        }
        if (!response.isSuccessful()) {
            try {
                throw new Error("HTTP Error Code: " + response.code() + ", Content: " + JsonParser.parseString((response.body().string())).getAsJsonObject());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
}