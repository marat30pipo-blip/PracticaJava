package task4.user.infrastructure.persistence;

import task4.user.domain.User;
import task4.user.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {


    private final String DB_URL;
    private final String DB_USER;
    private final String DB_PASSWORD;


    public UserRepositoryJdbcImpl(String DB_URL, String DB_USER, String DB_PASSWORD) {
        this.DB_URL = DB_URL;
        this.DB_USER = DB_USER;
        this.DB_PASSWORD = DB_PASSWORD;
    }



    @Override
    public void save(User user) {
        System.out.println("Сохранено");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> updateDate(String email, String password, String comment) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){

            // sellect * from account
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from useraccount")) {
                    while (resultSet.next()) {
                        String id = Integer.toString(resultSet.getInt("id")).split(" ")[0];
                        String name = resultSet.getString("name").split(" ")[0];
                        String email = resultSet.getString("email").split(" ")[0];
                        String password = resultSet.getString("password").split(" ")[0];
                        String profileDescription = resultSet.getString("profileDescription").split(" ")[0];
                        User user = new User(id, name, email, password, profileDescription);
                        users.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return users;
    }

}
