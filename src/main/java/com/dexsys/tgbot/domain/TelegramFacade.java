package com.dexsys.tgbot.domain;

import com.dexsys.tgbot.adapters.ITelegramFacade;
import com.dexsys.tgbot.adapters.IUserClient;
import com.dexsys.tgbot.app.userMock.UserDto;
import com.dexsys.tgbot.domain.services.MainMenuService;
import com.dexsys.tgbot.domain.services.UsersRepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class TelegramFacade implements ITelegramFacade {

    @Autowired
    private BotStateContext botStateContext;

    @Autowired
    private UsersRepositoryService UsersRepositoryService;

    @Autowired
    private MainMenuService mainMenuService;

    // For test UserMockClient
    @Autowired
    private IUserClient iUserClient;

    public synchronized SendMessage handleUpdate(Update update) {

        SendMessage replyMessage = null;
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            replyMessage = handleInputMessage(message);
            UsersRepositoryService.addUser(message);
        }
        return replyMessage;
    }

    public synchronized SendMessage handleInputMessage(Message message) {

        String inputMsg = message.getText();
        BotState botState;
        SendMessage replyMessage;

        System.out.println(UsersRepositoryService.getUsersMap());
        System.out.println(inputMsg);
        // For test UserMockClient
        List<UserDto> usersDto = iUserClient.getUsers();
        System.out.println();

        String userId = "";
        UserDto userDto = iUserClient.getUser(userId);
        System.out.println();

        Set<HttpMethod> options = iUserClient.getUserOptions(userId);
        System.out.println();

        iUserClient.generateUser();
        System.out.println();

        iUserClient.createUser(new UserDto());
        System.out.println();

        iUserClient.getUsers();
        System.out.println();
        // End testing UserMockClient

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

        System.out.println(UsersRepositoryService.getUsersMap());

        return replyMessage;
    }
}
