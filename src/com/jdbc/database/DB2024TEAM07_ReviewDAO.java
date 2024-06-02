package com.jdbc.database;
//Database 파일의 커넥션과의 연결 필요
//error라고 적힌 부분은 조치 필요(고민)-롤백/무시/등

/* 구현된 기능
    리뷰 작성:  add(DB2024TEAM07_Review review)
    리뷰 수정:  update(DB2024TEAM07_Review review)
    리뷰 개수 반환:    getCount()
    리뷰 리스트 반환:  getReview(int page)
    특정 유저의 리뷰 개수 반환:    getUserCount(String user_id)
    특정 유저의 리뷰 몰아보기:     getUserReview(int page, String user_id)
    특정 가게의 리뷰 개수 반환:    getResCount(int res_id)
    특정 가게의 리뷰 몰아보기:     getResReview(int page, String res_id)
    리뷰 삭제:  delete(int review_id)
 */


import com.jdbc.view.DB2024TEAM07_ReviewVO;
import com.jdbc.model.DB2024TEAM07_Review;
import com.jdbc.model.DB2024TEAM07_UserReview;

import java.util.ArrayList;
import java.sql.*;

public class DB2024TEAM07_ReviewDAO {
    private Connection conn;
    private PreparedStatement pStmt;
    private ResultSet rs;

    public DB2024TEAM07_ReviewDAO() {
        this.conn = DB2024TEAM07_Database.getInstance().getConnection();
    }

    //리뷰작성 기능(DB2024_Review 테이블에 투플 삽입)-----------------------------------------------------------------------
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
            pStmt = conn.prepareStatement(Q);
            pStmt.setInt(1, review.getReview_id());
            pStmt.setString(2, review.getUser_id());
            pStmt.setString(3, review.getMenu_id());
            pStmt.setInt(4, review.getRating());
            pStmt.setString(5, review.getReview_content());
            return pStmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }

    //리뷰수정 기능(DB2024_Review 테이블의 투플 수정)-----------------------------------------------------------------------
    //리뷰 수정은 로그인 한 회원만 할 수 있게 제한이 필요하다
    //review_id는 절대로 바뀌지 않는 값이므로 유저 업데이트 함수와 달리 기존 아이디 전달이 불필요하다
    public int update(DB2024TEAM07_Review review){
        String Q = "UPDATE DB2024_Review SET menu_id=?, rating=?, review_content=? WHERE review_id=?";
        try{
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, review.getMenu_id());
            pStmt.setInt(2, review.getRating());
            pStmt.setString(3, review.getReview_content());
            pStmt.setInt(4, review.getReview_id());
            return pStmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }


    //리뷰 개수 반환(DB2024_Review 테이블의 투플 반환)----------------------------------------------------------------------
    // 이 함수 반환값을 통해 페이지 값 계산하기
    public int getCount() {
        String Q = "SELECT COUNT(*) FROM DB2024_viewReview";
        try{
            pStmt = conn.prepareStatement(Q);
            rs = pStmt.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
            return 0;   //아무 리뷰도 없는 상태
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        return -2;      //error
    }

    //리뷰값 반환(DB2024_Review 테이블의 투플 반환)-----------------------------------------------------------------------
    //일단은 최신순, 10개 단위로 반환하는 형태 선택
    //ArrayList<Review>형식으로 반환
    //page는 가장 최신 값이 보이는 페이지가 1이라는 가정 하에 작성됨
    public ArrayList<DB2024TEAM07_ReviewVO> getReview(int page){
        String Q = "SELECT * FROM DB2024_viewReview ORDER BY review_id DESC";
        ArrayList<DB2024TEAM07_ReviewVO> list = new ArrayList<>();
        try{
            pStmt = conn.prepareStatement(Q,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = pStmt.executeQuery();
            rs.absolute((page-1)*10);
            int i=0;
            while(rs.next() && i<10) {
                DB2024TEAM07_ReviewVO review  = new DB2024TEAM07_ReviewVO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                );
                list.add(review);
                i++;
            }
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        return list;
    }


    //특정 유저의 리뷰 개수 반환(DB2024_Rating 테이블의 투플 반환)----------------------------------------------------------------------
    // 이 함수 반환값을 통해 페이지 값 계산하기
    public int getUserCount(String user_id) {
        String Q = "SELECT COUNT(*) FROM DB2024_Rating WHERE user_id = ?";
        try{
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, user_id);
            rs = pStmt.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
            return 0;   //아무 리뷰도 없는 상태
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        return -2;      //error
    }

    //특정 유저의 리뷰 몰아보기 기능(DB2024_Review 테이블의 투플 반환)-----------------------------------------------------------------------
    //일단은 최신순, 10개 단위로 반환하는 형태 선택
    //프로젝트 요구사항-조인 쿼리 사용됨
    public ArrayList<DB2024TEAM07_UserReview> getUserReview(int page, String user_id){
        /*
            SELECT *
            FROM DB2024_Review INNER JOIN DB2024_OtherUser ON (user_id)
            WHERE user_id = ?
            ORDER BY review_id DESC;
         */
        String Q = "SELECT * FROM DB2024_Review NATURAL JOIN DB2024_OtherUser WHERE user_id= ? ORDER BY review_id DESC";
        ArrayList<DB2024TEAM07_UserReview> userReviews = new ArrayList<>();
        try{
            pStmt = conn.prepareStatement(Q,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pStmt.setString(1, user_id);
            rs = pStmt.executeQuery();
            rs.absolute((page-1)*10);
            int i=0;
            while(rs.next() && i<10) {
                DB2024TEAM07_UserReview review  = new DB2024TEAM07_UserReview(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );
                userReviews.add(review);
                i++;
            }
        }catch(SQLException se) {
            se.printStackTrace();
        }
        return userReviews;
    }

    //특정 가게의 리뷰 개수 반환(DB2024_Rating 테이블의 투플 반환)----------------------------------------------------------------------
    // 이 함수 반환값을 통해 페이지 값 계산하기
    public int getResCount(int res_id) {
        String Q = "SELECT COUNT(*) FROM DB2024_Rating WHERE res_id = ?";
        try{
            pStmt = conn.prepareStatement(Q);
            pStmt.setInt(1, res_id);
            rs = pStmt.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
            return 0;   //아무 리뷰도 없는 상태
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        return -2;      //error
    }

    //특정 가게의 리뷰 몰아보기 기능(DB2024_Review 테이블의 투플 반환)-----------------------------------------------------------------------
    //일단은 최신순, 10개 단위로 반환하는 형태 선택
    //프로젝트 요구사항-중첩된 쿼리 사용됨
    public ArrayList<DB2024TEAM07_ReviewVO> getResReview(int page, int res_id){
        /*  SELECT *
            FROM DB2024_Review
            WHERE review_id IN (SELECT review_id
                                FROM DB2024_Rating
                                WHERE res_id = ?)
            ORDER BY review_id DESC;
        */
        String Q = "SELECT * FROM DB2024_ReviewVO WHERE review_id IN (SELECT review_id FROM DB2024_Rating WHERE res_id = ?) ORDER BY review_id DESC";
        ArrayList<DB2024TEAM07_ReviewVO> restaurantReviews = new ArrayList<>();
        try{
            pStmt = conn.prepareStatement(Q,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pStmt.setInt(1, res_id);
            rs = pStmt.executeQuery();
            rs.absolute((page-1)*10);
            int i=0;
            while(rs.next() && i<10) {
                DB2024TEAM07_ReviewVO review  = new DB2024TEAM07_ReviewVO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                );
                restaurantReviews.add(review);
                i++;
            }
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        return restaurantReviews;
    }

    //리뷰 삭제 기능(DB2024_Review 테이블의 투플 삭제)-----------------------------------------------------------------------
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
            pStmt.setInt(1, review_id);
            rs = pStmt.executeQuery();
            return pStmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }
}