package com.dexsys.tgbot.controllers;


import com.dexsys.tgbot.dto.UserDTO;
import com.dexsys.tgbot.entities.User;
import com.dexsys.tgbot.exception.NotFoundException;
import com.dexsys.tgbot.services.EntitiesToDTOService;
import com.dexsys.tgbot.services.UsersDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private EntitiesToDTOService entitiesToDTOService;

    @Autowired
    private UsersDBService usersDBService;

    @GetMapping("/info")
    @ResponseStatus(code = HttpStatus.OK)
    public HttpEntity<List<UserDTO>> getUsersInfo() {
        List<User> users = usersDBService.getUsers();
        List<UserDTO> usersDTO = entitiesToDTOService.UsersToUsersDTO(users);
        return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/info/{phoneNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public HttpEntity<UserDTO> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        User user = usersDBService.getUserByPhoneNumber(phoneNumber);
        UserDTO userDTO = entitiesToDTOService.UserToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/info/{phoneNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteUserByPhoneNumber(@PathVariable String phoneNumber) {
        usersDBService.deleteUserByPhoneNumber(phoneNumber);
    }
}
