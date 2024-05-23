//Database 파일의 커넥션과의 연결 필요
//error라고 적힌 부분은 조치 필요(고민)-롤백/무시/등

//구현됨: add, 레스토랑 평점 반환,
//구현해야 됨: 특정 유저의 리뷰 몰아보기, 특정 가게의 리뷰 몰아보기

import java.util.ArrayList;
import java.sql.*;

public class RatingDAO{
    private Connection conn;
    private PreparedStatement pStmt;
    private Statement stmt;
    private ResultSet rs;

    public RatingDAO() {
        this.conn = Database.getInstance().getConnection();
    }

    //새 리뷰 작성 시 무조건 호출되어야 함
    //이걸 윗단에서 작성하고 트랜잭션 구현할지 아님 리뷰DAO에서 해결볼지는 고민해봐야 할 것 같아요
    //개인적으로는 위에서 처리하는 게 ㄷㅓ  깔끔할 것 같다고 생각은 합니다
    public int add(int review_id, int res_id){
        String Q = "INSERT INTO DB2024_Rating VALUES (?, ?)";
        try{
            pStmt = conn.preparedStatement(Q);
            pStmt.setInt(1, review_id);
            pStmt.setString(2, res_id);
            return pStmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }

    //레스토랑 평점 반환 기능
    //DB2024_Review 테이블의 투플이 추가/삭제되거나, rating 속성 수정이 이뤄졌을 때 호출되어야 한다.
    //테이블 간 변화 적용이 필요한 부분이므로 트랜잭션 필수
    public float getAvg(int res_id){
        String Q = "SELECT AVG(rating) FROM DB2024_Rating WHERE res_id = ? GROUP BY res_id";
        try{
            pStmt = conn.preparedStatement(Q);
            pStmt.setInt(1, res_id);
            rs = pStmt.executeQuery();
            float ratingAvg = rs.next();
            if(ratingAvg == null){
                return -1;  //res_id is wrong
            }
            return ratingAvg;
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }

    //특정 가게의 리뷰 몰아보기

    //특정 유저의 리뷰 몰아보기


}