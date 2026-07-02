package task2.ru.itis.shop.app;

import task2.ru.itis.shop.user.api.UserConsoleOperations;
import task2.ru.itis.shop.user.application.UserService;
import task2.ru.itis.shop.user.infrastructure.persistence.UserFileRepository;
import task2.ru.itis.shop.user.infrastructure.persistence.UserMapper;

public class Main {
    public static void main(String[] args) {
        UserFileRepository userFileRepository = new UserFileRepository("user.txt", new UserMapper());
        UserConsoleOperations operations = new UserConsoleOperations(new UserService(userFileRepository));

        while (true) {
            operations.showMenu();
        }
    }
}
