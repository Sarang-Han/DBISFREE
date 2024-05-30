package jdbc.database;

import jdbc.model.DB2024TEAM07_Menu;

import java.sql.*;
import java.util.*;

//구현해야할 기능: 메뉴 등록, 메뉴 조회, 메뉴 수정, 메뉴 삭제
//건의할거: menu 테이블에 menu_id 추가? 한 식당 내에 메뉴 여러개라 메뉴 id 속성도 추가하면 좋을거같음.
public class DB2024TEAM07_MenuDAO{
    private Connection conn;
    private PreparedStatement pStmt;
    private ResultSet rs;

    public DB2024TEAM07_MenuDAO(){
        this.conn = DB2024TEAM07_Database.getInstance().getConnection();
    }

    //메뉴 등록 (관리자 관점)
    public int add(DB2024TEAM07_Menu menu) {
        String Q = "INSERT INTO DB2024_Menu (menu_id, menu_name, res_id, price, menu_comment) VALUES (?, ?, ?, ?, ?)";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setInt(1, menu.getMenu_id());
            pStmt.setString(2, menu.getMenu_name());
            pStmt.setInt(3, menu.getRes_id());
            pStmt.setInt(4, menu.getPrice());
            pStmt.setString(5, menu.getMenu_comment());

            return pStmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return 0;
    }

    //    메뉴 조회 1 (사용자 관점)
    //    minPrice 와 maxPrice 사이의 가격에 해당하는 메뉴들 조회
    //    사용자 입장에서 어떤 식당에 있는 메뉴들을 검색할 땐 res_name으로 검색하지 res_id로 검색하지 않기 때문에 Menu 테이블과 Restaurant 테이블을 조인해서 res_name 받아옴.
    //    사용자에게 필요한 정보(res_name, menu_name, price, menu_comment 만 보여주기
    public ResultSet searchByUsers(String res_name, String menu_name, Integer minPrice, Integer maxPrice) {
        StringBuilder Q = new StringBuilder( // DB2024_MenuView 뷰 활용.
                "SELECT res_name, menu_name, price, menu_comment FROM DB2024_MenuView WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();

        if (res_name != null && !res_name.isEmpty()) {
            Q.append(" AND res_name LIKE ?");
            params.add("%" + res_name + "%");
        }

        if (menu_name != null && !menu_name.isEmpty()) {
            Q.append(" AND menu_name LIKE ?");
            params.add("%" + menu_name + "%");
        }

        if (minPrice != null) {
            Q.append(" AND price >= ?");
            params.add(minPrice);
        }

        if (maxPrice != null) {
            Q.append(" AND price <= ?");
            params.add(maxPrice);
        }

        try {
            pStmt = conn.prepareStatement(Q.toString());

            for (int i = 0; i < params.size(); i++) {
                pStmt.setObject(i + 1, params.get(i));
            }
            return pStmt.executeQuery();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    // 메뉴 조회 2 (관리자 관점 - res_id로 검색해도 괜찮은 주체)
    public ResultSet searchByManager(int res_id) {
        String Q = "SELECT * FROM DB2024_Menu WHERE res_id=?";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setInt(1, res_id);

            return pStmt.executeQuery();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    //메뉴 수정 (관리자 관점)
        public int update(DB2024TEAM07_Menu menu, int pRes_id, int pMenu_id) {
            String Q = "UPDATE DB2024_Menu SET menu_id=?, menu_name=?, res_id=?, price=?, menu_comment=? WHERE res_id=? AND menu_id=?";
            try {
                pStmt = conn.prepareStatement(Q);
                pStmt.setInt(1, menu.getMenu_id());
                pStmt.setString(2, menu.getMenu_name());
                pStmt.setInt(3, menu.getRes_id());
                pStmt.setInt(4, menu.getPrice());
                pStmt.setString(5, menu.getMenu_comment());
                pStmt.setInt(6, pRes_id);
                pStmt.setInt(7, pMenu_id);

                return pStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }

    //메뉴 삭제 (관리자 관점)
        public int delete(int res_id, int menu_id) {
            String Q = "DELETE FROM DB2024_Menu WHERE res_id=? AND menu_id=?";
            try {
                pStmt = conn.prepareStatement(Q);
                pStmt.setInt(1, res_id);
                pStmt.setInt(2, menu_id);

                return pStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }
}