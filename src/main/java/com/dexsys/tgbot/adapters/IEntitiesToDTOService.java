package com.dexsys.tgbot.adapters;

import com.dexsys.tgbot.domain.entities.User;
import com.dexsys.tgbot.domain.dto.UserDTO;

import java.util.List;

public interface IEntitiesToDTOService {

    UserDTO UserToUserDTO (User user);

    List<UserDTO> UsersToUsersDTO (List<User> users);

}
