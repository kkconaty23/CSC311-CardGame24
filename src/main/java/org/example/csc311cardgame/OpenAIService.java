package org.example.csc311cardgame;

import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;

public class OpenAIService {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = ""; // Replace with your OpenAI API Key

    public static String getAIResponse(String userMessage) {
        OkHttpClient client = new OkHttpClient();

        JSONObject json = new JSONObject();
        json.put("model", "gpt-4");  // Change to "gpt-3.5-turbo" if using GPT-3.5
        json.put("messages", new Object[] {
                new JSONObject().put("role", "system").put("content", "You are a helpful assistant."),
                new JSONObject().put("role", "user").put("content", userMessage)
        });

        RequestBody body = RequestBody.create(
                json.toString(),
                MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return new JSONObject(response.body().string())
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getString("message");
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}

