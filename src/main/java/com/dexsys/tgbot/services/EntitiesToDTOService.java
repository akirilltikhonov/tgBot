package com.dexsys.tgbot.services;

import com.dexsys.tgbot.dto.UserDTO;
import com.dexsys.tgbot.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntitiesToDTOService {
    public UserDTO UserToUserDTO (User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUserName());
        userDTO.setChatId(user.getChatId());
        userDTO.setBirthDate(user.getBirthDate());
        return userDTO;
    }

    public List<UserDTO> UsersToUsersDTO (List<User> users) {
        List<UserDTO> usersDTO = new ArrayList<>();
        users.forEach(user -> usersDTO.add(UserToUserDTO(user)));
        return usersDTO;
    }
}
