//package app;
//
///*
//함수 분리 후 삭제할 파일입니다 - 고은서 -
// */
//
//import java.sql.*;
//import java.util.*;
//
//import jdbc.database.DB2024TEAM07_RestaurantDAO;
//import jdbc.database.DB2024TEAM07_MenuDAO;
//import jdbc.database.DB2024TEAM07_ReviewDAO;
//
//public class DB2024TEAM07_App {
//    private static DB2024TEAM07_RestaurantDAO restaurantDAO = new DB2024TEAM07_RestaurantDAO();
//    private static DB2024TEAM07_MenuDAO menuDAO = new DB2024TEAM07_MenuDAO();
//    private static DB2024TEAM07_ReviewDAO reviewDAO = new DB2024TEAM07_ReviewDAO();
//    private static final Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int choice;
//
//        do {
//            System.out.println("\n=== Management System ===");
//            System.out.println("1. Add Restaurant");
//            System.out.println("2. Add Menu");
//            System.out.println("3. Update Restaurant");
//            System.out.println("4. Update Menu");
//            System.out.println("5. Search Restaurant");
//            System.out.println("6. Search Menu");
//            System.out.println("7. Delete Restaurant");
//            System.out.println("8. Delete Menu");
//            System.out.println("9. Add Review");
//            System.out.println("10. Update Review");
//            System.out.println("11. Delete Review");
//            System.out.println("12. Get Review Count");
//            System.out.println("13. Get Reviews");
//            System.out.println("14. Get User Review Count");
//            System.out.println("15. Get User Reviews");
//            System.out.println("16. Get Restaurant Review Count");
//            System.out.println("17. Get Restaurant Reviews");
//
//            System.out.println("0. Exit");
//            System.out.print("Enter your choice: ");
//            choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    addRestaurant(scanner);
//                    break;
//                case 2:
//                    addMenu(scanner);
//                    break;
//                case 3:
//                    updateRestaurant(scanner);
//                    break;
//                case 4:
//                    updateMenu(scanner);
//                    break;
//                case 5:
//                    searchRestaurant(scanner);
//                    break;
//                case 6:
//                    searchMenu(scanner);
//                    break;
//                case 7:
//                    deleteRestaurant(scanner);
//                    break;
//                case 8:
//                    deleteMenu(scanner);
//                    break;
//                case 9:
//                    addReview();
//                    break;
//                case 10:
//                    updateReview();
//                    break;
//                case 11:
//                    deleteReview();
//                    break;
//                case 12:
//                    getReviewCount();
//                    break;
//                case 13:
//                    getReviews();
//                    break;
//                case 14:
//                    getUserReviewCount();
//                    break;
//                case 15:
//                    getUserReviews();
//                    break;
//                case 16:
//                    getRestaurantReviewCount();
//                    break;
//                case 17:
//                    getRestaurantReviews();
//                    break;
//                case 0:
//                    System.out.println("Exiting...");
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        } while (choice != 0);
//
//        scanner.close();
//    }
//
//
//
//    /* Review Functions - DB2024TEAM07_RestaurantDAO 오류 남 */
//    private static void addReview() {
//        System.out.print("Enter review ID: ");
//        int reviewId = scanner.nextInt();
//        scanner.nextLine();
//        System.out.print("Enter user ID: ");
//        String userId = scanner.nextLine();
//        System.out.print("Enter menu name: ");
//        String menuName = scanner.nextLine();
//        System.out.print("Enter rating: ");
//        int rating = scanner.nextInt();
//        scanner.nextLine();
//        System.out.print("Enter review content: ");
//        String reviewContent = scanner.nextLine();
//
//        DB2024TEAM07_Review review = new DB2024TEAM07_Review(reviewId, userId, menuName, rating, reviewContent);
//
//        int result = reviewDAO.add(review);
//        if (result > 0) {
//            System.out.println("Review added successfully.");
//        } else {
//            System.out.println("Failed to add review.");
//        }
//    }
//
//    private static void updateReview() {
//        System.out.print("Enter review ID to update: ");
//        int reviewId = scanner.nextInt();
//        scanner.nextLine();
//        System.out.print("Enter updated menu name: ");
//        String menuName = scanner.nextLine();
//        System.out.print("Enter updated rating: ");
//        int rating = scanner.nextInt();
//        scanner.nextLine();
//        System.out.print("Enter updated review content: ");
//        String reviewContent = scanner.nextLine();
//
//        DB2024TEAM07_Review review = new DB2024TEAM07_Review(reviewId, null, menuName, rating, reviewContent);
//
//        int result = reviewDAO.update(review);
//        if (result > 0) {
//            System.out.println("Review updated successfully.");
//        } else {
//            System.out.println("Failed to update review.");
//        }
//    }
//
//    private static void deleteReview() {
//        System.out.print("Enter review ID to delete: ");
//        int reviewId = scanner.nextInt();
//
//        int result = reviewDAO.delete(reviewId);
//        if (result > 0) {
//            System.out.println("Review deleted successfully.");
//        } else {
//            System.out.println("Failed to delete review.");
//        }
//    }
//
//    private static void getReviewCount() {
//        int count = reviewDAO.getCount();
//        System.out.println("Total number of reviews: " + count);
//    }
//
//    private static void getReviews() {
//        System.out.print("Enter page number: ");
//        int page = scanner.nextInt();
//
//        ArrayList<DB2024TEAM07_Review> reviews = reviewDAO.getReview(page);
//
//        System.out.println("=== Reviews ===");
//        for (DB2024TEAM07_Review review : reviews) {
//            System.out.println("Review ID: " + review.getReview_id());
//            System.out.println("User ID: " + review.getUser_id());
//            System.out.println("Menu Name: " + review.getMenu_name());
//            System.out.println("Rating: " + review.getRating());
//            System.out.println("Review Content: " + review.getReview_content());
//            System.out.println();
//        }
//    }
//
//    private static void getUserReviewCount() {
//        System.out.print("Enter user ID: ");
//        String userId = scanner.nextLine();
//
//        int count = reviewDAO.getUserCount(userId);
//        System.out.println("Total number of reviews for user " + userId + ": " + count);
//    }
//
//    private static void getUserReviews() {
//        System.out.print("Enter user ID: ");
//        String userId = scanner.nextLine();
//
//        System.out.print("Enter page number: ");
//        int page = scanner.nextInt();
//
//        ArrayList<DB2024TEAM07_UserReview> userReviews = reviewDAO.getUserReview(page, userId);
//
//        System.out.println("=== User Reviews ===");
//        for (DB2024TEAM07_UserReview review : userReviews) {
//            System.out.println("Review ID: " + review.getReview_id());
//            System.out.println("User ID: " + review.getUser_id());
//            System.out.println("Menu Name: " + review.getMenu_name());
//            System.out.println("Rating: " + review.getRating());
//            System.out.println("Review Content: " + review.getReview_content());
//            System.out.println();
//        }
//    }
//
//    private static void getRestaurantReviewCount() {
//        System.out.print("Enter restaurant ID: ");
//        int restaurantId = scanner.nextInt();
//
//        int count = reviewDAO.getResCount(restaurantId);
//        System.out.println("Total number of reviews for restaurant " + restaurantId + ": " + count);
//    }
//
//    private static void getRestaurantReviews() {
//        System.out.print("Enter restaurant ID: ");
//        int restaurantId = scanner.nextInt();
//
//        System.out.print("Enter page number: ");
//        int page = scanner.nextInt();
//
//        ArrayList<DB2024TEAM07_Review> restaurantReviews = reviewDAO.getResReview(page, restaurantId);
//
//        System.out.println("=== Restaurant Reviews ===");
//        for (DB2024TEAM07_Review review : restaurantReviews) {
//            System.out.println("Review ID: " + review.getReview_id());
//            System.out.println("User ID: " + review.getUser_id());
//            System.out.println("Menu Name: " + review.getMenu_name());
//            System.out.println("Rating: " + review.getRating());
//            System.out.println("Review Content: " + review.getReview_content());
//            System.out.println();
//        }
//    }
//}
//
