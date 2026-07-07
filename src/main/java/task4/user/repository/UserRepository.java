package task4.user.repository;

import task4.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);

    Optional<User> updateDate(String email, String password, String comment);

    List<User> findAll();
}
