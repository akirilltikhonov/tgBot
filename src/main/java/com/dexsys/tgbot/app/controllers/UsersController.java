package com.dexsys.tgbot.app.controllers;


import com.dexsys.tgbot.adapters.IEntitiesToDTOService;
import com.dexsys.tgbot.adapters.IUsersRepositoryService;
import com.dexsys.tgbot.domain.dto.UserDTO;
import com.dexsys.tgbot.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private IEntitiesToDTOService entitiesToDTOService;

    @Autowired
    private IUsersRepositoryService usersRepositoryService;

    @GetMapping("/info")
    @ResponseStatus(code = HttpStatus.OK)
    public HttpEntity<List<UserDTO>> getUsersInfo() {
        List<User> users = usersRepositoryService.getUsers();
        List<UserDTO> usersDTO = entitiesToDTOService.UsersToUsersDTO(users);
        return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/info/{phoneNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public HttpEntity<UserDTO> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        User user = usersRepositoryService.getUserByPhoneNumber(phoneNumber);
        UserDTO userDTO = entitiesToDTOService.UserToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/info/{phoneNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteUserByPhoneNumber(@PathVariable String phoneNumber) {
        usersRepositoryService.deleteUserByPhoneNumber(phoneNumber);
    }
}
