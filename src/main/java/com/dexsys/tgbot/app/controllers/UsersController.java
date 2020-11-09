package com.dexsys.tgbot.app.controllers;


import com.dexsys.tgbot.adapters.IEntitiesToDTOService;
import com.dexsys.tgbot.adapters.IUsersRepositoryService;
import com.dexsys.tgbot.domain.dto.UserDto;
import com.dexsys.tgbot.domain.entities.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.List;

@RestController
@Api(value = "Users Controller")
@RequestMapping("users")
public class UsersController {
    @Autowired
    private IEntitiesToDTOService entitiesToDTOService;

    @Autowired
    private IUsersRepositoryService usersRepositoryService;

    //TODO throw NotFoundException in request from here

    @ApiOperation("Operation to get users")
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public HttpEntity<List<UserDto>> getUsersInfo() {
        List<User> users = usersRepositoryService.getUsers();
        List<UserDto> usersDTO = entitiesToDTOService.UsersToUsersDTO(users);
        return ResponseEntity.ok(usersDTO);
    }

    @ApiOperation("Operation to get user by phone number")
    @GetMapping("/{phoneNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public HttpEntity<UserDto> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        User user = usersRepositoryService.getUserByPhoneNumber(phoneNumber);
        UserDto userDTO = entitiesToDTOService.UserToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @ApiOperation("Operation to delete user by phone number")
    @DeleteMapping("/{phoneNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteUserByPhoneNumber(@PathVariable String phoneNumber) {
        usersRepositoryService.deleteUserByPhoneNumber(phoneNumber);
    }
}
