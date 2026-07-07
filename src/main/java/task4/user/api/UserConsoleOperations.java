package task4.user.api;

import task4.user.application.UserService;
import task4.user.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner;

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        printUserMenu();

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                signUp();
            }
            break;
            case "2": {
                signIn();
            }
            break;
            case "3": {
                // найти пользователя по id
            }
            break;
            case "4": {
                updateData();
            }
            break;
            case "5": {
                findAll();
            }
            break;
            case "0": {
                System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить данные пользователя");
        System.out.println("5. Показать всех пользователей");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(email, password, profileDescription);
    }

    private void signIn() {
        System.out.println("Вы можете войти в приложение");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("Вы вошли в приложение");
        } else {
            System.out.println("Email или пароль не верны");
        }
    }

    private void updateData() {
        System.out.println("Введите email пользователя, данные которого хотите обновить: ");
        String email = scanner.nextLine();

        System.out.println("Введите новый пароль: ");
        String password = scanner.nextLine();

        System.out.println("Введите новое описание профиля: ");
        String comment = scanner.nextLine();

        Optional<User> updatedUser = userService.updateDate(email, password, comment);

        if (updatedUser.isPresent()) {
            System.out.println("Данные успешно обновлены!");
        } else {
            System.out.println("Пользователь с email " + email + " не найден!");
        }
    }

    private void findAll() {
        System.out.println("Список всех пользователей");
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            System.out.println("Пользователи не найдены");
        } else {
            for (User user : users) {
                System.out.println("ID: " + user.getId() +
                        ", Имя: " + user.getName() +
                        ", Email: " + user.getEmail());
            }
        }
        System.out.println();
    }
}