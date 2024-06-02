/*
DB2024TEAM07_RestaurantManager.java
*/

package com.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.jdbc.database.DB2024TEAM07_RestaurantDAO;
import com.jdbc.model.DB2024TEAM07_Restaurant;

public class DB2024TEAM07_RestaurantManager {

    private static DB2024TEAM07_RestaurantDAO restaurantDAO = new DB2024TEAM07_RestaurantDAO();

    /* Add Function */
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

    /* Update Function */
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

    /* Search Functions */
    /* Search by Restaurant Name, Cuisine Type, Location, Minimum Rating */
    public static void searchRestaurant(Scanner scanner) {
        System.out.print("Enter Restaurant Name (or press Enter to skip): ");
        String restaurantName = scanner.nextLine();
        if (restaurantName.trim().isEmpty()) restaurantName = null;

        System.out.print("Enter Cuisine Type (or press Enter to skip): ");
        String cuisineType = scanner.nextLine();
        if (cuisineType.trim().isEmpty()) cuisineType = null;

        System.out.print("Enter Location (or press Enter to skip): ");
        String location = scanner.nextLine();
        if (location.trim().isEmpty()) location = null;

        System.out.print("Enter Minimum Rating (or press Enter to skip): ");
        String ratingInput = scanner.nextLine();
        Float rating = null;
        if (!ratingInput.trim().isEmpty()) {
            try {
                rating = Float.parseFloat(ratingInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid rating input. Please enter a valid number or press Enter to skip.");
                return;
            }
        }

        List<DB2024TEAM07_Restaurant> restaurants = restaurantDAO.search(restaurantName, cuisineType, location, rating);

        if (restaurants.isEmpty()) {
            System.out.println("No restaurants found matching the criteria.");
        } else {
            System.out.println("Restaurants found:");
            for (DB2024TEAM07_Restaurant restaurant : restaurants) {
                System.out.println("Name: " + restaurant.getRes_name());
                System.out.println("ID: " + restaurant.getRes_id());
                System.out.println("Phone Number: " + restaurant.getPhone_num());
                System.out.println("Address: " + restaurant.getAddress());
                System.out.println("Operating Hours: " + restaurant.getOperating_hours());
                System.out.println("Break Time: " + restaurant.getBreak_time());
                System.out.println("Rating: " + restaurant.getRating());
                System.out.println("Cuisine Type: " + restaurant.getCuisine_type());
                System.out.println("Location: " + restaurant.getLocation());
                System.out.println("----------------------------");
            }
        }
    }

    /* Search by Cuisine Type */
    public static void searchRestaurantByCategory(Scanner scanner) {
        System.out.print("Enter Cuisine Type: ");
        String cuisineType = scanner.nextLine();

        try (ResultSet rs = restaurantDAO.searchRestaurantByCategory(cuisineType)) {
            if (rs != null && rs.next()) {
                do {
                    System.out.println("Restaurant Name: " + rs.getString("res_name"));
                    System.out.println("Phone Number: " + rs.getString("phone_num"));
                    System.out.println("Address: " + rs.getString("address"));
                    System.out.println("Operating Hours: " + rs.getString("operating_hours"));
                    System.out.println("Break Time: " + rs.getString("break_time"));
                    System.out.println("Rating: " + rs.getFloat("rating"));
                    System.out.println("Location: " + rs.getString("location"));
                    System.out.println();
                } while (rs.next());
            } else {
                System.out.println("No restaurants found for the given cuisine type.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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