import java.sql.*;
import java.util.*;

// 구현해야할 기능: 식당 등록(관리자), 식당 조회(유저), 식당 정보 업데이트(관리자), 식당 삭제(관리자)
// 생각해볼것: location으로 검색할건지 cuisine_type으로 검색할건지 식당 이름으로 검색할건지 --> 복합적인 검색조건으로 식당 조회하는 기능
public class DB2024TEAM07_RestaurantDAO {
    private Connection conn;
    private PreparedStatement pStmt;
    private ResultSet rs;

    public DB2024TEAM07_RestaurantDAO() {
        this.conn = Database.getInstance().getConnection();
    }

    //식당 등록 (관리자 관점)
    public int add(DB2024TEAM07_Restaurant restaurant) {
        String Q = "INSERT INTO DB2024_Restaurant (res_name, res_id, phone_num, address, operating_hours, break_time, rating, cuisine_type, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn.setAutoCommit(false);
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

            int rs = pStmt.executeUpdate();
            conn.commit();
            System.out.println("transaction 성공");

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("롤백 성공");
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
                if (pStmt != null) pStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    // 식당 검색 -복합적인 조건
    //res_name, rating, cuisine_type, location 4가지 조건을 복합적으로 검색
    //rating의 경우 사용자가 입력한 평점 이상인 식당들 검색
    public List<DB2024TEAM07_Restaurant> search(String res_name, String cuisine_type, String location, Float rating) {

        StringBuilder Q = new StringBuilder("SELECT * FROM DB2024_Restaurant WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (res_name != null && !res_name.isEmpty()) {
            Q.append(" AND res_name LIKE ?");
            params.add("'%" + res_name + "%'");
        }
        if (cuisine_type != null && !cuisine_type.isEmpty()) {
            Q.append(" AND cuisine_type LIKE ?");
            params.add("'%" + cuisine_type + "%'");
        }
        if (location != null && !location.isEmpty()) {
            Q.append(" AND location LIKE ?");
            params.add("'%" + location + "%'");
        }
        if (rating != null) {
            Q.append(" AND rating >= ?");
            params.add(rating);
        }

        List<DB2024TEAM07_Restaurant> restaurants = new ArrayList<>();
        try {
            pStmt = conn.prepareStatement(Q.toString());

            for (int i = 0; i < params.size(); i++) {
                pStmt.setObject(i + 1, params.get(i));
            }
            rs = pStmt.executeQuery();

            while (rs.next()) {
                DB2024TEAM07_Restaurant restaurant = new DB2024TEAM07_Restaurant();
                restaurant.setRes_name(rs.getString("res_name"));
                restaurant.setRes_id(rs.getInt("res_id"));
                restaurant.setPhone_num(rs.getString("phone_num"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setOperating_hours(rs.getString("operating_hours"));
                restaurant.setBreak_time(rs.getString("break_time"));
                restaurant.setRating(rs.getFloat("rating"));
                restaurant.setCuisine_type(rs.getString("cuisine_type"));
                restaurant.setLocation(rs.getString("location"));
                restaurants.add(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pStmt != null) pStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return restaurants;
    }

    //식당 수정 (관리자 관점)
    public int update(DB2024TEAM07_Restaurant restaurant, int pRes_id) {
        String Q = "UPDATE DB2024_Restaurant SET res_name=?, phone_num=?, address=?, operating_hours=?, break_time=?, rating=?, cuisine_type=?, location=? WHERE res_id=?";
        try {
            conn.setAutoCommit(false);
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

            int rs = pStmt.executeUpdate();
            conn.commit();
            System.out.println("transaction 성공");

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("롤백 성공");
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
                if (pStmt != null) pStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    //식당 삭제 (관리자 관점)
    public int delete(int res_id) {
        String Q = "DELETE FROM DB2024_Restaurant WHERE res_id=?";
        try {
            conn.setAutoCommit(false);
            pStmt = conn.prepareStatement(Q);
            pStmt.setInt(1, res_id);

            int rs = pStmt.executeUpdate();
            conn.commit();
            System.out.println("transaction 성공");

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("롤백 성공");
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
                if (pStmt != null) pStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
