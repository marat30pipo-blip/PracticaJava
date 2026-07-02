package task1.ru.itis.shop.user.application;

import task1.ru.itis.shop.user.domain.User;
import task1.ru.itis.shop.user.repository.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String email, String password, String profileDescription) {
        User user = new User(email, password, profileDescription);
        userRepository.save(user);
    }



    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
}