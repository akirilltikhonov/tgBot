package com.dexsys.tgbot;

import com.dexsys.tgbot.entities.User;
import com.dexsys.tgbot.repository.UserDB;
import com.dexsys.tgbot.services.MainKeyboardService;
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
    private UserDB userDB;

    @Autowired
    private BotStateContext botStateContext;

    @Autowired
    private MainKeyboardService mainKeyboardService;

    public synchronized SendMessage handleUpdate(Update update) {

        SendMessage replyMessage = null;
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            replyMessage = handleInputMessage(message);
        }
        return replyMessage;
    }

    public synchronized SendMessage handleInputMessage(Message message) {

        String inputMsg = message.getText();
        long chatId = message.getChatId();


        BotState botState;
        SendMessage replyMessage;

        String userName = getUserName(message);
        userDB.addUser(new User(userName, chatId));

        System.out.println(userDB.getUsers());
        System.out.println(inputMsg);

        if (inputMsg.equals("Enter my date of birth")) {
            botState = BotState.ENTER_BIRTHDAY;
        } else if (mainKeyboardService.isAllowedEnterBirthday()) {
            botState = BotState.SET_USER_BIRTHDAY;

        } else if (inputMsg.equals("List of users")) {
            botState = BotState.SHOW_USERS;
        } else {
            botState = BotState.MAIN_MENU;
        }

        replyMessage = botStateContext.setState(botState, message);
        replyMessage.setChatId(message.getChatId());

        System.out.println(userDB.getUsers());

        return replyMessage;
    }


    public synchronized String getUserName(Message msg) {
        String userName;
        if ((userName = msg.getChat().getUserName()) == null) {
            userName = msg.getChat().getFirstName() + " " + msg.getChat().getLastName();
        }
        return userName;
    }


}
