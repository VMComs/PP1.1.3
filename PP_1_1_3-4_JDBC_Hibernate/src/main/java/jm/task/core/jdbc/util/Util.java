package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Util {
    private final static String userName = "root";
    private final static String password = "Cjkmlkzdct[1";
    private final static String connectionURL = "jdbc:mysql://localhost:3306/new_schema?serverTimezone=Europe/Moscow&useSSL=false";
    private final static String JDBCDriver = "com.mysql.jdbc.Driver";


    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBCDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(connectionURL, userName, password);
    }    // реализуйте настройку соеденения с БД
}
