package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static com.example.demo.TranslatorText.prettify;

@SpringBootApplication
public class Demo8Application {

    public static void main(String[] args) throws TelegramApiException, IOException {
        SpringApplication.run(Demo8Application.class, args);
//        try {
//            String inputText = "hey";
//            String targetLanguage ="as";
//            TranslatorText translator = new TranslatorText();
//            String response = translator.Post(inputText, targetLanguage);
//            System.out.println(prettify(response));
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        ChuckNorrisJokeService norrisJokeService=new ChuckNorrisJokeService();
//        ArrayList<String> jokesList=norrisJokeService.scrapeJokes();
//        System.out.println(jokesList);
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

            MyBot bot = new MyBot();
            botsApi.registerBot(bot);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("Liron");
    }

}
