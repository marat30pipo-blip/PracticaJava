package task6.shop.user.api;

import task6.shop.user.api.dto.UserDto;
import task6.shop.user.application.UserService;

import java.util.List;
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
                findById();
            }
            break;
            case "4": {
                updateProfileDescription();
            }
            break;
            case "5": {
                findAll();
            }
            break;
            case "6": {
                findAllByProfileDescription();
            }
            break;
            case "0": {
                System.exit(0);
            }
            break;
            default: {
                System.out.println("Неизвестная команда. Попробуйте снова.");
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("МЕНЮ");
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить описание пользователя по почте");
        System.out.println("5. Получить информацию обо всех пользователях");
        System.out.println("6. Показать информацию о пользователях с заданным описанием профиля");
        System.out.println("0. Выход");
        System.out.print("Выберите пункт: ");
    }

    private void signUp() {
        System.out.println("РЕГИСТРАЦИЯ ПОЛЬЗОВАТЕЛЯ");
        System.out.print("Введите name: ");
        String name = scanner.nextLine();
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите password: ");
        String password = scanner.nextLine();
        System.out.print("Введите описание профиля: ");
        String profileDescription = scanner.nextLine();

        userService.signUp(name, email, password, profileDescription);
        System.out.println("Пользователь успешно зарегистрирован!");
    }

    private void signIn() {
        System.out.println("ВХОД В СИСТЕМУ");
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите password: ");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("Вы успешно вошли в приложение!");
        } else {
            System.out.println("Email или пароль не верны!");
        }
    }

    private void findById() {
        System.out.println("ПОИСК ПОЛЬЗОВАТЕЛЯ ПО ID");
        System.out.print("Введите id пользователя: ");
        try {
            Integer id = Integer.parseInt(scanner.nextLine());
            UserDto userDto = userService.findById(id);
            if (userDto != null) {
                System.out.println("Найден пользователь:");
                System.out.println("ID: " + userDto.getId());
                System.out.println("Email: " + userDto.getEmail());
                System.out.println("Profile Description: " + userDto.getProfileDescription());
            } else {
                System.out.println("Пользователь с id '" + id + "' не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректное число");
        }
    }

    private void updateProfileDescription() {
        System.out.println("ОБНОВЛЕНИЕ ОПИСАНИЯ ПРОФИЛЯ");
        System.out.print("Введите email пользователя: ");
        String email = scanner.nextLine();
        System.out.print("Введите новое описание профиля: ");
        String newProfileDescription = scanner.nextLine();

        if (userService.updateProfileDescription(email, newProfileDescription)) {
            System.out.println("Описание профиля успешно обновлено!");
        } else {
            System.out.println("Пользователь с email '" + email + "' не найден");
        }
    }

    private void findAll() {
        System.out.println("ВСЕ ПОЛЬЗОВАТЕЛИ");
        List<UserDto> users = userService.findAll();
        if (users.isEmpty()) {
            System.out.println("Пользователи не найдены");
        } else {
            System.out.println("Список всех пользователей:");
            for (UserDto userDto : users) {
                System.out.println("ID: " + userDto.getId());
                System.out.println("Email: " + userDto.getEmail());
                System.out.println("Profile Description: " + userDto.getProfileDescription());
            }
        }
    }

    private void findAllByProfileDescription() {
        System.out.println("ПОИСК ПОЛЬЗОВАТЕЛЕЙ ПО ОПИСАНИЮ ПРОФИЛЯ");
        System.out.print("Введите profileDescription: ");
        String profileDescription = scanner.nextLine();

        List<UserDto> foundUsers = userService.findAllByProfileDescription(profileDescription);

        if (foundUsers.isEmpty()) {
            System.out.println("Пользователи с profileDescription '" + profileDescription + "' не найдены");
        } else {
            System.out.println("Список пользователей с profileDescription '" + profileDescription + "':");
            for (UserDto userDto : foundUsers) {
                System.out.println("ID: " + userDto.getId());
                System.out.println("Email: " + userDto.getEmail());
                System.out.println("Profile Description: " + userDto.getProfileDescription());
            }
        }
    }
}