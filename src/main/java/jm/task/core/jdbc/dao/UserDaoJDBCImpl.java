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
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id BIGINT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR (45)NOT NULL, lastname VARCHAR (45) NOT NULL,age TINYINT NOT NULL, PRIMARY KEY (id)) ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Таблица users удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users(name,lastname,age)VALUES(?,?,?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Users не добавлены");
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE 'id' = id LIMIT 1L";
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            System.out.println("User c id - " + id + " удален из базы данных");
        } catch (SQLException e) {
            System.out.println("User не удален");
        }
    }

    public List<User> getAllUsers() {
        List<User> user = new ArrayList<>();
        String query = "select * from  users ";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                Byte age = rs.getByte("age");
                user.add(new User(id, name, lastname, age));
            }
            System.out.println(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
