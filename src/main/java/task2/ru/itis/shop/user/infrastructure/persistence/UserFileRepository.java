package task2.ru.itis.shop.user.infrastructure.persistence;

import task2.ru.itis.shop.user.domain.User;
import task2.ru.itis.shop.user.repository.UserRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserFileRepository implements UserRepository {

    private final String fileName;
    private final UserMapper userMapper;

    public UserFileRepository(String fileName, UserMapper userMapper) {
        this.fileName = fileName;
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String id = UUID.randomUUID().toString();
            user.setId(id);
            writer.write(userMapper.toLine(user));
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line = reader.readLine();

            while (line != null) {

                User user = userMapper.fromLine(line);

                if (user.getEmail().equals(email)) {
                    return Optional.of(user);
                }

                line = reader.readLine();
            }

            return Optional.empty();

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> updateDate(String email, String password, String comment) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            Optional<User> updatedUser = Optional.empty();

            for (int i = 0; i < lines.size(); i++) {
                User user = userMapper.fromLine(lines.get(i));
                if (user.getEmail().equals(email)) {
                    User newUser = new User(user.getId(), email, password, comment);
                    lines.set(i, userMapper.toLine(newUser));
                    updatedUser = Optional.of(newUser);
                    break;
                }
            }

            if (updatedUser.isPresent()) {
                Files.write(Paths.get(fileName), lines);
            }

            return updatedUser;

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}