package restapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import restapp.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
}