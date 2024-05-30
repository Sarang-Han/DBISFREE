package jdbc.database;

import jdbc.model.DB2024TEAM07_Restaurant;
import jdbc.view.DB2024TEAM07_CategoryVO;

import java.sql.*;
import java.util.*;

// 구현해야할 기능: 식당 등록(관리자), 식당 조회(유저), 식당 정보 업데이트(관리자), 식당 삭제(관리자)
// 생각해볼것: location으로 검색할건지 cuisine_type으로 검색할건지 식당 이름으로 검색할건지 --> 복합적인 검색조건으로 식당 조회하는 기능

public class DB2024TEAM07_RestaurantDAO {
    private Connection conn;
    private PreparedStatement pStmt;
    private ResultSet rs;

    public DB2024TEAM07_RestaurantDAO() {
        this.conn = DB2024TEAM07_Database.getInstance().getConnection();
    }

    //식당 등록 (관리자 관점) ---- DB2024_Restaurant에 투플 삽입------
    public int add(DB2024TEAM07_Restaurant restaurant) {
        String Q = "INSERT INTO DB2024_Restaurant (res_name, res_id, phone_num, address, operating_hours, break_time, rating, cuisine_type, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, restaurant.getRes_name());
            pStmt.setInt(2, restaurant.getRes_id());
            pStmt.setString(3, restaurant.getPhone_num());
            pStmt.setString(4, restaurant.getAddress());
            pStmt.setString(5, restaurant.getOperating_hours());
            pStmt.setString(6, restaurant.getBreak_time());
            pStmt.setFloat(7, restaurant.getRating());
            pStmt.setString(8, restaurant.getCuisine_type());
            pStmt.setString(9, restaurant.getLocation());

            return pStmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return 0;
    }

    // 식당 검색 -복합적인 조건
    // res_name, rating(0.5 단위 별로 3.0을 입력하면 3.0점 이상인 식당), cuisine_type, location 4가지 조건을 복합적으로 검색
    // rating의 경우 사용자가 입력한 평점 이상인 식당들 검색
    public List<DB2024TEAM07_Restaurant> search(String res_name, String cuisine_type, String location, Float rating) {

        StringBuilder Q = new StringBuilder("SELECT * FROM DB2024_Restaurant WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (res_name != null && !res_name.isEmpty()) {
            Q.append(" AND res_name LIKE ?");
            params.add("%" + res_name + "%");
        }
        if (cuisine_type != null && !cuisine_type.isEmpty()) {
            Q.append(" AND cuisine_type LIKE ?");
            params.add("%" + cuisine_type + "%");
        }
        if (location != null && !location.isEmpty()) {
            Q.append(" AND location LIKE ?");
            params.add("%" + location + "%");
        }
        if (rating != null) {
            Q.append(" AND rating >= ?");
            params.add(rating);
        }

        List<DB2024TEAM07_Restaurant> restaurants = new ArrayList<>();
        try {
            PreparedStatement pStmt = conn.prepareStatement(Q.toString());

            for (int i = 0; i < params.size(); i++) {
                pStmt.setObject(i + 1, params.get(i));
            }
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                DB2024TEAM07_Restaurant restaurant = new DB2024TEAM07_Restaurant(
                        rs.getString("res_name"),
                        rs.getInt("res_id"),
                        rs.getString("phone_num"),
                        rs.getString("address"),
                        rs.getString("operating_hours"),
                        rs.getString("break_time"),
                        rs.getFloat("rating"),
                        rs.getString("cuisine_type"),
                        rs.getString("location")
                );
                restaurants.add(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    public DB2024TEAM07_CategoryVO searchByCategory(String cuisine_type) {
        String Q = "SELECT * FROM DB2024_Category WHERE cuisine_type = ?";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, cuisine_type);
            rs = pStmt.executeQuery();

            if (rs.next()) {
                DB2024TEAM07_CategoryVO category = new DB2024TEAM07_CategoryVO(
                        rs.getString("cuisine_type"),
                        rs.getString("res_name")
                );
                return category;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    //식당 수정 (관리자 관점)
    public int update(DB2024TEAM07_Restaurant restaurant, int pRes_id) {
        String Q = "UPDATE DB2024_Restaurant SET res_name=?, phone_num=?, address=?, operating_hours=?, break_time=?, rating=?, cuisine_type=?, location=? WHERE res_id=?";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, restaurant.getRes_name());
            pStmt.setString(2, restaurant.getPhone_num());
            pStmt.setString(3, restaurant.getAddress());
            pStmt.setString(4, restaurant.getOperating_hours());
            pStmt.setString(5, restaurant.getBreak_time());
            pStmt.setFloat(6, restaurant.getRating());
            pStmt.setString(7, restaurant.getCuisine_type());
            pStmt.setString(8, restaurant.getLocation());
            pStmt.setInt(9, pRes_id);

            return pStmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return 0;
    }

    //식당 삭제 (관리자 관점)
    public int delete(int res_id) {
        String Q = "DELETE FROM DB2024_Restaurant WHERE res_id=?";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setInt(1, res_id);

            rs = pStmt.executeQuery();
            return pStmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return 0;
    }
}
