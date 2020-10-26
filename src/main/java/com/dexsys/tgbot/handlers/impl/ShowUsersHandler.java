package com.dexsys.tgbot.handlers.impl;

import com.dexsys.tgbot.BotState;
import com.dexsys.tgbot.handlers.BotMessageHandler;
import com.dexsys.tgbot.services.UsersDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class ShowUsersHandler implements BotMessageHandler {

    @Autowired
    private UsersDBService UsersDBService;

    @Override
    public BotState getHandlerState() {
        return BotState.SHOW_USERS;
    }

    @Override
    public SendMessage handle(Message message) {
        SendMessage replyMessage = new SendMessage();
        StringBuffer stringBuffer = new StringBuffer();
        UsersDBService.getUsers().
                forEach(x ->
                        stringBuffer.append(String.format("User: %s; ChatId: %s; Phone number: %s; Birthday: %s\n",
                                x.getUserName(), x.getChatId(), x.getPhoneNumber(),
                                x.getBirthDate() == null ? null : UsersDBService.getDateFormat().format(x.getBirthDate()))));
        replyMessage.setText(stringBuffer.toString());
        return replyMessage;
    }
}
