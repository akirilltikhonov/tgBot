package com.dexsys.tgbot.handlers.impl;

import com.dexsys.tgbot.BotState;
import com.dexsys.tgbot.handlers.BotMessageHandler;
import com.dexsys.tgbot.services.UsersRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class ShowUsersHandler implements BotMessageHandler {

    @Autowired
    private UsersRepositoryService UsersRepositoryService;

    @Override
    public BotState getHandlerState() {
        return BotState.SHOW_USERS;
    }

    @Override
    public SendMessage handle(Message message) {
        SendMessage replyMessage = new SendMessage();
        StringBuffer stringBuffer = new StringBuffer();
        UsersRepositoryService.getUsers().
                forEach(x ->
                        stringBuffer.append(String.format("User: %s; ChatId: %s; Phone number: %s; Birthday: %s\n",
                                x.getUserName(), x.getChatId(), x.getPhoneNumber(),
                                x.getBirthdate() == null ? null : UsersRepositoryService.getDateFormat().format(x.getBirthdate()))));
        replyMessage.setText(stringBuffer.toString());
        return replyMessage;
    }
}
