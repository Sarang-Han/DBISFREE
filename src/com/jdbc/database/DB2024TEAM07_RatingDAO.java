package com.jdbc.database;

/* 구현된 기능
    가게에 대한 리뷰 등록:   add(int review_id, int res_id)
    레스토랑 평균 평점 반환:  getAvg(int res_id)
*/

import java.sql.*;

/**
 * This class provides data access object (DAO) methods for interacting with the DB2024_Rating and DB2024_Review tables in a database.
 * It handles operations related to restaurant reviews and ratings.
 */
public class DB2024TEAM07_RatingDAO{
    private Connection conn;
    private PreparedStatement pStmt;
    private Statement stmt;
    private ResultSet rs;

    /**
     * Constructor that establishes a connection to the database using the DB2024TEAM07_Database class.
     */
    public DB2024TEAM07_RatingDAO() {
        this.conn = DB2024TEAM07_Database.getInstance().getConnection();
    }

    //가게에 대한 리뷰 등록 -----------------------------------------------------------------------
    //새 리뷰 작성 시 무조건 호출되어야 함
    //이걸 서비스 단에서 트랜잭션 구현할지 아님 DAO에서 해결볼지는 고민해봐야 할 것 같아요
    //개인적으로는 위에서 처리하는 게 더 깔끔할 것 같다고 생각은 합니다
    /**
     * Adds a new review to the DB2024_Rating table.
     *
     * @param review_id the ID of the review
     * @param res_id the ID of the restaurant
     * @return the number of rows affected (usually 1 for a successful insert)
     */
    public int add(int review_id, int res_id){
        String Q = "INSERT INTO DB2024_Rating VALUES (?, ?)";
        try{
            pStmt = conn.prepareStatement(Q);
            pStmt.setInt(1, review_id);
            pStmt.setInt(2, res_id);
            return pStmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }

    //레스토랑 평점 반환 기능-----------------------------------------------------------------------
    //DB2024_Review 테이블의 투플이 추가/삭제되거나, rating 속성 수정이 이뤄졌을 때 호출되어야 한다.
    //테이블 간 변화 적용이 필요한 부분이므로 트랜잭션 필수
    //인덱스 힌트(DB2024_idx_AvgRating)
    //중첩 쿼리 이용됨
    /**
     * Calculates and returns the average rating for a specific restaurant.
     *
     * @param res_id the ID of the restaurant
     * @return the average rating as a float value
     */
    public float getAvg(int res_id){
        /*
        SELECT AVG(r.rating)
        FROM DB2024_Review r
        INNER JOIN DB2024_Rating ra
        USE INDEX (DB2024_idx_AvgRating) ON r.review_id = ra.review_id
        WHERE ra.res_id = ?
         */

        String Q = "SELECT AVG(r.rating) FROM DB2024_Review r INNER JOIN DB2024_Rating ra USE INDEX (DB2024_idx_AvgRating) ON r.review_id = ra.review_id WHERE ra.res_id = ?";
        try{
            pStmt = conn.prepareStatement(Q);
            pStmt.setInt(1, res_id);
            rs = pStmt.executeQuery();
            if(rs.next()){
                return rs.getFloat(1);
            }
            return -1;   //res_id is wrong
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }
}
