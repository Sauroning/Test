package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Util util = new Util();
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("CREATE TABLE users (" +
                    "`id` INT NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(40) NULL,`lastname` VARCHAR(40) NULL," +
                    "`age` TINYINT(3) NULL, PRIMARY KEY (`id`));");
        } catch (SQLSyntaxErrorException a) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        final String DROP = "DROP TABLE users";
        Util util = new Util();
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(DROP)) {
            preparedStatement.execute();
        } catch (SQLSyntaxErrorException a) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        final String SAVE = "INSERT INTO users (name, lastname, age) VALUES (?,?,?)";
        Util util = new Util();
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(SAVE)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        final String REMOVE = "DELETE FROM users WHERE id=?";
        Util util = new Util();
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(REMOVE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        final String GET_ALL = "SELECT * FROM users";
        Util util = new Util();
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(GET_ALL)) {
            ResultSet res = preparedStatement.executeQuery();
            List<User> result = new ArrayList<>();
            while (res.next()) {
                User user = new User();
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setLastName(res.getString("lastname"));
                user.setAge(res.getByte("age"));
                result.add(user);
            }
            System.out.println(result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        final String REMOVE = "TRUNCATE users";
        Util util = new Util();
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(REMOVE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
