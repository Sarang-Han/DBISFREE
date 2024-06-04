package com.manager;

import com.jdbc.database.DB2024TEAM07_Database;
import com.jdbc.database.DB2024TEAM07_ReviewDAO;
import com.jdbc.database.DB2024TEAM07_RatingDAO;
import com.jdbc.model.DB2024TEAM07_Review;
import com.jdbc.model.DB2024TEAM07_UserReview;
import com.jdbc.view.DB2024TEAM07_ReviewVO;

import java.sql.*;
import java.util.*;

public class DB2024TEAM07_ReviewManager {
    private static DB2024TEAM07_ReviewDAO reviewDAO = new DB2024TEAM07_ReviewDAO();
    private static DB2024TEAM07_RatingDAO ratingDAO = new DB2024TEAM07_RatingDAO();
    private static int page;
    private static DB2024TEAM07_Database instance;

    public static void addReview(Scanner scanner) {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Menu ID: ");
        String menuId = scanner.next();

        System.out.print("Enter Rating (1-5): ");
        int rating = scanner.nextInt();

        System.out.print("Enter review comment: ");
        scanner.nextLine();
        String reviewContent = scanner.nextLine();

        Connection conn = null;
        try {
            conn = instance.getConnection();
            conn.setAutoCommit(false);

            DB2024TEAM07_Review review = new DB2024TEAM07_Review(0, userId, menuId, rating, reviewContent);
            int result = reviewDAO.add(review);

            if (result > 0) {
                System.out.println("Review added successfully.");

                int resId = getResIdForMenu(conn, menuId);

                double newAvgRating = ratingDAO.getAvg(resId);
                String updateRatingQuery = "UPDATE DB2024_Restaurant SET rating = ? WHERE restaurant_id = ?";
                try {
                    PreparedStatement pStmt = conn.prepareStatement(updateRatingQuery);
                    pStmt.setDouble(1, newAvgRating);
                    pStmt.setInt(2, resId);
                    pStmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                conn.commit();

            } else {
                System.out.println("Failed to add review.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void updateReview(Scanner scanner) {  //트랜잭션으로 구현
        System.out.print("Enter review ID to update: ");
        int reviewId = scanner.nextInt();

        System.out.print("Enter updated menu ID: ");
        String menuId = scanner.nextLine();

        System.out.print("Enter updated rating (1-5): ");
        int rating = scanner.nextInt();

        System.out.print("Enter updated review content: ");
        scanner.nextLine();
        String reviewContent = scanner.nextLine();

        Connection conn = null;
        try {
            conn = instance.getConnection();
            conn.setAutoCommit(false);

            DB2024TEAM07_Review review = new DB2024TEAM07_Review(reviewId, null, menuId, rating, reviewContent);
            int result = reviewDAO.update(review);

            if (result > 0) {
                System.out.println("Review updated successfully.");

                int resId = getResIdForReview(conn, reviewId);
                double newAvgRating = ratingDAO.getAvg(resId);
                String updateRatingQuery = "UPDATE DB2024_Restaurant SET rating = ? WHERE restaurant_id = ?";
                try {
                    PreparedStatement pStmt = conn.prepareStatement(updateRatingQuery);
                    pStmt.setDouble(1, newAvgRating);
                    pStmt.setInt(2, resId);
                    pStmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                conn.commit();

            } else {
                System.out.println("Failed to update review.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void getCount() {
        int count = reviewDAO.getCount();
        System.out.println("Total number of reviews: " + count);
    }

    public static void getReview(Scanner scanner) {
        System.out.print("Enter page number: ");
        int page = scanner.nextInt();

        ArrayList<DB2024TEAM07_ReviewVO> reviews = reviewDAO.getReview(page);

        System.out.println("=== Reviews ===");
        for (DB2024TEAM07_ReviewVO review : reviews) {
            System.out.println("Review ID: " + review.getReview_id());
            System.out.println("User: " + review.getName());
            System.out.println("Menu name: " + review.getMenu_name());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Review Content: " + review.getReview_content());
            System.out.println();
        }
    }

    public static void getUserCount(Scanner scanner) {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();

        int count = reviewDAO.getUserCount(userId);
        System.out.println("Total number of reviews for user " + userId + ": " + count);
    }

    public static void getUserReview(Scanner scanner) {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter page number: ");
        int page = scanner.nextInt();
        scanner.nextLine();

        ArrayList<DB2024TEAM07_UserReview> userReviews = reviewDAO.getUserReview(page, userId);
        System.out.println("=== User Reviews ===");
        for (DB2024TEAM07_UserReview review : userReviews) {
            System.out.println("Reivew ID: " + review.getReview_id());
            System.out.println("User ID: " + review.getUser_id());
            System.out.println("Menu ID: " + review.getMenu_id());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Review Comment: " + review.getReview_content());
            System.out.println("");
        }
    }

    public static void getResCount(Scanner scanner) {
        System.out.print("Enter restaurant ID: ");
        int resId = scanner.nextInt();

        int count = reviewDAO.getResCount(resId);
        System.out.println("Total number of reviews for restaurant " + resId + ": " + count);
    }

    public static void getResReview(Scanner scanner) {
        System.out.print("Enter restaurant ID: ");
        int resId = scanner.nextInt();

        ArrayList<DB2024TEAM07_ReviewVO> restaurantReviews = reviewDAO.getResReview(page, resId);
        for (DB2024TEAM07_ReviewVO review : restaurantReviews) {
            System.out.println("Review ID: " + review.getReview_id());
            System.out.println("Menu Name: " + review.getMenu_name());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Review Content: " + review.getReview_content());
            System.out.println();
        }
    }

    public static void deleteReview(Scanner scanner) {
        System.out.print("Enter review ID to delete: ");
        int reviewId = scanner.nextInt();

        Connection conn = null;
        try {
            conn = instance.getConnection();
            conn.setAutoCommit(false);

            int result = reviewDAO.delete(reviewId);
            if (result > 0) {
                System.out.println("Review deleted successfully.");
            } else {
                System.out.println("Failed to delete review.");
            }

            String ratingDeleteQuery = "DELETE FROM DB2024_Rating WHERE review_id = ?";
            try {
                PreparedStatement pStmt = conn.prepareStatement(ratingDeleteQuery);
                pStmt.setInt(1, reviewId);
                pStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            int resId = getResIdForReview(conn, reviewId);

            double newAvgRating = ratingDAO.getAvg(resId);
            String updateRatingQuery = "UPDATE DB2024_Restaurant SET rating = ? WHERE restaurant_id = ?";
            try {
                PreparedStatement pStmt = conn.prepareStatement(updateRatingQuery);
                pStmt.setDouble(1, newAvgRating);
                pStmt.setInt(2, resId);
                pStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //리뷰 삭제 시 필요한 레스토랑 ID 리뷰 테이블에서 가져오기
    private static int getResIdForReview(Connection conn, int reviewId) {
        String resIdQuery = "SELECT res_id FROM DB2024_Review WHERE review_id = ?";
        try {
            PreparedStatement pStmt = conn.prepareStatement(resIdQuery);
            pStmt.setInt(1, reviewId);
            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("res_id");
            } else {
                throw new IllegalArgumentException("Review ID not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    //리뷰 추가 시 필요한 레스토랑 ID 메뉴 테이블에서 가져오기
    private static int getResIdForMenu(Connection conn, String menuId) throws SQLException {
        String resIdQuery = "SELECT res_id FROM DB2024_Menu WHERE menu_id = ?";
        PreparedStatement pStmt = conn.prepareStatement(resIdQuery);
        pStmt.setString(1, menuId);
        ResultSet rs = pStmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("res_id");
        } else {
            throw new IllegalArgumentException("Menu ID not found");
        }
    }



    public float getRatingForReview(Connection conn, int reviewId) throws SQLException {
        String Q = "SELECT rating FROM Reviews WHERE review_id = ?";
        try (PreparedStatement pStmt = conn.prepareStatement(Q)) {
            pStmt.setInt(1, reviewId);
            try (ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getFloat("rating");
                }
            }
        }
        throw new SQLException("Failed to retrieve rating for review ID: " + reviewId);
    }


    public static double calculateNewAvgRating(double currentAvgRating, int newRating, int reviewCount) {
        return ((currentAvgRating * reviewCount) + newRating) / (reviewCount + 1);
    }


    public static double getNewAvgRating(Connection conn, int resId, int newRating) throws SQLException {
        double currentAvgRating = ratingDAO.getAvg(resId);
        int reviewCount = reviewDAO.getResCount(resId);
        return calculateNewAvgRating(currentAvgRating, newRating, reviewCount);
    }

    public static float updateRating(Connection conn, int resId, double newAvgRating) throws SQLException {
        String UPDATE_RATING_QUERY = "UPDATE Restaurants SET avg_rating = ? WHERE restaurant_id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_RATING_QUERY)) {
            preparedStatement.setDouble(1, newAvgRating);
            preparedStatement.setInt(2, resId);
            preparedStatement.executeUpdate();
        }
        return 0;
    }
}

