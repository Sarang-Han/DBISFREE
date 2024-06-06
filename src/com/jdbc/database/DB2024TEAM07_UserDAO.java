package com.jdbc.database;

/* 구현된 기능:
    회원가입:   add(DB2024TEAM07_User user)
    로그인:    sighIn(String user_id, String user_pw)
    회원정보 수정:    update(DB2024TEAM07_User user, String pUser_id)
    회원정보 확인:    getUser(String user_id),    getOtherUser(String user_id)
    회원탈퇴:   delete(String user_id, String user_pw)
 */

import com.jdbc.model.DB2024TEAM07_User;
import com.jdbc.view.DB2024TEAM07_UserVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a Data Access Object (DAO) for managing users in a database.
 * It offers methods for user registration, login, information modification, retrieval, and deletion.
 */
public class DB2024TEAM07_UserDAO {
    private Connection conn;
    private PreparedStatement pStmt;
    private ResultSet rs;

    /**
     * Constructor that initializes the database connection.
     */
    public DB2024TEAM07_UserDAO() {
        this.conn = DB2024TEAM07_Database.getInstance().getConnection();
    }

    //회원가입 기능(DB2024_User 테이블에 투플 삽입)-----------------------------------------------------------------------
    /**
     * Registers a new user by inserting a tuple into the DB2024_User table.
     *
     * @param user The DB2024TEAM07_User object representing the new user to be registered.
     * @return The number of rows affected by the INSERT operation. This should be 1 if the registration was successful.
     *         Returns -2 if an error occurs during registration.
     */
    public int add(DB2024TEAM07_User user) {
        String Q = "INSERT INTO DB2024_User VALUES (?, ?, ?, ?, ?, ?)";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, user.getUser_id());
            pStmt.setString(2, user.getUser_pw());
            pStmt.setString(3, user.getName());
            pStmt.setInt(4, user.getStudent_id());
            pStmt.setString(5, user.getEmail());
            pStmt.setString(6, user.getLocation());
            return pStmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return -2;  //error
    }

    //로그인 기능(DB2024_User 테이블 검색)-----------------------------------------------------------------------
    /**
     * Attempts to log in a user by verifying their credentials against the DB2024_User table.
     *
     * @param user_id The user's ID.
     * @param user_pw The user's password.
     * @return 1 if the login is successful (user ID exists and password matches).
     *         0 if the login fails due to an incorrect password (user ID exists but password doesn't match).
     *        -1 if the login fails due to a non-existent user ID.
     *        -2 if an error occurs during the login process.
     */
    public int signIn(String user_id, String user_pw) {
        String Q = "SELECT user_pw FROM DB2024_User WHERE user_id = ?";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, user_id);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                if (rs.getString(1).equals(user_pw))
                    return 1;   //id: 존재 / pw: 일치
                else
                    return 0;   //id: 존재 / pw: 불일치
            } else
                return -1;      //id: 결과 없음
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return -2;  //error
    }

    //회원정보 수정(DB2024_User 테이블 검색, 투플값 수정)-----------------------------------------------------------------------
    //수정하고자 하는 유저의 기존 아이디 전달 필요(두 번째 인자)
    //수정할 내용을 담은 User 객체와, 수정하기 이전의 user id를 전달받아 수정하는 방식
    /**
     * Updates a user's information in the DB2024_User table.
     *
     * @param user The DB2024TEAM07_User object containing the updated information.
     * @param pUser_id The previous user ID of the user whose information is being updated.
     * @return The number of rows affected by the UPDATE operation. This should be 1 if the update was successful.
     *         Returns -2 if an error occurs during the update process.
     */
    public int update(DB2024TEAM07_User user, String pUser_id) {
        String Q = "UPDATE DB2024_User SET user_id=?, user_pw=?, name=?, student_id=?, email=?, location=? WHERE user_id=?";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, user.getUser_id());
            pStmt.setString(2, user.getUser_pw());
            pStmt.setString(3, user.getName());
            pStmt.setInt(4, user.getStudent_id());
            pStmt.setString(5, user.getEmail());
            pStmt.setString(6, user.getLocation());
            pStmt.setString(7, pUser_id);
            return pStmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return -2;  //error
    }

    //회원정보 확인 기능-----------------------------------------------------------------------
    //로그인한 유저가 본인의 정보를 확인하는 용도
    /**
     * Retrieves the information of the logged-in user.
     *
     * @param user_id The ID of the logged-in user.
     * @return A DB2024TEAM07_User object containing the user's information, or null if the user is not found.
     */
    public DB2024TEAM07_User getUser(String user_id) {
        String Q = "SELECT * FROM DB2024_User WHERE user_id = ?";
        try {
            DB2024TEAM07_User user = new DB2024TEAM07_User();
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, user_id);
            rs = pStmt.executeQuery();
            if (rs.next()) {    //id: 존재
                user.setUser_id(rs.getString(1));
                user.setUser_pw(rs.getString(2));
                user.setName(rs.getString(3));
                user.setStudent_id(rs.getInt(4));
                user.setEmail(rs.getString(5));
                user.setLocation(rs.getString(6));
                return user;
            }
            //else
            //id: 불일치(결과 없음)
            //아래 리턴문에서 null이 반환됨
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;  //error, id 없음
    }

    //회원정보 확인 기능(2)-----------------------------------------------------------------------
    //다른 유저의 정보를 확인하는 용도
    /**
     * Retrieves the information of another user.
     *
     * @param user_id The ID of the user whose information is being retrieved.
     * @return A DB2024TEAM07_UserVO object containing the user's basic information (ID, name, location), or null if the user is not found.
     */
    public DB2024TEAM07_UserVO getOtherUser(String user_id) {
        String Q = "SELECT * FROM DB2024_OtherUser WHERE user_id = ?";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, user_id);
            rs = pStmt.executeQuery();
            if (rs.next()) {    //id: 존재
                DB2024TEAM07_UserVO user = new DB2024TEAM07_UserVO(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                return user;
            }
            //else
            //id: 불일치(결과 없음)
            //아래 리턴문에서 null이 반환됨
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;  //error, id 없음
    }

    //탈퇴 기능(DB2024_User 테이블의 투플 삭제)-----------------------------------------------------------------------
    //유저가 비밀번호를 작성해야 탈퇴할 수 있는 구조로 구현되면 좋다.
    /*
        윗단에서 signin을 진행한 다음(유저에게 pw를 받아오기) 그 리턴값을 받아서
        이 함수 안의 signInRes if문이 작성된 형태로, 조건부로 이 함수가 불리는 편이 더 깔끔할 것 같긴 한데
        일단은 제가 내부적으로 구현해 두었습니다
     */
    /**
     * Deletes a user from the DB2024_User table.
     * The user must enter their password to confirm the deletion.
     *
     * @param user_id The ID of the user to be deleted.
     * @param user_pw The password of the user to be deleted.
     * @return 1 if the deletion is successful.
     *         0 if the deletion fails due to an incorrect password.
     *        -1 if the deletion fails due to a non-existent user ID.
     *        -2 if an error occurs during the deletion process.
     */
    public int delete(String user_id, String user_pw) {
        String Q = "DELETE FROM DB2024_User WHERE user_id = ?";
        try {
            int signInRes = signIn(user_id, user_pw);
            if (signInRes == 1) {    //id ok pw ok
                pStmt = conn.prepareStatement(Q);
                pStmt.setString(1, user_id);
                return pStmt.executeUpdate(); // executeUpdate()로 변경
            } else {                  //0: id o pw x, -1: id x, -2: error
                return signInRes;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return -2;  //error
    }

    public List<DB2024TEAM07_User> getAllUsers() {
        List<DB2024TEAM07_User> users = new ArrayList<>();
        String query = "SELECT * FROM DB2024_User";
        try {
            pStmt = conn.prepareStatement(query);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                DB2024TEAM07_User user = new DB2024TEAM07_User();
                user.setUser_id(rs.getString("user_id"));
                user.setUser_pw(rs.getString("user_pw"));
                user.setName(rs.getString("name"));
                user.setStudent_id(rs.getInt("student_id"));
                user.setEmail(rs.getString("email"));
                user.setLocation(rs.getString("location"));
                users.add(user);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return users;
    }
}