package com.jdbc.database;

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

import com.jdbc.view.DB2024TEAM07_ResReviewVO;
import com.jdbc.model.DB2024TEAM07_Review;
import com.jdbc.model.DB2024TEAM07_UserReview;

import java.util.ArrayList;
import java.sql.*;

/**
 * This class provides data access object (DAO) methods for interacting with the DB2024_Review and related tables in a database.
 * It manages various functionalities related to user reviews, restaurant reviews, and review management.
 */
public class DB2024TEAM07_ReviewDAO {
    private Connection conn;
    private PreparedStatement pStmt;
    private ResultSet rs;

    /**
     * Constructor that establishes a connection to the database using the DB2024TEAM07_Database class.
     */
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

    /**
     * Adds a new review to the database.
     *
     * @param review the review object containing user ID, rating, and review content
     * @param menuId the ID of the menu associated with the review
     * @param resId the ID of the restaurant associated with the review
     * @return 1 on successful insertion, -1 if the review ID cannot be generated, or -2 on an error
     */
    public int add(DB2024TEAM07_Review review, int menuId, int resId) {
        String reviewQuery = "INSERT INTO DB2024_Review (user_id, rating, review_content) VALUES (?, ?, ?)";
        String mappingQuery = "INSERT INTO DB2024_Review_Menu_Res_Mapping (review_id, menu_id, res_id) VALUES (?, ?, ?)";

        try (PreparedStatement pStmt = conn.prepareStatement(reviewQuery, Statement.RETURN_GENERATED_KEYS)) {
            pStmt.setString(1, review.getUser_id());
            pStmt.setInt(2, review.getRating());
            pStmt.setString(3, review.getReview_content());
            pStmt.executeUpdate();

            // Get the generated review ID
            try (ResultSet generatedKeys = pStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int reviewId = generatedKeys.getInt(1);

                    // Add data to the mapping table
                    try (PreparedStatement pStmtMapping = conn.prepareStatement(mappingQuery)) {
                        pStmtMapping.setInt(1, reviewId);
                        pStmtMapping.setInt(2, menuId);
                        pStmtMapping.setInt(3, resId);
                        pStmtMapping.executeUpdate();
                    }
                } else {
                    return -1;
                }
            }
            return 1;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return -2;
    }

    //리뷰수정 기능(DB2024_Review 테이블의 투플 수정)-----------------------------------------------------------------------
    //리뷰 수정은 로그인 한 회원만 할 수 있게 제한이 필요하다
    //review_id는 절대로 바뀌지 않는 값이므로 유저 업데이트 함수와 달리 기존 아이디 전달이 불필요하다
    /**
     * Updates an existing review in the database.
     *
     * @param review the review object containing updated rating and review content
     * @return the number of rows affected (1 for a successful update) or -2 on an error
     */
    public int update(DB2024TEAM07_Review review){
        String Q = "UPDATE DB2024_Review SET rating=?, review_content=? WHERE review_id=?";
        try{
            pStmt = conn.prepareStatement(Q);
            pStmt.setInt(1, review.getRating());
            pStmt.setString(2, review.getReview_content());
            pStmt.setInt(3, review.getReview_id());
            return pStmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }


    //리뷰 개수 반환(DB2024_Review 테이블의 투플 반환)----------------------------------------------------------------------
    // 이 함수 반환값을 통해 페이지 값 계산하기
    /**
     * Retrieves the total number of reviews in the database.
     *
     * @return the total count of reviews as an integer
     */
    public int getCount() {
        String Q = "SELECT COUNT(*) FROM DB2024_Review";
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
    /**
     * Retrieves a paginated list of reviews from the database, ordered by review ID in descending order.
     *
     * @param page the page number (starting from 1) to retrieve
     * @return a list of `DB2024TEAM07_Review` objects representing the reviews for that page
     */
    public ArrayList<DB2024TEAM07_Review> getReview(int page){
        String Q = "SELECT * FROM DB2024_Review ORDER BY review_id DESC";
        ArrayList<DB2024TEAM07_Review> list = new ArrayList<>();
        try{
            pStmt = conn.prepareStatement(Q,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = pStmt.executeQuery();
            rs.absolute((page-1)*10);
            int i=0;
            while(rs.next() && i<10) {
                DB2024TEAM07_Review review  = new DB2024TEAM07_Review(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)
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
    /**
     * Retrieves the total number of reviews for a specific user.
     *
     * @param user_id the ID of the user
     * @return the total count of reviews for that user as an integer
     */
    public int getUserCount(String user_id) {
        String Q = "SELECT COUNT(*) FROM DB2024_Review WHERE user_id = ?";
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
    /**
     * Retrieves a paginated list of reviews for a specific user, ordered by review ID in descending order.
     *
     * @param page the page number (starting from 1) to retrieve
     * @param user_id the ID of the user
     * @return a list of `DB2024TEAM07_ResReviewVO` objects representing the reviews for that user and page
     */
    public ArrayList<DB2024TEAM07_ResReviewVO> getUserReview(int page, String user_id){
        /*
            SELECT *
            FROM DB2024_Review INNER JOIN DB2024_OtherUser ON (user_id)
            WHERE user_id = ?
            ORDER BY review_id DESC;
         */
        String Q = "SELECT * FROM DB2024_ResReview WHERE user_id= ? ORDER BY review_id DESC";
        ArrayList<DB2024TEAM07_ResReviewVO> userReviews = new ArrayList<>();
        try{
            pStmt = conn.prepareStatement(Q,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pStmt.setString(1, user_id);
            rs = pStmt.executeQuery();
            rs.absolute((page-1)*10);
            int i=0;
            while(rs.next() && i<10) {
                DB2024TEAM07_ResReviewVO review  = new DB2024TEAM07_ResReviewVO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
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
    /**
     * Retrieves the total number of reviews for a specific restaurant.
     *
     * @param res_id the ID of the restaurant
     * @return the total count of reviews for that restaurant as an integer
     */
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
    //프로젝트 요구사항-조인 쿼리, 중첩된 쿼리 사용됨
    /**
     * Retrieves a paginated list of reviews for a specific restaurant, ordered by review ID in descending order.
     *
     * @param page the page number (starting from 1) to retrieve
     * @param res_id the ID of the restaurant
     * @return a list of `DB2024TEAM07_UserReview` objects representing the reviews for that restaurant and page
     */
    public ArrayList<DB2024TEAM07_UserReview> getResReview(int page, int res_id){
        /*  SELECT *
            FROM DB2024_Review r NATURAL JOIN DB2024_OtherUser u
            WHERE review_id IN (SELECT review_id FROM DB2024_Rating WHERE res_id = ?)
            ORDER BY review_id DESC;
        */
        String Q = "SELECT * FROM DB2024_Review r NATURAL JOIN DB2024_OtherUser u WHERE review_id IN (SELECT review_id FROM DB2024_Rating WHERE res_id = ?) ORDER BY review_id DESC";
        ArrayList<DB2024TEAM07_UserReview> restaurantReviews = new ArrayList<>();
        try{
            pStmt = conn.prepareStatement(Q,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pStmt.setInt(1, res_id);
            rs = pStmt.executeQuery();
            rs.absolute((page-1)*10);
            int i=0;
            while(rs.next() && i<10) {
                DB2024TEAM07_UserReview review  = new DB2024TEAM07_UserReview(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
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
    /**
     * Deletes a review from the database.
     *
     * @param review_id the ID of the review to delete
     * @return the number of rows affected (1 for a successful deletion) or -2 on an error
     */
    public int delete(int review_id){
        String Q = "DELETE FROM DB2024_Review WHERE review_id = ?";
        try{
            pStmt = conn.prepareStatement(Q);
            pStmt.setInt(1, review_id);
            int rowsAffected = pStmt.executeUpdate(); // 변경된 부분
            return rowsAffected;
            //rs = pStmt.executeQuery();
            //return pStmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }
}
