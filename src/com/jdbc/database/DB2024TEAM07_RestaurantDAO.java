package com.jdbc.database;

import com.jdbc.model.DB2024TEAM07_Restaurant;

import java.sql.*;
import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 구현해야할 기능: 식당 등록(관리자), 식당 조회(유저), 식당 정보 업데이트(관리자), 식당 삭제(관리자)
// 생각해볼것: location으로 검색할건지 cuisine_type으로 검색할건지 식당 이름으로 검색할건지 --> 복합적인 검색조건으로 식당 조회하는 기능

public class DB2024TEAM07_RestaurantDAO {
    private Connection conn;
    private PreparedStatement pStmt;

    public DB2024TEAM07_RestaurantDAO() {
        this.conn = DB2024TEAM07_Database.getInstance().getConnection();
    }

    //식당 등록 (관리자 관점) ---- DB2024_Restaurant에 투플 삽입------
    public int add(DB2024TEAM07_Restaurant restaurant) {
        String Q = "INSERT INTO DB2024_Restaurant (res_name, res_id, phone_num, address, operating_hours, break_time, rating, cuisine_type, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pStmt = conn.prepareStatement(Q);

            // res_name
            if (restaurant.getRes_name() != null) {
                pStmt.setString(1, restaurant.getRes_name());
            } else {
                pStmt.setNull(1, java.sql.Types.VARCHAR);
            }

            // res_id
            pStmt.setInt(2, restaurant.getRes_id());  // res_id는 반드시 있어야 한다고 가정

            // phone_num
            if (restaurant.getPhone_num() != null) {
                pStmt.setString(3, restaurant.getPhone_num());
            } else {
                pStmt.setNull(3, java.sql.Types.VARCHAR);
            }

            // address
            if (restaurant.getAddress() != null) {
                pStmt.setString(4, restaurant.getAddress());
            } else {
                pStmt.setNull(4, java.sql.Types.VARCHAR);
            }

            // operating_hours
            if (restaurant.getOperating_hours() != null) {
                pStmt.setString(5, restaurant.getOperating_hours());
            } else {
                pStmt.setNull(5, java.sql.Types.VARCHAR);
            }

            // break_time
            if (restaurant.getBreak_time() != null) {
                pStmt.setString(6, restaurant.getBreak_time());
            } else {
                pStmt.setNull(6, java.sql.Types.VARCHAR);
            }

            // rating
            if (restaurant.getRating() != -1) {
                pStmt.setFloat(7, restaurant.getRating());
            } else {
                pStmt.setNull(7, java.sql.Types.FLOAT);
            }

            // cuisine_type
            if (restaurant.getCuisine_type() != null) {
                pStmt.setString(8, restaurant.getCuisine_type());
            } else {
                pStmt.setNull(8, java.sql.Types.VARCHAR);
            }

            // location
            if (restaurant.getLocation() != null) {
                pStmt.setString(9, restaurant.getLocation());
            } else {
                pStmt.setNull(9, java.sql.Types.VARCHAR);
            }

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
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return restaurants;
    }

    // 식당 검색 - cuisine_type 별로
    public ResultSet searchRestaurantByCategory(String cuisine_type) {
        String Q = "SELECT res_name, phone_num, address, operating_hours, break_time, rating, location " +
                "FROM DB2024_Restaurant USE INDEX(DB2024_idx_Restaurant) WHERE cuisine_type = ?";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, cuisine_type);
            return pStmt.executeQuery();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    //식당 수정 (관리자 관점)
    public int update(DB2024TEAM07_Restaurant restaurant, int pRes_id) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE DB2024_Restaurant SET ");
        List<Object> parameters = new ArrayList<>();

        if (restaurant.getRes_name() != null) {
            queryBuilder.append("res_name=?, ");
            parameters.add(restaurant.getRes_name());
        }
        if (restaurant.getPhone_num() != null) {
            queryBuilder.append("phone_num=?, ");
            parameters.add(restaurant.getPhone_num());
        }
        if (restaurant.getAddress() != null) {
            queryBuilder.append("address=?, ");
            parameters.add(restaurant.getAddress());
        }
        if (restaurant.getOperating_hours() != null) {
            queryBuilder.append("operating_hours=?, ");
            parameters.add(restaurant.getOperating_hours());
        }
        if (restaurant.getBreak_time() != null) {
            queryBuilder.append("break_time=?, ");
            parameters.add(restaurant.getBreak_time());
        }
        if (restaurant.getRating() != -1) {
            queryBuilder.append("rating=?, ");
            parameters.add(restaurant.getRating());
        }
        if (restaurant.getCuisine_type() != null) {
            queryBuilder.append("cuisine_type=?, ");
            parameters.add(restaurant.getCuisine_type());
        }
        if (restaurant.getLocation() != null) {
            queryBuilder.append("location=?, ");
            parameters.add(restaurant.getLocation());
        }

        if (!parameters.isEmpty()) {
            queryBuilder.setLength(queryBuilder.length() - 2);
        } else {
            return 0;
        }

        queryBuilder.append(" WHERE res_id=?");
        parameters.add(pRes_id);

        String query = queryBuilder.toString();

        try {
            pStmt = conn.prepareStatement(query);
//
            int i = 1;
            for (Object param : parameters) {
                pStmt.setObject(i, param);
                i++;
            }
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

            return pStmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return 0;
    }

    public DB2024TEAM07_Restaurant getRandomRestaurant() {
        String query = "SELECT * FROM DB2024_Restaurant ORDER BY RAND() LIMIT 1";
        try {
            ResultSet rs = conn.createStatement().executeQuery(query);
            if (rs.next()) {
                return new DB2024TEAM07_Restaurant(
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 모든 레스토랑을 검색하여 레스토랑 이름, 아이디 반환
    public List<DB2024TEAM07_Restaurant> getAllRestaurants() {
        String query = "SELECT res_id, res_name FROM DB2024_Restaurant";
        List<DB2024TEAM07_Restaurant> restaurants = new ArrayList<>();

        try {
            pStmt = conn.prepareStatement(query);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                DB2024TEAM07_Restaurant restaurant = new DB2024TEAM07_Restaurant(
                        rs.getString("res_name"),
                        rs.getInt("res_id"),
                        null, // phone_num
                        null, // address
                        null, // operating_hours
                        null, // break_time
                        -1, // rating
                        null, // cuisine_type
                        null // location
                );
                restaurants.add(restaurant);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return restaurants;
    }
}

