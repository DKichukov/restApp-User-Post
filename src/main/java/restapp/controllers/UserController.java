package restapp.controllers;

import org.springframework.web.bind.annotation.*;
import restapp.entities.User;
import restapp.services.UserService;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        userService.saveUser(user);
    }
}
