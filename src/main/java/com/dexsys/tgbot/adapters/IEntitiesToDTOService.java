package com.dexsys.tgbot.adapters;

import com.dexsys.tgbot.domain.entities.User;
import com.dexsys.tgbot.domain.dto.UserDto;

import java.util.List;

public interface IEntitiesToDTOService {

    UserDto UserToUserDTO (User user);

    List<UserDto> UsersToUsersDTO (List<User> users);

}
