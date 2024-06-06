package com.jdbc.database;

import com.jdbc.model.DB2024TEAM07_Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides data access object (DAO) methods for interacting with the DB2024_Menu table in a database.
 * It handles CRUD (Create, Read, Update, Delete) operations on menu data.
 */
public class DB2024TEAM07_MenuDAO{
    private Connection conn;
    private PreparedStatement pStmt;
    private ResultSet rs;

    /**
     * Constructor that establishes a connection to the database using the DB2024TEAM07_Database class.
     */
    public DB2024TEAM07_MenuDAO(){
        this.conn = DB2024TEAM07_Database.getInstance().getConnection();
    }

    /**
     * Adds a new menu item to the database.
     *
     * @param menu the DB2024TEAM07_Menu object representing the menu item to add
     * @return the number of rows affected (usually 1 for a successful insert)
     */
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

    /**
     * Retrieves all menus for a specific restaurant identified by its ID.
     * This method utilizes an index (DB2024_idx_Menu) on the res_id column for efficient retrieval.
     *
     * @param res_id the ID of the restaurant
     * @return a ResultSet containing all menus for the specified restaurant
     */
    public ResultSet searchMenuByRestaurant(int res_id) { // 메뉴 조회 - 식당별로 메뉴 검색
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

    /**
     * Searches for menus managed by a restaurant with the specified ID.
     * This method is intended for use by restaurant managers to view their restaurant's menus.
     *
     * @param res_id the ID of the restaurant
     * @return a ResultSet containing all menus for the specified restaurant
     */
    public ResultSet searchByManager(int res_id) { // 메뉴 조회 2 (관리자 관점 - res_id로 검색해도 괜찮은 주체)
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

    /**
     * Updates an existing menu item in the database.
     *
     * @param menu the DB2024TEAM07_Menu object containing the updated information for the menu item
     * @param pRes_id the original restaurant ID of the menu item (for identification)
     * @param pMenu_id the original menu ID of the menu item (for identification)
     * @return the number of rows affected (usually 1 for a successful update)
     */
    public int update(DB2024TEAM07_Menu menu, int pRes_id, int pMenu_id) {  //메뉴 수정 (관리자 관점)
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

            for (int i = 0; i < parameters.size(); i++) {
                pStmt.setObject(i + 1, parameters.get(i));
            }

            return pStmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return 0;
    }

    /**
     * Deletes a menu item from the database.
     *
     * @param res_id the ID of the restaurant the menu item belongs to
     * @param menu_id the ID of the menu item to delete
     * @return the number of rows affected (usually 1 for a successful delete)
     */
    public int delete(int res_id, int menu_id) { //메뉴 삭제 (관리자 관점)
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

    /**
     * Retrieves all menu names and corresponding IDs for a specific restaurant.
     *
     * @param res_id the ID of the restaurant
     * @return a ResultSet containing menu names and IDs
     */
    public ResultSet getAllMenuByRestaurant(int res_id) {
        String query = "SELECT menu_id, menu_name FROM DB2024_Menu WHERE res_id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, res_id);
            return statement.executeQuery();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }
}