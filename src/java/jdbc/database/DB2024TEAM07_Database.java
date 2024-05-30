package jdbc.database;

import java.sql.*;

public class DB2024TEAM07_Database{

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/DB2024TEAM07"; // 수정
    static final String USER = "DB2024TEAM07"; // 수정
    static final String PASS = "DB2024TEAM07"; // 수정

    private static DB2024TEAM07_Database instance;
    private Connection conn;

    private DB2024TEAM07_Database() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    //단일 instance를 생성하도록 도와줌
    public static DB2024TEAM07_Database getInstance() {
        if (instance == null) {
            synchronized (DB2024TEAM07_Database.class) {
                if (instance == null) {
                    instance = new DB2024TEAM07_Database();
                }
            }
        }
        return instance;
    }

//    public void closeConnection() {
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}