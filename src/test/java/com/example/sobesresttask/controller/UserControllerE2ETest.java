package com.example.sobesresttask.controller;

import com.example.sobesresttask.model.User;
import com.example.sobesresttask.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerE2ETest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAge(30);

        ResponseEntity<User> response = restTemplate.postForEntity("/user/createUser", user, User.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        User createdUser = response.getBody();
        assert createdUser != null;
        assertNotNull(createdUser.getId());
        assertEquals(user.getFirstName(), createdUser.getFirstName());
        assertEquals(user.getLastName(), createdUser.getLastName());
        assertEquals(user.getAge(), createdUser.getAge());

        Optional<User> foundUser = userRepository.findById(createdUser.getId());
        assertTrue(foundUser.isPresent());
        assertEquals(createdUser, foundUser.get());
    }

    @Test
    public void testFindUserById() {
        User user = new User();

        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setAge(25);
        userRepository.save(user);

        ResponseEntity<Optional<User>> response = restTemplate.exchange(
                "/user/findUserById/" + user.getId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        Optional<User> foundUser = Optional.of(Objects.requireNonNull(response.getBody()).get());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }
}
