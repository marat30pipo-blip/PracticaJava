package task1.ru.itis.shop.user.api;

import task1.ru.itis.shop.user.application.UserService;
import task1.ru.itis.shop.user.domain.User;

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

            case "3": {
                findById();
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
        System.out.println("Пользователь успешно зарегистрирован!");
    }



    private void findById() {
        System.out.println("Введите ID пользователя:");
        String id = scanner.nextLine();
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isPresent()) {
            System.out.println("Email пользователя: " + userOptional.get().getEmail());
        } else {
            System.out.println("Пользователь с ID " + id + " не найден");
        }
        System.out.println("Обработка завершена!");
    }
}