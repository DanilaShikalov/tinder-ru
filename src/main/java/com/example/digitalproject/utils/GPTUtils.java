package com.example.digitalproject.utils;

import com.example.digitalproject.models.dto.message.MessageGetDTO;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class GPTUtils {

    public static String sendMessageToGPT(String message, List<MessageGetDTO> messages) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            JSONArray array = new JSONArray();

            for (int i = 0; i < messages.size(); i++) {
                HashMap<String, String> map = new HashMap<>();
                map.put("role", i % 2 == 0 ? "user" : "assistant");

                if (messages.size() - 1 == i) {
                    map.put("content", messages.get(i).getMessage() + " . Ответь на это сообщение будто ты девушка. Отвечай очень коротко");
                } else {
                    map.put("content", messages.get(i).getMessage());
                }

                array.put(new JSONObject(map));
            }

            HttpPost httpPost = getHttpPost(message, array);

            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);

            System.out.println("Response Code: " + statusCode);
            System.out.println("Response Body: " + responseBody);

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static HttpPost getHttpPost(String message, JSONArray array) {
        String url = "https://dmik-chat.vercel.app/api/chat";
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("Content-Type", "application/json");

        String requestBody = String.format("{\"model\":{\"id\":\"gpt-3.5-turbo\",\"name\":\"Default (GPT-3.5)\"},\"messages\":%s,\"key\":\"\",\"prompt\":\"You are ChatGPT, a large language model trained by OpenAI. Follow the users instructions carefully. Respond using markdown.\"}", array.toString());

        StringEntity requestEntity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);

        httpPost.setEntity(requestEntity);
        return httpPost;
    }
}
