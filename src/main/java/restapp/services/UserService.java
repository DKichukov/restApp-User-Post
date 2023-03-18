    package restapp.services;

    import org.springframework.stereotype.Service;
    import restapp.entities.User;
    import restapp.exceptions.UserNotFoundException;
    import restapp.repositories.UserRepository;

    import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserById(int id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Id: " + id);
        }
        return user;
    }

    public void deleteUserById(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) userRepository.deleteById(id);
        else {
            throw new UserNotFoundException("Id: " + id);
        }
    }

}
