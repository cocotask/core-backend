package com.cocotask.core.user.rest;

import com.cocotask.core.user.domain.User;
import com.cocotask.core.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User addUser(@RequestBody User userInput) {
        return userService.addUser(userInput);
    }

    @GetMapping("/users")
    public List<User> readUsers() throws Exception {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userUid}")
    public User readUser(@PathVariable Long userUid) throws Exception {
        return userService.getUser(userUid);
    }

    @GetMapping("/users/email/{userEmail:.+}")
    public User readUserByUserEmail(@PathVariable String userEmail) throws Exception {
        return userService.getUserByUserEmail(userEmail);
    }

    @PatchMapping("/users/{userUid}")
    public User modifyUser(@PathVariable Long userUid, @RequestBody User userInput) {
        return userService.updateUser(userUid, userInput);
    }

    @DeleteMapping("/users/{userUid}")
    public void deleteUser(@PathVariable Long userUid) {
        userService.deleteUser(userUid);
    }
}
