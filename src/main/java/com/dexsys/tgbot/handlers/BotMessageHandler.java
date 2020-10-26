package com.dexsys.tgbot.handlers;

import com.dexsys.tgbot.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface BotMessageHandler {

    BotState getHandlerState();

    SendMessage handle(Message message);

}
