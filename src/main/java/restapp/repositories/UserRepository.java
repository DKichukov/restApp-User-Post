package restapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import restapp.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
