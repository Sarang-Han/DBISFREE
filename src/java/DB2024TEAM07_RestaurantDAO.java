import java.sql.*;

// 구현해야할 기능: 식당 등록(관리자), 식당 조회(유저), 식당 정보 업데이트(관리자), 식당 삭제(관리자)
// 생각해볼것: location으로 검색할건지 cuisine_type으로 검색할건지 식당 이름으로 검색할건지 --> 복합적인 검색조건으로 식당 조회하는 기능
public class DB2024TEAM07_RestaurantDAO {
    private Connection conn;
    private PreparedStatement pStmt;
    private ResultSet rs;

    public DB2024TEAM07_RestaurantDAO() {
        this.conn = Database.getInstance().getConnection();
    }

    //식당 등록(관리자)
    public int add(DB2024TEAM07_Restaurant restaurant) {
        String Q = "INSERT INTO DB2024_Restaurant (res_name, res_id, phone_num, address, operating_hours, break_time, rating, cuisine_type, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (
                conn =Database.getConnection() ;
                pStmt = conn.prepareStatement(Q);
            ){
            try {
                conn.setAutoCommit(false);

                pStmt.setString(1, restaurant.getRes_name());
                pStmt.setInt(2, restaurant.getRes_id());
                pStmt.setString(3, restaurant.getPhone_num());
                pStmt.setString(4, restaurant.getAddress());
                pStmt.setString(5, restaurant.getOperating_hours());
                pStmt.setString(6, restaurant.getBreak_time());
                pStmt.setFloat(7, restaurant.getRating());
                pStmt.setString(8, restaurant.getCuisine_type());
                pStmt.setString(9, restaurant.getLocation());

                rs = pStmt.executeUpdate();
                conn.commit();
                System.out.println("transaction 성공");

                return rs;
            } catch (SQLException e1) {
                e1.printStackTrace();
                try {
                    if (conn != null) {
                        conn.rollback();
                        System.out.println("롤백 성공");
                    }
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            conn.setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    //식당 조회 (res_id으로 검색)
    public ResultSet search(int res_id) {
        String Q = "SELECT * FROM DB2024_Restaurant WHERE res_id=?";
        try (
                conn =Database.getConnection() ;
                pStmt = conn.prepareStatement(Q);
            ){
            try {
                conn.setAutoCommit(false);

                pStmt.setInt(1, res_id);

                rs = pStmt.executeUpdate();
                conn.commit();
                System.out.println("transaction 성공");

                return rs;
            } catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    //식당 수정
    public int update(DB2024TEAM07_Restaurant restaurant, int pRes_id) {
        String Q = "UPDATE DB2024_Restaurant SET res_name=?, phone_num=?, address=?, operating_hours=?, break_time=?, rating=?, cuisine_type=?, location=? WHERE res_id=?";
        try (
                conn =Database.getConnection() ;
                pStmt = conn.prepareStatement(Q);
            ){
            try {
                conn.setAutoCommit(false);

                pStmt.setString(1, restaurant.getRes_name());
                pStmt.setInt(2, restaurant.getRes_id());
                pStmt.setString(3, restaurant.getPhone_num());
                pStmt.setString(4, restaurant.getAddress());
                pStmt.setString(5, restaurant.getOperating_hours());
                pStmt.setString(6, restaurant.getBreak_time());
                pStmt.setFloat(7, restaurant.getRating());
                pStmt.setString(8, restaurant.getCuisine_type());
                pStmt.setString(9, restaurant.getLocation());
                pStmt.setInt(10, pRes_id);

                rs = pStmt.executeUpdate();
                conn.commit();
                System.out.println("transaction 성공");

                return rs;
            } catch (SQLException e1) {
                e1.printStackTrace();
                try {
                    if (conn != null) {
                        conn.rollback();
                        System.out.println("롤백 성공");
                    }
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            conn.setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }


    //식당 삭제
    public int delete(int res_id) {
        String Q = "DELETE FROM DB2024_Restaurant WHERE res_id=?";
        try (
                conn =Database.getConnection() ;
        pStmt = conn.prepareStatement(Q);
            ){
            try {
                conn.setAutoCommit(false);

                pStmt.setInt(1, res_id);

                rs = pStmt.executeUpdate();
                conn.commit();
                System.out.println("transaction 성공");

                return rs;
            } catch (SQLException e1) {
                e1.printStackTrace();
                try {
                    if (conn != null) {
                        conn.rollback();
                        System.out.println("롤백 성공");
                    }
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            conn.setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}