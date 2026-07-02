package task2.ru.itis.shop.user.application;

import task2.ru.itis.shop.user.domain.User;
import task2.ru.itis.shop.user.repository.UserRepository;

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

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get().getPassword().equals(password);
        } else return false;
    }

    public Optional<User> updateDate(String email, String password, String comment) {
        return userRepository.updateDate(email, password, comment);
    }
}