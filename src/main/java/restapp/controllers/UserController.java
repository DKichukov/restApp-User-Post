package restapp.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import restapp.entities.Post;
import restapp.entities.User;
import restapp.repositories.PostRepository;
import restapp.repositories.UserRepository;
import restapp.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserController(UserService userService, UserRepository userRepository,
                          PostRepository postRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users/new")
    public void createUser(@Valid @RequestBody User user) {
        userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUser(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> getPostsForUser(@PathVariable Integer id) {
        Optional<User> user = userService.findUserById(id);
        return user.get().getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public List<Post> createPostsForUser(@PathVariable Integer id, @Valid @RequestBody Post post) {
        Optional<User> user = userService.findUserById(id);
        post.setUser(user.get());
        postRepository.save(post);
        return user.get().getPosts();
    }
}
