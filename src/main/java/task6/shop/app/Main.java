package task6.shop.app;

import task6.shop.infrastructure.persistence.jdbs.DriverManagerDataSource;
import task6.shop.user.api.UserConsoleOperations;
import task6.shop.user.application.UserService;
import task6.shop.user.infrastructure.persistence.jdbc.UserRepositoryJdbcImpl;
import task6.shop.user.repository.UserRepository;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {

        DataSource dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/shop_db",
                "postgres", "IV2007@$");
        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        UserService userService = new UserService(userRepository);

        UserConsoleOperations operations = new UserConsoleOperations(userService);

        while (true) {
            operations.showMenu();
        }
    }
}