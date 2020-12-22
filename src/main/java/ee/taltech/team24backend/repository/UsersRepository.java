package ee.taltech.team24backend.repository;

import ee.taltech.team24backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAllByUsername(String username);
}
