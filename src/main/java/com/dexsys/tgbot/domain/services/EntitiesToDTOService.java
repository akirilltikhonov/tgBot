package com.dexsys.tgbot.domain.services;

import com.dexsys.tgbot.adapters.IEntitiesToDTOService;
import com.dexsys.tgbot.domain.dto.UserDto;
import com.dexsys.tgbot.domain.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntitiesToDTOService implements IEntitiesToDTOService {
    public UserDto UserToUserDTO (User user) {
        UserDto userDTO = new UserDto();
        userDTO.setUserName(user.getUserName());
        userDTO.setChatId(user.getChatId());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setBirthdate(user.getBirthdate());
        return userDTO;
    }

    public List<UserDto> UsersToUsersDTO (List<User> users) {
        List<UserDto> usersDTO = new ArrayList<>();
        users.forEach(user -> usersDTO.add(UserToUserDTO(user)));
        return usersDTO;
    }
}
