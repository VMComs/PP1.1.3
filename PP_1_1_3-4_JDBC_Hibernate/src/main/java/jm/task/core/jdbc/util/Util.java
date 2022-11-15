package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Util {
    private final static String USERNAME = "root";
    private final static String PASSWORD = "Cjkmlkzdct[1";
    private final static String URL = "jdbc:mysql://localhost:3306/new_schema?serverTimezone=Europe/Moscow&useSSL=false";
    private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Ошибка подключения к БД");
        }
            return conn;
    }
}
