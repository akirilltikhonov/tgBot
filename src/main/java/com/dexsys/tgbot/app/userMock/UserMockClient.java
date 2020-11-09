package com.dexsys.tgbot.app.userMock;

import com.dexsys.tgbot.adapters.IUserClient;
import com.dexsys.tgbot.domain.dto.UserDtoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class UserMockClient implements IUserClient {

    @Autowired
    private RestTemplate restTemplate;

    //TODO move base url to property file
    String base = "https://serene-coast-56441.herokuapp.com//api/users";

    @Override
    public List<UserDtoDB> getUsers() {

        String url = base;
        ResponseEntity<UserDtoDB[]> response =
                restTemplate.getForEntity(
                        url,
                        UserDtoDB[].class);
        UserDtoDB[] usersDto = response.getBody();

        System.out.println("getUsers");
        System.out.println(response.getHeaders());
        System.out.println(response.getStatusCode());
        System.out.println(Arrays.asList(usersDto).toString());
        System.out.println("Number of users:" + response.getBody().length);

        //TODO throw exceptions if status code 404 or no users (or etc)

        return Arrays.asList(usersDto);
    }

    @Override
    public UserDtoDB getUser(String userId) {

        String url = base + "/" + userId;
        ResponseEntity<UserDtoDB> response =
                restTemplate.getForEntity(
                        url,
                        UserDtoDB.class);
        UserDtoDB userDtoDB = response.getBody();

        System.out.println("getUser");
        System.out.println(response.getStatusCode());
        System.out.println(userDtoDB);

        //TODO throw exceptions if status code 404 or no user(or etc)

        return userDtoDB;
    }

    public Set<HttpMethod> getUserOptions(String userId) {

        String url = base + "/" + userId;
        Set<HttpMethod> optionsForAllow = restTemplate.optionsForAllow(url);
        HttpHeaders header = restTemplate.headForHeaders(url);

        System.out.println("getUserOptions");
        System.out.println(header);
        System.out.println(optionsForAllow);

        //TODO throw exceptions if status code 404 or no user(or etc)

        return optionsForAllow;
    }

    @Override
    public void generateUser() {

        String url = base + "/generate";
        ResponseEntity<UserDtoDB> response =
                restTemplate.postForEntity(
                        url,
                        new UserDtoDB(),
                        UserDtoDB.class);
        UserDtoDB userDtoDB = response.getBody();

        System.out.println("generateUser");
        System.out.println(response.getStatusCode());
        System.out.println(userDtoDB);

        //TODO throw exceptions if status code 404(or etc)
    }

    @Override
    public void createUser(UserDtoDB userDtoDBPost) {

        String url = base;
        ResponseEntity<UserDtoDB> response =
                restTemplate.postForEntity(
                        url,
                        userDtoDBPost,
                        UserDtoDB.class);
        UserDtoDB userDtoDBResponse = response.getBody();

        System.out.println("createUser");
        System.out.println(response.getStatusCode());
        System.out.println("Post: " + userDtoDBPost);
        System.out.println("Response: " + userDtoDBResponse);

        //TODO throw exceptions if status code 404 (or etc)
    }

    //TODO PATCH request updateUser(UpdateDto updateDto, UUID userId), url ../api/users/{userId}

}