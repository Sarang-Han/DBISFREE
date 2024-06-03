package com.jdbc.database;
//Database 파일의 커넥션과의 연결 필요
//error라고 적힌 부분은 조치 필요(고민)-롤백/무시/등

/* 구현된 기능
    가게에 대한 리뷰 등록:   add(int review_id, int res_id)
    레스토랑 평균 평점 반환:  getAvg(int res_id)
*/

import java.sql.*;

public class DB2024TEAM07_RatingDAO{
    private Connection conn;
    private PreparedStatement pStmt;
    private Statement stmt;
    private ResultSet rs;

    public DB2024TEAM07_RatingDAO() {
        this.conn = DB2024TEAM07_Database.getInstance().getConnection();
    }

    //가게에 대한 리뷰 등록 -----------------------------------------------------------------------
    //새 리뷰 작성 시 무조건 호출되어야 함
    //이걸 서비스 단에서 트랜잭션 구현할지 아님 DAO에서 해결볼지는 고민해봐야 할 것 같아요
    //개인적으로는 위에서 처리하는 게 더 깔끔할 것 같다고 생각은 합니다
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
    public float getAvg(int res_id){
        /*
        SELECT AVG(rating)
        FROM DB2024_Review
        WHERE review_id =
            (SELECT review_id
            FROM DB2024_Rating DB2024_Rating USE INDEX(DB2024_idx_AvgRating)
            WHERE res_id = ?)
         */
        String Q = "SELECT AVG(rating) FROM DB2024_Review WHERE review_id = (SELECT review_id FROM DB2024_Rating DB2024_Rating USE INDEX(DB2024_idx_AvgRating) WHERE res_id = ?)";
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
