package com.dexsys.tgbot.app.userMock;

import com.dexsys.tgbot.adapters.IUserClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class UserMockClient implements IUserClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<UserDto> getUsers() {
        String url = "https://serene-coast-56441.herokuapp.com//api/users";
        ResponseEntity<UserDto[]> response =
                restTemplate.getForEntity(
                        url,
                        UserDto[].class);
        UserDto[] usersDto = response.getBody();

        System.out.println("getUsers");
        System.out.println(response.getHeaders());
        System.out.println(response.getStatusCode());
        System.out.println(Arrays.asList(usersDto).toString());
        System.out.println("Number of users:" + response.getBody().length);

        return Arrays.asList(usersDto);
    }

    @Override
    public UserDto getUser(String userId) {
        String url = "https://serene-coast-56441.herokuapp.com//api/users" + "/" + userId;

        ResponseEntity<UserDto> response =
                restTemplate.getForEntity(
                        url,
                        UserDto.class);
        UserDto userDto = response.getBody();

        System.out.println("getUser");
        System.out.println(response.getStatusCode());
        System.out.println(userDto);

        return userDto;
    }

    public Set<HttpMethod> getUserOptions(String userId) {
        String url = "https://serene-coast-56441.herokuapp.com//api/users" + "/" + userId;
        Set<HttpMethod> optionsForAllow = restTemplate.optionsForAllow(url);
        HttpHeaders header = restTemplate.headForHeaders(url);

        System.out.println("getUserOptions");
        System.out.println(header);
        System.out.println(optionsForAllow);

        return optionsForAllow;
    }

    @Override
    public void generateUser() {
        String url = "https://serene-coast-56441.herokuapp.com//api/users/generate";

        ResponseEntity<UserDto> response =
                restTemplate.postForEntity(
                        url,
                        new UserDto(),
                        UserDto.class);
        UserDto userDto = response.getBody();

        System.out.println("generateUser");
        System.out.println(response.getStatusCode());
        System.out.println(userDto);
    }


//    @Override
//    public void createUser(UserDto userDtoPost) {
//        String url = "https://serene-coast-56441.herokuapp.com//api/users";
//
//        ResponseEntity<UserDto> response =
//                restTemplate.postForEntity(
//                        url,
//                        userDtoPost,
//                        UserDto.class);
//        UserDto userDtoResponse = response.getBody();
//
//        System.out.println("createUser");
//        System.out.println(response.getStatusCode());
//        System.out.println(userDtoPost);
//        System.out.println(userDtoResponse);
//    }
}