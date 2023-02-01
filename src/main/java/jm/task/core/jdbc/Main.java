package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Фродо", "Беггинс", (byte) 30);
        userService.saveUser("Бильбо", "Беггинс", (byte) 100);
        userService.saveUser("Семуайс", "Генджи", (byte) 30);
        userService.saveUser("Перегрим", "Тук", (byte) 30);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.connectionClose();

    }
}
