package com.dexsys.tgbot.domain.handlers;

import com.dexsys.tgbot.domain.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface BotMessageHandler {

    BotState getHandlerState();

    SendMessage handle(Message message);

}
