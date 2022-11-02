package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final static String CREATE_TABLE_SQL = "create table if not exists users_table"
            + "(user_id int auto_increment,"
            + "name varchar(20) not null,"
            + "lastName varchar(20) not null,"
            + "age int not null,"
            + "primary key (user_id))";
    private final static String DROP_TABLE_SQL = "drop table if exists users_table";
    private final static String INSERT_USER_SQL = "insert into users_table (name, lastName, age) VALUES (?, ?, ?)";
    private final static String GET_ALL_USERS_SQL = "select * from users_table";
    private final static String REMOVE_BY_ID_SQL = "delete from users_table where user_id = ?";
    private final static String CLEAN_TABLE_SQL = "delete from users_table";

    public UserDaoJDBCImpl() throws SQLException {
    }
    Connection connection = Util.getConnection();

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_SQL);
        } catch (SQLException ex) {
            System.out.println("users уже создана");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(DROP_TABLE_SQL);
        } catch (SQLException ex) {
            System.out.println("Такая база уже не существует");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement pstatement = connection.prepareStatement(INSERT_USER_SQL)) {
            pstatement.setString(1, name);
            pstatement.setString(2, lastName);
            pstatement.setInt(3, age);
            pstatement.execute();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement prstatement = connection.prepareStatement(REMOVE_BY_ID_SQL)) {
            prstatement.setString(1, String.valueOf(id));
            prstatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_SQL);
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getInt("user_id"));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CLEAN_TABLE_SQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
