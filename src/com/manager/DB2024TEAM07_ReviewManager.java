package com.manager;

import com.jdbc.database.DB2024TEAM07_Database;
import com.jdbc.database.DB2024TEAM07_ReviewDAO;
import com.jdbc.database.DB2024TEAM07_RatingDAO;
import com.jdbc.model.DB2024TEAM07_Review;
import com.jdbc.model.DB2024TEAM07_UserReview;
import com.jdbc.view.DB2024TEAM07_ResReviewVO;

import java.sql.*;
import java.util.*;

/**
 * This class manages review functionalities in the E-MATEASY application.
 * It provides methods for adding, updating, deleting, and retrieving reviews
 * from the database.
 */
public class DB2024TEAM07_ReviewManager {
    private static DB2024TEAM07_ReviewDAO reviewDAO = new DB2024TEAM07_ReviewDAO();
    private static DB2024TEAM07_RatingDAO ratingDAO = new DB2024TEAM07_RatingDAO();
    private static int page;

    /**
     * Adds a new review to the database for a specific restaurant and menu item.
     *
     * @param scanner a Scanner object to read user input
     */
    public static void addReview(Scanner scanner) {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Restaurant ID: ");
        int resId = scanner.nextInt();

        System.out.print("Enter Menu ID: ");
        int menuId = scanner.nextInt();

        System.out.print("Enter Rating (1-5): ");
        int rating = scanner.nextInt();

        System.out.print("Enter review comment: ");
        scanner.nextLine(); // 버퍼 비우기
        String reviewContent = scanner.nextLine();

        Connection conn = null;
        try {
            conn = DB2024TEAM07_Database.getInstance().getConnection();
            conn.setAutoCommit(false);

            DB2024TEAM07_Review review = new DB2024TEAM07_Review(0, userId, rating, reviewContent);
            int result = reviewDAO.add(review, menuId, resId);

            switch (result) {
                case 1:
                    System.out.println("Review added successfully.");

                    int reviewId = review.getReview_id();
                    int ratingResult = ratingDAO.add(reviewId, resId);

                    if (ratingResult > 0) {
                        double newAvgRating = ratingDAO.getAvg(resId);
                        if (newAvgRating >= 0) {
                            String updateRatingQuery = "UPDATE DB2024_Restaurant SET rating = ? WHERE res_id = ?";

                            try (PreparedStatement pStmt = conn.prepareStatement(updateRatingQuery)) {
                                pStmt.setDouble(1, newAvgRating);
                                pStmt.setInt(2, resId);
                                pStmt.executeUpdate();
                                conn.commit();
                                System.out.println("Restaurant rating updated successfully.");
                            }
                        } else {
                            System.out.println("Failed to retrieve new average rating.");
                            conn.rollback();
                        }
                    } else {
                        System.out.println("Failed to add rating.");
                        conn.rollback();
                    }
                    break;
                case -3:
                    System.out.println("Failed to add review.");
                    conn.rollback();
                    break;
                default:
                    System.out.println("Failed to add review due to an unknown error.");
                    conn.rollback();
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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

    /**
     * Updates an existing review in the database.
     *
     * @param scanner a Scanner object to read user input
     */
    public static void updateReview(Scanner scanner) {
        System.out.print("Enter review ID to update: ");
        int reviewId = scanner.nextInt();

        System.out.print("Enter rating (1-5) to update: ");
        int rating = scanner.nextInt();

        System.out.print("Enter review content to update: ");
        scanner.nextLine();
        String reviewContent = scanner.nextLine();

        Connection conn = null;
        try {
            conn = DB2024TEAM07_Database.getInstance().getConnection();
            conn.setAutoCommit(false);

            DB2024TEAM07_Review review = new DB2024TEAM07_Review(reviewId, null,  rating, reviewContent);
            int result = reviewDAO.update(review);

            if (result > 0) {
                System.out.println("Review updated successfully.");

                int resId = getResIdForReview(conn, reviewId);

                double newAvgRating = ratingDAO.getAvg(resId);
                String updateRatingQuery = "UPDATE DB2024_Restaurant SET rating = ? WHERE res_id = ?";
                try {
                    PreparedStatement pStmt = conn.prepareStatement(updateRatingQuery);
                    pStmt.setDouble(1, newAvgRating);
                    pStmt.setInt(2, resId);
                    pStmt.executeUpdate();
                    System.out.println("Restaurant rating updated successfully.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                conn.commit();

            } else {
                System.out.println("Failed to update review.");
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
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

    /**
     * Retrieves the total number of reviews in the database.
     */
    public static void getCount() {
        int count = reviewDAO.getCount();
        System.out.println("Total number of reviews: " + count);
    }

    /**
     * Retrieves a page of reviews from the database.
     *
     * @param scanner a Scanner object to read user input
     */
    public static void getReview(Scanner scanner) {
        System.out.print("Enter page number: ");
        int page = scanner.nextInt();

        ArrayList<DB2024TEAM07_Review> reviews = reviewDAO.getReview(page);

        System.out.printf("%-10s%-18s%-10s%-100s%n", "Review ID", "User ID", "Rating", "Review Content");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        for (DB2024TEAM07_Review review : reviews) {
            System.out.printf("%-10d%-20s%-9d%s%n",
                    review.getReview_id(),
                    review.getUser_id(),
                    review.getRating(),
                    review.getReview_content());
        }

    }

    /**
     * Retrieves the total number of reviews for a specific user.
     *
     * @param scanner a Scanner object to read user input
     */
    public static void getUserCount(Scanner scanner) {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();

        int count = reviewDAO.getUserCount(userId);
        System.out.println("Total number of reviews for user " + userId + ": " + count);
    }

    /**
     * Retrieves a page of reviews for a specific user from the database.
     *
     * @param scanner a Scanner object to read user input
     */
    public static void getUserReview(Scanner scanner) {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter page number: ");
        int page = scanner.nextInt();
        scanner.nextLine();

        ArrayList<DB2024TEAM07_ResReviewVO> userReviews = reviewDAO.getUserReview(page, userId);
        System.out.printf("%-10s%-15s%-20s%-15s%-100s%n", "Review ID", "User ID", "Restaurant", "Rating", "Review Comment");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        for (DB2024TEAM07_ResReviewVO review : userReviews) {
            System.out.printf("%-10d%-15s%-20s%-10d%-60s%n",
                    review.getReview_id(),
                    review.getUser_id(),
                    review.getRes_name(),
                    review.getRating(),
                    review.getReview_content());
        }

    }

    /**
     * Retrieves the total number of reviews for a specific restaurant.
     *
     * @param scanner a Scanner object to read user input
     */
    public static void getResCount(Scanner scanner) {
        System.out.print("Enter restaurant ID: ");
        int resId = scanner.nextInt();

        int count = reviewDAO.getResCount(resId);
        System.out.println("Total number of reviews for restaurant " + resId + ": " + count);
    }

    /**
     * Retrieves a page of reviews for a specific restaurant from the database.
     *
     * @param scanner a Scanner object to read user input
     */
    public static void getResReview(Scanner scanner) {
        System.out.print("Enter restaurant ID: ");
        int resId = scanner.nextInt();

        ArrayList<DB2024TEAM07_UserReview> restaurantReviews = reviewDAO.getResReview(page, resId);
        System.out.printf("%-10s%-15s%-15s%-10s%-100s%n", "Review ID", "User ID", "User Name", "Rating", "Review Content");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        for (DB2024TEAM07_UserReview review : restaurantReviews) {
            System.out.printf("%-10d%-15s%-13s%-10d%-60s%n",
                    review.getReview_id(),
                    review.getUser_id(),
                    review.getName(),
                    review.getRating(),
                    review.getReview_content());
        }

    }

    /**
     * Deletes a review from the database.
     *
     * @param scanner a Scanner object to read user input
     */
    public static void deleteReview(Scanner scanner) {
        System.out.print("Enter review ID to delete: ");
        int reviewId = scanner.nextInt();

        Connection conn = null;
        try {
            conn = DB2024TEAM07_Database.getInstance().getConnection();
            conn.setAutoCommit(false);

            int result = reviewDAO.delete(reviewId);
            if (result > 0) {
                System.out.println("Review deleted successfully.");

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
                String updateRatingQuery = "UPDATE DB2024_Restaurant SET rating = ? WHERE res_id = ?";
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
                System.out.println("Failed to delete review.");
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
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

    /**
     * Helper method to retrieve the restaurant ID associated with a review from the database.
     *
     * @param conn a Connection object representing the database connection
     * @param reviewId the ID of the review
     * @return the restaurant ID or -1 if not found
     */
    private static int getResIdForReview(Connection conn, int reviewId) {
        String resIdQuery = "SELECT res_id FROM DB2024_Rating WHERE review_id = ?";
        try {
            PreparedStatement pStmt = conn.prepareStatement(resIdQuery);
            pStmt.setInt(1, reviewId);
            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("res_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}