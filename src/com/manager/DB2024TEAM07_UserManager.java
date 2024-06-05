package com.manager;

import com.jdbc.database.DB2024TEAM07_UserDAO;
import com.jdbc.model.DB2024TEAM07_Restaurant;
import com.jdbc.model.DB2024TEAM07_User;
import com.jdbc.database.DB2024TEAM07_RestaurantDAO;

public class DB2024TEAM07_UserManager {
    private final DB2024TEAM07_UserDAO userDAO;
    private DB2024TEAM07_User loggedInUser;
    private static DB2024TEAM07_RestaurantDAO restaurantDAO;

    public DB2024TEAM07_UserManager(DB2024TEAM07_UserDAO userDAO) {
        this.userDAO = userDAO;
        this.restaurantDAO = new DB2024TEAM07_RestaurantDAO();
    }

    // 사용자 로그인
    public boolean login(String username, String password) {
        int loginResult = userDAO.signIn(username, password);
        if (loginResult == 1) {
            this.loggedInUser = userDAO.getUser(username);
            return true;
        }
        return false;
    }

    // 사용자 로그아웃
    public void logout() {
        this.loggedInUser = null;
    }

    // 사용자 추가 (회원 가입)
    public boolean addUser(DB2024TEAM07_User user) {
        return userDAO.add(user) > 0;
    }

    // 현재 로그인된 사용자 반환
    public DB2024TEAM07_User getLoggedInUser() {
        return loggedInUser;
    }

    // RestaurantDAO 인스턴스 반환
    public DB2024TEAM07_RestaurantDAO getRestaurantDAO() {
        return restaurantDAO;
    }

    public DB2024TEAM07_UserDAO getUserDAO() {
        return userDAO;
    }

    public static void recommendRandomRestaurant() {
        DB2024TEAM07_Restaurant randomRestaurant = restaurantDAO.getRandomRestaurant();
    }
}