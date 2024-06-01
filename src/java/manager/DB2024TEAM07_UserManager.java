package manager;

import jdbc.database.DB2024TEAM07_UserDAO;
import jdbc.model.DB2024TEAM07_User;

public class DB2024TEAM07_UserManager {
    private DB2024TEAM07_UserDAO userDAO;
    private DB2024TEAM07_User loggedInUser;

    public DB2024TEAM07_UserManager(DB2024TEAM07_UserDAO userDAO) {
        this.userDAO = userDAO;
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
}
