package manager;

import jdbc.database.DB2024TEAM07_Database;
import jdbc.database.DB2024TEAM07_ReviewDAO;
import jdbc.model.DB2024TEAM07_Review;
import jdbc.model.DB2024TEAM07_UserReview;
import java.util.*;

public class DB2024TEAM07_ReviewManager {
    private static DB2024TEAM07_ReviewDAO reviewDAO = new DB2024TEAM07_ReviewDAO();
    private static void addReview(Scanner sc) {
        System.out.print("Enter User ID: ");
        String userId = sc.next();

        System.out.print("Enter Menu ID: ");
        String menuId = sc.next();

        System.out.print("Enter Rating (1-5): ");
        int rating = sc.nextInt();

        System.out.print("Enter review comment: ");
        sc.nextLine();
        String reviewContent = sc.nextLine();

        DB2024TEAM07_Review review = new DB2024TEAM07_Review(0, userId, menuId, rating, reviewContent);
        int result = reviewDAO.add(review);

        if (result > 0) {
            System.out.println("Review updated successfully.");
        } else {
            System.out.println("Failed to update review.");
        }
    }

    private static void updateReview(DB2024TEAM07_ReviewDAO reviewDAO, Scanner sc) {
        System.out.print("Enter review ID to update: ");
        int reviewId = sc.nextInt();
        System.out.print("Enter updated menu ID: ");
        String menuId = sc.next();
        System.out.print("Enter updated rating (1-5): ");
        int rating = sc.nextInt();
        System.out.print("Enter updated review content: ");
        sc.nextLine();
        String reviewContent = sc.nextLine();

        DB2024TEAM07_Review review = new DB2024TEAM07_Review(reviewId, null, menuId, rating, reviewContent);
        int result = reviewDAO.update(review);
        if (result > 0) {
            System.out.println("Review updated successfully.");
        } else {
            System.out.println("Failed to update review.");
        }
    }

    private static void getReviewCount() {
        int count = reviewDAO.getCount();
        System.out.println("Total number of reviews: " + count);
    }

    private static void getReviews(Scanner sc) {
        System.out.print("Enter page number: ");
        int page = sc.nextInt();

        ArrayList<DB2024TEAM07_Review> reviews = reviewDAO.getReview(page);

        System.out.println("=== Reviews ===");
        for (DB2024TEAM07_Review review : reviews) {
            System.out.println("Review ID: " + review.getReview_id());
            System.out.println("User ID: " + review.getUser_id());
            System.out.println("Menu ID: " + review.getMenu_id());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Review Content: " + review.getReview_content());
            System.out.println();
        }
    }

    private static void getUserReviewCount(Scanner sc) {
        System.out.print("Enter user ID: ");
        String userId = sc.nextLine();

        int count = reviewDAO.getUserCount(userId);
        System.out.println("Total number of reviews for user " + userId + ": " + count);
    }

    private static void getUserReviews(DB2024TEAM07_ReviewDAO reviewDAO, Scanner sc) {
        System.out.print("Enter user ID: ");
        String userId = sc.next();
        System.out.print("Enter page number: ");
        int page = sc.nextInt();

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

    private static void getRestaurantReviewCount(Scanner sc) {
        System.out.print("Enter restaurant ID: ");
        int restaurantId = sc.nextInt();

        int count = reviewDAO.getResCount(restaurantId);
        System.out.println("Total number of reviews for restaurant " + restaurantId + ": " + count);
    }

    private static void getRestaurantReviews(DB2024TEAM07_ReviewDAO dao, Scanner sc) {
        System.out.print("Enter restaurant ID: ");
        int resId = sc.nextInt();

        ArrayList<jdbc.view.DB2024TEAM07_ReviewVO> restaurantReviews = reviewDAO.getResReview(page, resId);
        for (jdbc.view.DB2024TEAM07_ReviewVO review : restaurantReviews) {
            System.out.println("Review ID: " + review.getReview_id());
            System.out.println("User ID: " + review.getUser_id());
            System.out.println("Menu Name: " + review.getMenu_name());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Review Content: " + review.getReview_content());
            System.out.println();
        }
    }

    private static void deleteReview(DB2024TEAM07_ReviewDAO reviewDAO, Scanner sc) {
        System.out.print("Enter review ID to delete: ");
        int reviewId = sc.nextInt();

        int result = reviewDAO.delete(reviewId);
        if (result > 0) {
            System.out.println("Review deleted successfully.");
        } else {
            System.out.println("Failed to delete review.");
        }
    }

}
