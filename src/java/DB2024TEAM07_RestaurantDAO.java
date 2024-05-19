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

    //식당 조회 1. res_name으로 검색
    public ResultSet searchByRes_name(String res_name) {
        String Q = "SELECT * FROM DB2024_Restaurant WHERE res_name=?";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, res_name);

            rs = pStmt.executeQuery();
            return rs;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    //식당 조회 2. res_id으로 검색
    public ResultSet searchByResId(int res_id) {
        String Q = "SELECT * FROM DB2024_Restaurant WHERE res_id=?";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setInt(1, res_id);

            rs = pStmt.executeQuery();
            return rs;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    //식당 조회 3. cuisine_type으로 검색
    public ResultSet searchByCuisineType(String cuisine_type) {
        String Q = "SELECT * FROM DB2024_Restaurant WHERE cuisine_type=?";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, cuisine_type);

            rs = pStmt.executeQuery();
            return rs;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    // 다양한 조건에 따른 식당 조회 --> 사용자/관리자가 검색조건을 어떻게 조합하느냐에 따라서 조회된 결과값이 달라지도록
    // 위에 3개는 사실상 없어도 될듯
    public ResultSet searchByConditions(Map<String, Object> conditions) {
        // 쿼리를 동적으로 생성하기 위해 StringBuilder 사용
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM DB2024_Restaurant WHERE 1=1");

        // Map에 있는 조건들을 쿼리에 추가
        for (String key : conditions.keySet()) {
            queryBuilder.append(" AND ").append(key).append("=?");
        }

        try {
            // 동적으로 생성된 쿼리로 PreparedStatement 객체 생성
            pStmt = conn.prepareStatement(queryBuilder.toString());

            int index = 1;
            // Map에 있는 조건들의 값을 PreparedStatement에 설정
            for (Map.Entry<String, Object> entry : conditions.entrySet()) {
                if (entry.getValue() instanceof String) {
                    pStmt.setString(index, (String) entry.getValue());
                } else if (entry.getValue() instanceof Integer) {
                    pStmt.setInt(index, (Integer) entry.getValue());
                } else if (entry.getValue() instanceof Float) {
                    pStmt.setFloat(index, (Float) entry.getValue());
                }
                index++;
            }
            // 쿼리 실행
            rs = pStmt.executeQuery();
            return rs;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    //식당 수정
    public int update(DB2024TEAM07_Restaurant restaurant, int pRes_id) {
        String Q = "UPDATE DB2024_Restaurant SET res_name=?, phone_num=?, address=?, operating_hours=?, break_time=?, rating=?, cuisine_type=?, location=? WHERE res_id=?";
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
            pStmt.setInt(10, pRes_id);

            return pStmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return 0;
    }
    //식당 삭제
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

}