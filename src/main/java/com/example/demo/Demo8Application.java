package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class Demo8Application {

    public static void main(String[] args) throws TelegramApiException {
        SpringApplication.run(Demo8Application.class, args);
        System.out.println("Liron");
    }

}
