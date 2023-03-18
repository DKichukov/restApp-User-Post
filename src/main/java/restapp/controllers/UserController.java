package restapp.controllers;

import jakarta.validation.Valid;
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

    @PostMapping("/users/new")
    public void createUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
       userService.deleteUserById(id);
    }
}
