package com.dexsys.tgbot;

import com.dexsys.tgbot.handlers.MainMenuHandler;
import com.dexsys.tgbot.handlers.MessageHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotStateContext {

    private Map<BotState, MessageHandler> messageHandlers = new HashMap<>();

    public BotStateContext(List<MessageHandler> messageHandlers) {
        messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerState(), handler));
    }
    public SendMessage setState(BotState currentState, Message message) {
        MessageHandler currentHandler = findMessageHandler(currentState);
        return currentHandler.handle(message);
    }
    public MessageHandler findMessageHandler(BotState currentState) {
        return messageHandlers.get(currentState);
    }
}
