//unable to get open API working, but was able to send out a message and get a 404 response from the network
package org.example.csc311cardgame;

import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;

public class OpenAIService {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "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"; // Replace with your OpenAI API Key

    public static String getAIHint(String cardValues) {
        OkHttpClient client = new OkHttpClient();

        JSONObject json = new JSONObject();
        json.put("model", "gpt-4");  // Use GPT-4 or GPT-3.5
        json.put("messages", new Object[]{
                new JSONObject().put("role", "system").put("content",
                        "You are a helpful assistant that provides hints for solving 24-game puzzles. " +
                                "Give a partial hint without revealing the full answer."),
                new JSONObject().put("role", "user").put("content",
                        "Give me a hint for solving the 24-game with these numbers: " + cardValues)
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
            if (!response.isSuccessful()) {
                System.out.println(cardValues);
                return "Error: API response code " + response.code() + " - " + response.message();

            }

            String responseBody = response.body().string();
            System.out.println("Raw API Response: " + responseBody); // Debugging

            JSONObject jsonResponse = new JSONObject(responseBody);

            // Check if "choices" exists before accessing it
            if (!jsonResponse.has("choices")) {
                return "Error: OpenAI response does not contain 'choices'. Full response: " + responseBody;
            }

            return jsonResponse.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}
