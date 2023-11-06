package com.example.demo;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.demo.TranslatorText.prettify;

public class MyBot extends TelegramLongPollingBot {

    private boolean isValidIndex;
    private boolean isValidLanguage;
    private ArrayList<String> jokesList;
    private Map<String, String> languageMap;
    private String selectedJoke;
    public void init() throws IOException {
        selectedJoke="";
        this.isValidIndex = false;
        this.isValidLanguage=false;
        this.jokesList=new ArrayList<>();
        this.languageMap = new HashMap<>();
        createJokesList();
        createLanguageMap();
    }
    @Override
    public String getBotUsername() {
        return "branchSafe_bot";
    }

    @Override
    public String getBotToken() {
        return "6619443857:AAHFMfa3aR0Cqsj-2ggxqzYKKxLgQCENa6s";
    }
    public void createJokesList() throws IOException {
        ChuckNorrisJokeService norrisJokeService=new ChuckNorrisJokeService();
        this.jokesList=norrisJokeService.scrapeJokes();
    }


//    @Override
//    public void onUpdateReceived(Update update) {
//        // We check if the update has a message and the message has text
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
//            message.setChatId(update.getMessage().getChatId().toString());
//            message.setText(update.getMessage().getText());
//
//            try {
//                execute(message); // Call method to send the message
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//    }
public void onUpdateReceived(Update update) {
    long chatId;
    String userIndexInput="", userLanguageInput="", selectedLanguage="";
    if (update.hasMessage() && update.getMessage().hasText()) {
        Message message = update.getMessage();
        chatId = message.getChatId();

        // Check if the received message is the command to start input
        if (message.getText().equals("/startinput")) {
            sendTextMessage("Insert integer in the range of 1-101", chatId);
            isValidIndex = true;
        } else {
            // Process the user's input here only if expecting valid input
            if (isValidIndex) {
                userIndexInput = message.getText();

                // Validate if the input is a valid integer within the specified range
                try {
                    int userNumber = Integer.parseInt(userIndexInput);
                    if (userNumber >= 1 && userNumber <= 101) {
                        selectedJoke = jokesList.get(userNumber);
                        isValidIndex = false;
                        isValidLanguage = true;
                        sendTextMessage("Insert target language",chatId);
                        sendTextMessage(getKeysAsString(), chatId);
                    } else {
                        sendTextMessage("Invalid input. Please enter an integer in the range of 1-101.", chatId);
                    }
                } catch (NumberFormatException e) {
                    // Handle the case where user input is not a valid integer
                    sendTextMessage("Invalid input. Please enter an integer in the range of 1-101.", chatId);
                }
            } else if (isValidLanguage) {
                userLanguageInput = message.getText().trim(); // Trim and convert to lowercase for case-insensitive comparison
                if (isLanguageValid(userLanguageInput)) {
                    selectedLanguage = getLanguageAbbreviation(userLanguageInput);
                    isValidLanguage = false;
                    getTranslateInput(selectedJoke,selectedLanguage,chatId);
                } else {
                    sendTextMessage("Invalid input. Please select a valid language", chatId);
                }
            }
        }
    }
}

    private void sendTextMessage(String text,long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId)); // Convert long to String
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void createLanguageMap(){
        languageMap.put("Afrikaans", "af");
        languageMap.put("Arabic", "ar");
        languageMap.put("Assamese", "as");
        languageMap.put("Bangla", "bn");
        languageMap.put("Bashkir", "ba");
        languageMap.put("Bosnian (Latin)", "bs");
        languageMap.put("Bulgarian", "bg");
        languageMap.put("Catalan", "ca");
        languageMap.put("Chinese Simplified", "zh-Hans");
        languageMap.put("Chinese Traditional", "zh-Hant");

        languageMap.put("Croatian", "hr");
        languageMap.put("Czech", "cs");
        languageMap.put("Danish", "da");

        languageMap.put("Dutch", "nl");
        languageMap.put("English", "en");
        languageMap.put("Estonian", "et");
        languageMap.put("Fijian", "fj");
        languageMap.put("Filipino", "fil");
        languageMap.put("Finnish", "fi");
        languageMap.put("French", "fr");

        languageMap.put("German", "de");
        languageMap.put("Greek", "el");
        languageMap.put("Gujarati", "gu");
        languageMap.put("Hebrew", "he");
        languageMap.put("Hindi", "hi");
        languageMap.put("Hungarian", "hu");
        languageMap.put("Icelandic", "is");

        languageMap.put("Indonesian", "id");
        languageMap.put("Inuktitut", "iu");
        languageMap.put("Irish", "ga");
        languageMap.put("Italian", "it");
        languageMap.put("Japanese", "ja");
        languageMap.put("Kannada", "kn");

        languageMap.put("Korean", "ko");

        languageMap.put("Latvian", "lv");
        languageMap.put("Lithuanian", "lt");

        languageMap.put("Malagasy", "mg");
        languageMap.put("Malay (Latin)", "ms");
        languageMap.put("Malayalam", "ml");
        languageMap.put("Maltese", "mt");
        languageMap.put("Maori", "mi");
        languageMap.put("Marathi", "mr");

        languageMap.put("Norwegian", "nb");

        languageMap.put("Odia", "or");

        languageMap.put("Persian", "fa");
        languageMap.put("Polish", "pl");
        languageMap.put("Portuguese (Brazil)", "pt");
        languageMap.put("Punjabi", "pa");
        languageMap.put("Romanian", "ro");
        languageMap.put("Russian", "ru");
        languageMap.put("Samoan (Latin)", "sm");
        languageMap.put("Serbian (Latin)", "sr-Latn");

        languageMap.put("Slovak", "sk");
        languageMap.put("Slovenian", "sl");
        languageMap.put("Spanish", "es");
        languageMap.put("Swahili (Latin)", "sw");
        languageMap.put("Swedish", "sv");
        languageMap.put("Tahitian", "ty");
        languageMap.put("Tamil", "ta");
        languageMap.put("Tatar (Latin)", "tt");
        languageMap.put("Telugu", "te");
        languageMap.put("Thai", "th");
        languageMap.put("Tibetan", "bo");
        languageMap.put("Turkish", "tr");
        languageMap.put("Ukrainian", "uk");
        languageMap.put("Urdu", "ur");
        languageMap.put("Vietnamese", "vi");
        languageMap.put("Welsh", "cy");
    }
    public boolean isLanguageValid(String languageInput) {
        // Check if the languageInput exists in the languageMap values
        for (String key : languageMap.keySet()) {
            if (key.equals(languageInput)) {
                return true;
            }
        }
        return false;
    }
    public  String getLanguageAbbreviation(String languageName) {
        // Retrieve the value (abbreviation) for the given key (language name)
        return languageMap.get(languageName);
    }
    public String getKeysAsString() {
        StringBuilder keysStringBuilder = new StringBuilder();
        for (String key : languageMap.keySet()) {
            keysStringBuilder.append(key).append("\n");
        }
        return keysStringBuilder.toString();
    }
    public void getTranslateInput(String inputText,String targetLanguage,Long chatId) {
        try {
            TranslatorText translator = new TranslatorText();
            String response = translator.Post(inputText, targetLanguage);

            sendTextMessage(prettify(response),chatId);

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
