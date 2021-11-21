package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Karman", (byte) 28);
        userService.saveUser("Misha", "Petrov", (byte) 29);
        userService.saveUser("Nik", "Durov", (byte) 39);
        userService.saveUser("Dima", "Komarov", (byte) 20);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
