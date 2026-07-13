package task6.shop.user.application;

import task6.shop.user.api.dto.UserDto;
import task6.shop.user.domain.User;
import task6.shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String name, String email, String password, String profileDescription) {
        User user = new User(name, email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get().getPassword().equals(password);
        } else {
            return false;
        }
    }

    public UserDto findById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserDto(user.getId(), user.getEmail(), user.getProfileDescription());
        }
        return null;
    }

    public boolean updateProfileDescription(String email, String newProfileDescription) {
        return userRepository.updateProfileDescription(email, newProfileDescription);
    }

    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(new UserDto(user.getId(), user.getEmail(), user.getProfileDescription()));
        }
        return userDtos;
    }

    public List<UserDto> findAllByProfileDescription(String profileDescription) {
        List<User> users = userRepository.findAllByProfileDescription(profileDescription);
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(new UserDto(user.getId(), user.getEmail(), user.getProfileDescription()));
        }
        return userDtos;
    }
}