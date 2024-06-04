package com.jdbc.database;

import com.jdbc.model.DB2024TEAM07_Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB2024TEAM07_MenuDAO{
    private Connection conn;
    private PreparedStatement pStmt;
    private ResultSet rs;

    public DB2024TEAM07_MenuDAO(){
        this.conn = DB2024TEAM07_Database.getInstance().getConnection();
    }

    //메뉴 등록 (관리자 관점)
    public int add(DB2024TEAM07_Menu menu) {
        String Q = "INSERT INTO DB2024_Menu (menu_id, menu_name, res_id, price) VALUES (?, ?, ?, ?)";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setInt(1, menu.getMenu_id());
            pStmt.setString(2, menu.getMenu_name());
            pStmt.setInt(3, menu.getRes_id());
            pStmt.setInt(4, menu.getPrice());

            return pStmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return 0;
    }

    //    메뉴 조회 1 (사용자 관점)
    //    minPrice 와 maxPrice 사이의 가격에 해당하는 메뉴들 조회
    //    사용자 입장에서 어떤 식당에 있는 메뉴들을 검색할 땐 res_name으로 검색하지 res_id로 검색하지 않기 때문에 Menu 테이블과 Restaurant 테이블을 조인해서 res_name 받아옴.
    //    사용자에게 필요한 정보(res_name, menu_name, price 만 보여주기
    public ResultSet searchByUsers(String res_name, String menu_name, Integer minPrice, Integer maxPrice) {
        StringBuilder Q = new StringBuilder( // DB2024_MenuView 뷰 활용.
                "SELECT res_name, menu_name, price FROM DB2024_MenuView WHERE 1=1"
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

    // 메뉴 조회 - 식당별로 메뉴 검색
    public ResultSet searchMenuByRestaurant(int res_id) {
        String Q = "SELECT menu_id, menu_name, price FROM DB2024_Menu use index(DB2024_idx_Menu) WHERE res_id = ?";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setInt(1, res_id);
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
        StringBuilder queryBuilder = new StringBuilder("UPDATE DB2024_Menu SET ");
        List<Object> parameters = new ArrayList<>();

        if (menu.getMenu_id() != -1) {
            queryBuilder.append("menu_id = ?, ");
            parameters.add(menu.getMenu_id());
        }
        if (menu.getMenu_name() != null && !menu.getMenu_name().isEmpty()) {
            queryBuilder.append("menu_name = ?, ");
            parameters.add(menu.getMenu_name());
        }
        if (menu.getRes_id() != -1) {
            queryBuilder.append("res_id = ?, ");
            parameters.add(menu.getRes_id());
        }
        if (menu.getPrice() != -1) {
            queryBuilder.append("price = ?, ");
            parameters.add(menu.getPrice());
        }

        queryBuilder.setLength(queryBuilder.length() - 2);

        queryBuilder.append(" WHERE res_id = ? AND menu_id = ?");
        parameters.add(pRes_id);
        parameters.add(pMenu_id);

        String query = queryBuilder.toString();

        try {
            pStmt = conn.prepareStatement(query);

            // Set the parameters for the prepared statement
            for (int i = 0; i < parameters.size(); i++) {
                pStmt.setObject(i + 1, parameters.get(i));
            }

            return pStmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
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
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return 0;
    }
}