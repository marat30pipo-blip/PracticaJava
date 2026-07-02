package task2.ru.itis.shop.user.repository;

import task2.ru.itis.shop.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);

    Optional<User> updateDate(String email, String password, String comment);
}
