package repository;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    void deleteByUsername(String username);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
