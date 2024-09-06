package com.sonata.Service;

import com.sonata.Model.User;
import com.sonata.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Set up initial data if needed
        userRepo.deleteAll(); // Ensure a clean state before each test
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("JohnDoe");
        user.setEmail("john@example.com");
        user.setPassword("password123");

        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals("JohnDoe", savedUser.getUsername());
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setUsername("JohnDoe");
        userRepo.save(user);

        User foundUser = userService.getUserById(user.getId());

        assertNotNull(foundUser);
        assertEquals("JohnDoe", foundUser.getUsername());
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setUsername("JohnDoe");

        User user2 = new User();
        user2.setUsername("JaneDoe");

        userService.saveUser(user1);
        userService.saveUser(user2);

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
    }

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("JohnDoe");
        userRepo.save(user);

        User foundUser = userService.getUserByUsername("JohnDoe");

        assertNotNull(foundUser);
        assertEquals("JohnDoe", foundUser.getUsername());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUsername("JohnDoe");
        userRepo.save(user);

        userService.deleteUser(user.getId());

        assertFalse(userRepo.findById(user.getId()).isPresent());
    }
}
