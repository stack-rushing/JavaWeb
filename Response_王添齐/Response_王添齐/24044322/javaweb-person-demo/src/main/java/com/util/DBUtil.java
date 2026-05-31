package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/javaweb_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PWD = "你的数据库密码";

    static {
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PWD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
