package com.jdbc.database;

import java.sql.*;

/**
 * This class provides a single point of access to the database connection for the E-MATEASY application.
 * It utilizes the Singleton design pattern to ensure only one instance of the database connection is created
 * and shared throughout the application.
 */
public class DB2024TEAM07_Database{

    /**
     * The JDBC driver class name.
     */
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * The database URL for connecting to the E-MATEASY database.
     */
    static final String DB_URL = "jdbc:mysql://localhost:3306/DB2024TEAM07"; // 수정
    static final String USER = "DB2024TEAM07"; // 수정
    static final String PASS = "DB2024TEAM07"; // 수정


    private static DB2024TEAM07_Database instance;
    private Connection conn;

    /**
     * Private constructor to prevent object creation from outside the class.
     * This enforces the Singleton design pattern.
     */
    private DB2024TEAM07_Database() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the single instance of the DB2024TEAM07_Database object.
     *
     * @return the instance of DB2024TEAM07_Database
     */
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

    /**
     * Provides access to the established database connection.
     *
     * @return the Connection object representing the connection to the database
     */
    public Connection getConnection() {
        return conn;
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