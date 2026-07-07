package task4.app;

import task4.user.api.UserConsoleOperations;
import task4.user.application.UserService;
import task4.user.infrastructure.persistence.UserRepositoryJdbcImpl;

public class Main {
    public static void main(String[] args) {

        final String DB_URL = "jdbc:postgresql://localhost:5432/java_2026";
        final String DB_USER = "postgres";
        final String DB_PASSWORD = "IV2007@$";

        UserRepositoryJdbcImpl userFileRepository = new UserRepositoryJdbcImpl(DB_URL, DB_USER, DB_PASSWORD);
        UserConsoleOperations operations = new UserConsoleOperations(new UserService(userFileRepository));

        while (true) {
            operations.showMenu();
        }




    }
}
