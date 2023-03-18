package restapp.services;

import org.springframework.stereotype.Service;
import restapp.entities.User;
import restapp.exceptions.UserNotFoundException;
import restapp.repositories.UserRepository;

import java.util.List;
import java.util.function.Predicate;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(int id) throws UserNotFoundException {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        User user = findAll().stream().filter(predicate).findFirst().orElse(null);
        if (user == null) {
            throw new UserNotFoundException("Id: " + id);
        }
        return user;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserById(Integer id) throws UserNotFoundException {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        User user = findAll().stream().filter(predicate).findFirst().orElse(null);
        if (user!=null) userRepository.deleteById(id);
        else {
            throw new UserNotFoundException("Id: " + id);
        }
    }

}
