
package com.example.demo;

import java.io.IOException;
import com.google.gson.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TranslatorText {
    private static String key = "bc66b2bee2154bfd806b7b3967d2a66c";

    // location, also known as region.
    // required if you're using a multi-service or regional (not global) resource. It can be found in the Azure portal on the Keys and Endpoint page.
    private static String location = "centralus";


    // Instantiates the OkHttpClient.
    OkHttpClient client = new OkHttpClient();

    // This function performs a POST request.
    public String Post(String inputText, String targetLanguage) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        String endpoint = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=en&to=" + targetLanguage;
        RequestBody body = RequestBody.create(mediaType, "[{\"Text\": \"" + inputText + "\"}]");
        Request request = new Request.Builder()
                .url(endpoint)
                .post(body)
                .addHeader("Ocp-Apim-Subscription-Key", key)
                .addHeader("Ocp-Apim-Subscription-Region", location)
                .addHeader("Content-type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    // This function prettifies the json response.
    public static String prettify(String json_text) {
        // Parse the JSON response
        JsonArray jsonArray = JsonParser.parseString(json_text).getAsJsonArray();

        // Access the first element of the array
        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

        // Access the 'translations' array
        JsonArray translationsArray = jsonObject.getAsJsonArray("translations");

        // Access the first element of the 'translations' array
        JsonObject translationObject = translationsArray.get(0).getAsJsonObject();

        // Extract the 'text' field
        String translatedText = translationObject.get("text").getAsString();
        return translatedText;
    }

}