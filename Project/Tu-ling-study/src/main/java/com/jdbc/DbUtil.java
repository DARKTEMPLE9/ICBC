package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    /**
     * 打开数据库
     */
    private static String driver;//数据库驱动

    private static String url;

    private static String username;

    private static String password;

    static {
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/vhr";
        username = "root";
        password = "root";
    }

    public static Connection open() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("数据库连接关闭失败");
                e.printStackTrace();
            }
        }

    }

}
