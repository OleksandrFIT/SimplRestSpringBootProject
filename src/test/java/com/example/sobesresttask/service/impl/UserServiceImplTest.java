package com.example.sobesresttask.service.impl;

import com.example.sobesresttask.model.User;
import com.example.sobesresttask.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(ThreadLocalRandom.current().nextLong(18, 100));
        user.setFirstName(RandomStringUtils.randomAlphabetic(10));
        user.setLastName(RandomStringUtils.randomAlphabetic(10));
        user.setAge(ThreadLocalRandom.current().nextInt(18, 100));

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);
        Assertions.assertNotNull(createdUser.getId());
        assertEquals(user.getFirstName(), createdUser.getFirstName());
        assertEquals(user.getLastName(), createdUser.getLastName());
        assertEquals(user.getAge(), createdUser.getAge());
    }

    @Test
    void getUserById() {
        User user = new User();
        user.setId(ThreadLocalRandom.current().nextLong(18, 100));
        user.setFirstName(RandomStringUtils.randomAlphabetic(10));
        user.setLastName(RandomStringUtils.randomAlphabetic(10));
        user.setAge(ThreadLocalRandom.current().nextInt(18, 100));

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.getUserById(1L);
        Assertions.assertTrue(retrievedUser.isPresent());
        assertEquals(user.getFirstName(), retrievedUser.get().getFirstName());
        assertEquals(user.getLastName(), retrievedUser.get().getLastName());
        assertEquals(user.getAge(), retrievedUser.get().getAge());
    }
}