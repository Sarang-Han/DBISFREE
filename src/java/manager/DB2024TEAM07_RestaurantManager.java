package manager;

import java.util.*;
import jdbc.database.DB2024TEAM07_RestaurantDAO;
import jdbc.model.DB2024TEAM07_Restaurant;

public class DB2024TEAM07_RestaurantManager {

    private static DB2024TEAM07_RestaurantDAO restaurantDAO = new DB2024TEAM07_RestaurantDAO();

    public static void addRestaurant(Scanner scanner) {
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

    public static void updateRestaurant(Scanner scanner) {
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

    public static void searchRestaurant(Scanner scanner) {
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

    public static void deleteRestaurant(Scanner scanner) {
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
}
