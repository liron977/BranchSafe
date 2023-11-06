package com.example.demo;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {

    private boolean expectingValidInput = false;
    @Override
    public String getBotUsername() {
        return "branchSafe_bot";
    }

    @Override
    public String getBotToken() {
        return "6619443857:AAHFMfa3aR0Cqsj-2ggxqzYKKxLgQCENa6s";
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
    if (update.hasMessage() && update.getMessage().hasText()) {
        Message message = update.getMessage();
        long chatId = message.getChatId();

        // Check if the received message is the command to start input
        if (message.getText().equals("/startinput")) {
            expectingValidInput = true;
            sendTextMessage("Insert integer in the range of 1-101",chatId);
        } else {
            // Process the user's input here only if expecting valid input
            if (expectingValidInput) {
                String userInput = message.getText();

                // Validate if the input is a valid integer within the specified range
                try {
                    int userNumber = Integer.parseInt(userInput);
                    if (userNumber >= 1 && userNumber <= 101) {
                        sendTextMessage("You entered a valid number: " + userNumber,chatId);
                        expectingValidInput = false;
                    } else {
                        sendTextMessage("Invalid input. Please enter an integer in the range of 1-101.",chatId);
                    }
                } catch (NumberFormatException e) {
                    // Handle the case where user input is not a valid integer
                    sendTextMessage("Invalid input. Please enter an integer in the range of 1-101.",chatId);
                }

                // Reset the state after processing the input

            } else {
                expectingValidInput = false;
                // Process the user's input here
                String userInput = message.getText();
                // Handle the user's input as needed
                sendTextMessage( "You entered: " + userInput,chatId);
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

}
