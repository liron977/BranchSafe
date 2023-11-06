package com.example.demo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


@Service
public class ChuckNorrisJokeService {

    private static final String JOKE_URL = "https://parade.com/968666/parade/chuck-norris-jokes/"; // Replace this with the actual URL

    public ArrayList<String> scrapeJokes() throws IOException {
        ArrayList<String> jokesList = new ArrayList<>();
        String targetUrl = "https://parade.com/968666/parade/chuck-norris-jokes/";

        try {
            Document document = Jsoup.connect("https://parade.com/968666/parade/chuck-norris-jokes/")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .header("Referer", "https://parade.com")
                    .header("Accept-Language", "en-US,en;q=0.9")
                    .get();

            Elements jokeElements = document.select("ol li"); // Select all <li> elements inside <ol>

            for (Element element : jokeElements) {
                String joke = element.text(); // Extract text from <li> element
                jokesList.add(joke);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        return jokesList;
    }
}
