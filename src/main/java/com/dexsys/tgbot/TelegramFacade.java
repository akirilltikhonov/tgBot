package com.dexsys.tgbot;

import com.dexsys.tgbot.services.UsersDBService;
import com.dexsys.tgbot.services.MainMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class TelegramFacade {

    @Autowired
    private BotStateContext botStateContext;

    @Autowired
    private UsersDBService UsersDBService;

    @Autowired
    private MainMenuService mainMenuService;

    public synchronized SendMessage handleUpdate(Update update) {

        SendMessage replyMessage = null;
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            replyMessage = handleInputMessage(message);
            UsersDBService.addUser(message);
        }
        return replyMessage;
    }

    public synchronized SendMessage handleInputMessage(Message message) {

        String inputMsg = message.getText();
        BotState botState;
        SendMessage replyMessage;

        System.out.println(UsersDBService.getUsersMap());
        System.out.println(inputMsg);

        if (inputMsg.equals("Enter my date of birth")) {
            botState = BotState.ENTER_BIRTHDAY;
        } else if (mainMenuService.isAllowedEnterBirthday()) {
            botState = BotState.SET_USER_BIRTHDAY;
        } else if (inputMsg.equals("Enter my phone number")) {
            botState = BotState.ENTER_PHONE_NUMBER;
        } else if (mainMenuService.isAllowedEnterPhoneNumber()) {
            botState = BotState.SET_PHONE_NUMBER;
        } else if (inputMsg.equals("List of users")) {
            botState = BotState.SHOW_USERS;
        } else {
            botState = BotState.MAIN_MENU;
        }

        replyMessage = botStateContext.setState(botState, message);
        replyMessage.setChatId(message.getChatId());

        System.out.println(UsersDBService.getUsersMap());

        return replyMessage;
    }
}
