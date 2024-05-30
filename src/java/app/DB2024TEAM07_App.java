package app;

/*
함수 분리 후 삭제할 파일입니다 - 고은서 -
 */

import java.sql.*;
import java.util.*;

import jdbc.database.DB2024TEAM07_RestaurantDAO;
import jdbc.database.DB2024TEAM07_MenuDAO;
import jdbc.database.DB2024TEAM07_ReviewDAO;
import jdbc.model.DB2024TEAM07_Menu;
import jdbc.model.DB2024TEAM07_Restaurant;
import jdbc.model.DB2024TEAM07_Review;

public class DB2024TEAM07_App {
    private static DB2024TEAM07_RestaurantDAO restaurantDAO = new DB2024TEAM07_RestaurantDAO();
    private static DB2024TEAM07_MenuDAO menuDAO = new DB2024TEAM07_MenuDAO();
    private static DB2024TEAM07_ReviewDAO reviewDAO = new DB2024TEAM07_ReviewDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Management System ===");
            System.out.println("1. Add Restaurant");
            System.out.println("2. Add Menu");
            System.out.println("3. Update Restaurant");
            System.out.println("4. Update Menu");
            System.out.println("5. Search Restaurant");
            System.out.println("6. Search Menu");
            System.out.println("7. Delete Restaurant");
            System.out.println("8. Delete Menu");
            System.out.println("9. Add Review");
            System.out.println("10. Update Review");
            System.out.println("11. Delete Review");
            System.out.println("12. Get Review Count");
            System.out.println("13. Get Reviews");
            System.out.println("14. Get User Review Count");
            System.out.println("15. Get User Reviews");
            System.out.println("16. Get Restaurant Review Count");
            System.out.println("17. Get Restaurant Reviews");

            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addRestaurant(scanner);
                    break;
                case 2:
                    addMenu(scanner);
                    break;
                case 3:
                    updateRestaurant(scanner);
                    break;
                case 4:
                    updateMenu(scanner);
                    break;
                case 5:
                    searchRestaurant(scanner);
                    break;
                case 6:
                    searchMenu(scanner);
                    break;
                case 7:
                    deleteRestaurant(scanner);
                    break;
                case 8:
                    deleteMenu(scanner);
                    break;
                case 9:
                    addReview();
                    break;
                case 10:
                    updateReview();
                    break;
                case 11:
                    deleteReview();
                    break;
                case 12:
                    getReviewCount();
                    break;
                case 13:
                    getReviews();
                    break;
                case 14:
                    getUserReviewCount();
                    break;
                case 15:
                    getUserReviews();
                    break;
                case 16:
                    getRestaurantReviewCount();
                    break;
                case 17:
                    getRestaurantReviews();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }


    /* Add Functions */
    private static void addRestaurant(Scanner scanner) {

        System.out.print("Enter Restaurant Name: ");
        String res_name = scanner.nextLine();

        System.out.print("Enter Restaurant ID: ");
        int res_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone_num = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Operating Hours: ");
        String operating_hours = scanner.nextLine();

        System.out.print("Enter Break Time: ");
        String break_time = scanner.nextLine();

        System.out.print("Enter Rating (choose from 1 to 5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Cuisine Type: ");
        String cuisine_type = scanner.nextLine();

        System.out.print("Enter Location: ");
        String location = scanner.nextLine();

        DB2024TEAM07_Restaurant restaurant = new DB2024TEAM07_Restaurant(
                res_name, res_id, phone_num,
                address, operating_hours, break_time,
                rating, cuisine_type, location);
        int result = restaurantDAO.add(restaurant);

        if (result > 0) {
            System.out.println("Restaurant added successfully.");
        } else {
            System.out.println("Error adding restaurant.");
        }
    }

    private static void addMenu(Scanner scanner) {
        System.out.print("Enter Restaurant ID: ");
        int res_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Menu Name: ");
        String menu_name = scanner.nextLine();

        System.out.print("Enter Menu ID: ");
        int menu_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Price: ");
        int price = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Menu Comment: ");
        String menu_comment = scanner.nextLine();

        DB2024TEAM07_Menu menu = new DB2024TEAM07_Menu(res_id, menu_name, menu_id, price, menu_comment);
        int result = menuDAO.add(menu);

        if (result > 0) {
            System.out.println("Menu added successfully.");
        } else {
            System.out.println("Error adding menu.");
        }
    }

    /* Update Functions */
    private static void updateRestaurant(Scanner scanner) {
        System.out.print("Enter Restaurant ID: ");
        int res_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Restaurant Name: ");
        String res_name = scanner.nextLine();

        System.out.print("Enter New Phone Number: ");
        String phone_num = scanner.nextLine();

        System.out.print("Enter New Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter New Operating Hours: ");
        String operating_hours = scanner.nextLine();

        System.out.print("Enter New Break Time: ");
        String break_time = scanner.nextLine();

        System.out.print("Enter New Rating: ");
        int rating = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Cuisine Type: ");
        String cuisine_type = scanner.nextLine();

        System.out.print("Enter New Location: ");
        String location = scanner.nextLine();

        DB2024TEAM07_Restaurant updatedRestaurant = new DB2024TEAM07_Restaurant(
                res_name, res_id, phone_num, address, operating_hours, break_time, rating, cuisine_type, location);
        int updateRestaurantResult = restaurantDAO.update(updatedRestaurant, res_id);
        if (updateRestaurantResult > 0) {
            System.out.println("Restaurant updated successfully.");
        } else {
            System.out.println("Failed to update restaurant.");
        }
    }

    private static void updateMenu(Scanner scanner) {
        System.out.print("Enter Restaurant ID: ");
        int res_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Menu ID: ");
        int menu_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Menu Name: ");
        String newMenuName = scanner.nextLine();

        System.out.print("Enter New Price: ");
        int newPrice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Menu Comment: ");
        String newMenuComment = scanner.nextLine();

        DB2024TEAM07_Menu updatedMenu = new DB2024TEAM07_Menu(
                res_id, newMenuName, menu_id, newPrice, newMenuComment);
        int result = menuDAO.update(updatedMenu, res_id, menu_id);

        if (result > 0) {
            System.out.println("Menu updated successfully.");
        } else {
            System.out.println("Error updating menu.");
        }
    }

    /* Search Functions */
    private static void searchRestaurant(Scanner scanner) {
        System.out.print("Enter Restaurant Name: ");
        String res_name = scanner.nextLine();

        System.out.print("Enter Cuisine Type: ");
        String cuisine_type = scanner.nextLine();

        System.out.print("Enter Location: ");
        String location = scanner.nextLine();

        System.out.print("Enter Minimum Rating (optional): ");
        Float rating = null;
        String ratingInput = scanner.nextLine();
        if (!ratingInput.isEmpty()) {
            rating = Float.parseFloat(ratingInput);
        }

        List<DB2024TEAM07_Restaurant> restaurants = restaurantDAO.search(res_name, cuisine_type, location, rating);
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants found matching the criteria.");
        } else {
            System.out.println("Found restaurants:");
            for (DB2024TEAM07_Restaurant restaurant : restaurants) {
                System.out.println(restaurant);
            }
        }
    }

    private static void searchMenu(Scanner scanner) {
        System.out.print("Enter Restaurant Name: ");
        String restaurantName = scanner.nextLine();

        System.out.print("Enter Menu Name: ");
        String menuName = scanner.nextLine();

        System.out.print("Enter Minimum Price: ");
        int minPrice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Maximum Price: ");
        int maxPrice = scanner.nextInt();
        scanner.nextLine();

        try (ResultSet Result = menuDAO.searchByUsers(restaurantName, menuName, minPrice, maxPrice)) {
            if (Result != null && Result.next()) {
                System.out.println("Menu found by search:");
                do {
                    System.out.println("Menu Name: " + Result.getString("menu_name"));
                    System.out.println("Price: " + Result.getInt("price"));
                    System.out.println("Comment: " + Result.getString("menu_comment"));
                } while (Result.next());
            } else {
                System.out.println("No menu found matching user search criteria.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Delete Functions */
    private static void deleteRestaurant(Scanner scanner) {
        System.out.print("Enter Restaurant ID: ");
        int res_id = scanner.nextInt();
        scanner.nextLine();

        int deleteRestaurantResult = restaurantDAO.delete(res_id);
        if (deleteRestaurantResult > 0) {
            System.out.println("Restaurant deleted successfully.");
        } else {
            System.out.println("Failed to delete restaurant.");
        }
    }

    private static void deleteMenu(Scanner scanner) {
        System.out.print("Enter Restaurant ID: ");
        int res_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Menu ID: ");
        int menu_id = scanner.nextInt();
        scanner.nextLine();

        int result = menuDAO.delete(res_id, menu_id);

        if (result > 0) {
            System.out.println("Menu deleted successfully.");
        } else {
            System.out.println("Error deleting menu.");
        }
    }

    /* Review Functions - DB2024TEAM07_RestaurantDAO 오류 남 */
    private static void addReview() {
        System.out.print("Enter review ID: ");
        int reviewId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter menu name: ");
        String menuName = scanner.nextLine();
        System.out.print("Enter rating: ");
        int rating = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter review content: ");
        String reviewContent = scanner.nextLine();

        DB2024TEAM07_Review review = new DB2024TEAM07_Review(reviewId, userId, menuName, rating, reviewContent);

        int result = reviewDAO.add(review);
        if (result > 0) {
            System.out.println("Review added successfully.");
        } else {
            System.out.println("Failed to add review.");
        }
    }

    private static void updateReview() {
        System.out.print("Enter review ID to update: ");
        int reviewId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter updated menu name: ");
        String menuName = scanner.nextLine();
        System.out.print("Enter updated rating: ");
        int rating = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter updated review content: ");
        String reviewContent = scanner.nextLine();

        DB2024TEAM07_Review review = new DB2024TEAM07_Review(reviewId, null, menuName, rating, reviewContent);

        int result = reviewDAO.update(review);
        if (result > 0) {
            System.out.println("Review updated successfully.");
        } else {
            System.out.println("Failed to update review.");
        }
    }

    private static void deleteReview() {
        System.out.print("Enter review ID to delete: ");
        int reviewId = scanner.nextInt();

        int result = reviewDAO.delete(reviewId);
        if (result > 0) {
            System.out.println("Review deleted successfully.");
        } else {
            System.out.println("Failed to delete review.");
        }
    }

    private static void getReviewCount() {
        int count = reviewDAO.getCount();
        System.out.println("Total number of reviews: " + count);
    }

    private static void getReviews() {
        System.out.print("Enter page number: ");
        int page = scanner.nextInt();

        ArrayList<DB2024TEAM07_Review> reviews = reviewDAO.getReview(page);

        System.out.println("=== Reviews ===");
        for (DB2024TEAM07_Review review : reviews) {
            System.out.println("Review ID: " + review.getReview_id());
            System.out.println("User ID: " + review.getUser_id());
            System.out.println("Menu Name: " + review.getMenu_name());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Review Content: " + review.getReview_content());
            System.out.println();
        }
    }

    private static void getUserReviewCount() {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();

        int count = reviewDAO.getUserCount(userId);
        System.out.println("Total number of reviews for user " + userId + ": " + count);
    }

    private static void getUserReviews() {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter page number: ");
        int page = scanner.nextInt();

        ArrayList<DB2024TEAM07_UserReview> userReviews = reviewDAO.getUserReview(page, userId);

        System.out.println("=== User Reviews ===");
        for (DB2024TEAM07_UserReview review : userReviews) {
            System.out.println("Review ID: " + review.getReview_id());
            System.out.println("User ID: " + review.getUser_id());
            System.out.println("Menu Name: " + review.getMenu_name());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Review Content: " + review.getReview_content());
            System.out.println();
        }
    }

    private static void getRestaurantReviewCount() {
        System.out.print("Enter restaurant ID: ");
        int restaurantId = scanner.nextInt();

        int count = reviewDAO.getResCount(restaurantId);
        System.out.println("Total number of reviews for restaurant " + restaurantId + ": " + count);
    }

    private static void getRestaurantReviews() {
        System.out.print("Enter restaurant ID: ");
        int restaurantId = scanner.nextInt();

        System.out.print("Enter page number: ");
        int page = scanner.nextInt();

        ArrayList<DB2024TEAM07_Review> restaurantReviews = reviewDAO.getResReview(page, restaurantId);

        System.out.println("=== Restaurant Reviews ===");
        for (DB2024TEAM07_Review review : restaurantReviews) {
            System.out.println("Review ID: " + review.getReview_id());
            System.out.println("User ID: " + review.getUser_id());
            System.out.println("Menu Name: " + review.getMenu_name());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Review Content: " + review.getReview_content());
            System.out.println();
        }
    }
}

