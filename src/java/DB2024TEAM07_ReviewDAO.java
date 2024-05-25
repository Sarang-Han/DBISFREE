//Database 파일의 커넥션과의 연결 필요
//error라고 적힌 부분은 조치 필요(고민)-롤백/무시/등

//구현됨: 리뷰 작성, 리뷰 수정, 리뷰 삭제
//구현됨??: 리뷰 리스트 반환(안 쓰일 시 삭제될 수도 있다)

import java.util.ArrayList;
import java.sql.*;

public class DB2024TEAM07_ReviewDAO {
    private Connection conn;
    private PreparedStatement pStmt;
    private Statement stmt;
    private ResultSet rs;

    public DB2024TEAM07_ReviewDAO() {
        this.conn = Database.getInstance().getConnection();
    }

    //리뷰작성 기능(DB2024_Review 테이블에 투플 삽입)
    //리뷰 작성은 로그인 한 회원만 할 수 있게 제한이 필요하다
    /*리뷰를 삭제하는 경우,
    1. Rating 테이블의, 연관된 투플 삽입과(RatingDAO.add())
    2. Restaurant 테이블의, 연관된 투플의 rating값 변경이 필요하다(RatingDAO.getAVG()이용)
    현재 각각의 테이블 쓰기 기능이 함수로 구현되어 있으므로,
    윗단에서 해당 함수를 수행 시 꼭!! 트랜잭션 처리를 하는 것이 요구된다
    만약에 구현하다가 잘 모르겠으면 저한테 연락 주세용.-김민서-
    */
    public int add(DB2024TEAM07_Review review){
        String Q = "INSERT INTO DB2024_Review VALUES (?, ?, ?, ?, ?)";
        try{
            pStmt = conn.preparedStatement(Q);
            pStmt.setInt(1, review.getReview_id());
            pStmt.setString(2, review.getUser_id);
            pStmt.setString(3, review.getMenu_name());
            pStmt.setInt(4, review.getRating());
            pStmt.setString(5, review.getReview_content());
            return pStmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }

    //리뷰수정 기능(DB2024_Review 테이블의 투플 수정)
    //리뷰 수정은 로그인 한 회원만 할 수 있게 제한이 필요하다
    //review_id는 절대로 바뀌지 않는 값이므로 유저 업데이트 함수와 달리 기존 아이디 전달이 불필요하다
    public int update(DB2024TEAM07_Review review){
        String Q = "UPDATE DB2024_Review SET menu_name=?, rating=?, review_content=? WHERE review_id=?";
        try{
            pStmt = conn.preparedStatement(Q);
            pStmt.setString(1, review.getMenu_name());
            pStmt.setInt(2, review.getRating());
            pStmt.setString(3, review.getReview_content());
            pStmt.setString(4, review.getReview_id());
            return pStmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }

    //리뷰값 반환(DB2024_Review 테이블의 투플 반환)
    //일단은 최신순, 10개 단위로 반환하는 형태 선택
        //1. 존재하는 것중 가장 큰 review_id를 반환하는 함수
    public int getNext() {
        String Q = "SELECT review_id FROM DB2024_Review ORDER BY review_id DESC";
        try{
            pStmt = conn.prepareStatement(Q);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
            return 1;   //아무 리뷰도 없는 상태
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        return -2;      //error
    }
        //2. ArrayList<Review>형식으로 반환
        //page는 가장 최신 값이 보이는 페이지가 1이라는 가정 하에 작성됨
    public ArrayList<DB2024TEAM07_Review> getReview(int page){
        String Q = "SELECT * FROM DB2024_Review WHERE review_id < ? ORDER BY review_id DESC LIMIT 10";
        ArrayList<DB2024TEAM07_Review> list = new ArrayList<>();
        try{
            pStmt = conn.prepareStatement(Q);
            pstmt.setInt(1, getNext() - (page-1)*10);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                DB2024TEAM07_Review review  = new DB2024TEAM07_Review(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                );
                list.add(ㄱeview);
            }
        }
        catch(SQLException se) {
            e.printStackTrace();
        }
        return list;
    }

    //리뷰 삭제 기능(DB2024_Review 테이블의 투플 삭제)
    //삭제 전에 삭제를 확인하는 것이 필수
    /*리뷰를 삭제하는 경우,
    1. Rating 테이블의, 연관된 투플 삭제와(sql단에서 자동으로 처리됨)
    2. Restaurant 테이블의, 연관된 투플의 rating값 변경이 필요하다(RatingDAO.getAVG()이용)
    현재 각각의 테이블 쓰기 기능이 함수로 구현되어 있으므로,
    윗단에서 해당 함수를 수행 시 꼭!! 트랜잭션 처리를 하는 것이 요구된다
    만약에 구현하다가 잘 모르겠으면 저한테 연락 주세용.-김민서-
    */
    public int delete(int review_id){
        String Q = "DELETE FROM DB2024_Review WHERE review_id = ?";
        try{
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, review_id);
            rs = pStmt.executeQuery();
            return pStmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }
}
