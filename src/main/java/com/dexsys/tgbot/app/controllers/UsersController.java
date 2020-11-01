package com.dexsys.tgbot.app.controllers;


import com.dexsys.tgbot.adapters.IEntitiesToDTOService;
import com.dexsys.tgbot.adapters.IUsersRepositoryService;
import com.dexsys.tgbot.domain.dto.UserDTO;
import com.dexsys.tgbot.domain.entities.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Users Controller")
@RequestMapping("users")
public class UsersController {

    @Autowired
    private IEntitiesToDTOService entitiesToDTOService;

    @Autowired
    private IUsersRepositoryService usersRepositoryService;

    @ApiOperation("Operation to get users")
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public HttpEntity<List<UserDTO>> getUsersInfo() {
        List<User> users = usersRepositoryService.getUsers();
        List<UserDTO> usersDTO = entitiesToDTOService.UsersToUsersDTO(users);
        return ResponseEntity.ok(usersDTO);
    }

    @ApiOperation("Operation to get user by phone number")
    @GetMapping("/{phoneNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public HttpEntity<UserDTO> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        User user = usersRepositoryService.getUserByPhoneNumber(phoneNumber);
        UserDTO userDTO = entitiesToDTOService.UserToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @ApiOperation("Operation to delete user by phone number")
    @DeleteMapping("/{phoneNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteUserByPhoneNumber(@PathVariable String phoneNumber) {
        usersRepositoryService.deleteUserByPhoneNumber(phoneNumber);
    }
}
