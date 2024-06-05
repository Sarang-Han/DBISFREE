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
    public static void updateRestaurant(Scanner scanner, DB2024TEAM07_RestaurantDAO restaurantDAO) {
        System.out.print("Enter Restaurant ID: ");
        int res_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Restaurant Name (Press enter to skip): ");
        String res_name = scanner.nextLine();

        System.out.print("Enter New Phone Number (Press enter to skip): ");
        String phone_num = scanner.nextLine();

        System.out.print("Enter New Address (Press enter to skip): ");
        String address = scanner.nextLine();

        System.out.print("Enter New Operating Hours (Press enter to skip): ");
        String operating_hours = scanner.nextLine();

        System.out.print("Enter New Break Time (Press enter to skip): ");
        String break_time = scanner.nextLine();

        System.out.print("Enter New Rating (Press enter to skip): ");
        String ratingInput = scanner.nextLine();
        int rating = -1;
        if (!ratingInput.isEmpty()) {
            rating = Integer.parseInt(ratingInput);
        }

        System.out.print("Enter New Cuisine Type (Press enter to skip): ");
        String cuisine_type = scanner.nextLine();

        System.out.print("Enter New Location (Press enter to skip): ");
        String location = scanner.nextLine();

        DB2024TEAM07_Restaurant updatedRestaurant = new DB2024TEAM07_Restaurant(
                res_name.isEmpty() ? null : res_name,
                res_id,
                phone_num.isEmpty() ? null : phone_num,
                address.isEmpty() ? null : address,
                operating_hours.isEmpty() ? null : operating_hours,
                break_time.isEmpty() ? null : break_time,
                rating,
                cuisine_type.isEmpty() ? null : cuisine_type,
                location.isEmpty() ? null : location
        );

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
            System.out.printf("%-20s \t%-10s \t%-15s \t%-30s \t%-20s \t%-20s \t%-10s \t%-20s \t%-20s%n", "Name", "ID", "Phone Number", "Address", "Operating Hours", "Break Time", "Rating", "Cuisine Type", "Location");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (DB2024TEAM07_Restaurant restaurant : restaurants) {
                System.out.printf("%-20s \t%-10d \t%-15s \t%-30s \t%-20s \t%-20s \t%-10.1f \t%-20s \t%-20s%n",
                        restaurant.getRes_name(), restaurant.getRes_id(), restaurant.getPhone_num(), restaurant.getAddress(),
                        restaurant.getOperating_hours(), restaurant.getBreak_time(), restaurant.getRating(),
                        restaurant.getCuisine_type(), restaurant.getLocation());
            }
        }
    }

    /* Search by Cuisine Type */
    public static void searchRestaurantByCategory(Scanner scanner) {
        System.out.print("Enter Cuisine Type: ");
        String cuisineType = scanner.nextLine();

        try (ResultSet rs = restaurantDAO.searchRestaurantByCategory(cuisineType)) {
            if (rs != null && rs.next()) {
                System.out.printf("%-25s %-20s %-40s %-20s %-15s %-10s %-20s%n",
                        "Restaurant Name", "Phone Number", "Address", "Operating Hours", "Break Time", "Rating", "Location");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
                do {
                    System.out.printf("%-25s %-20s %-40s %-20s %-15s %-10.1f %-20s%n",
                            rs.getString("res_name"), rs.getString("phone_num"), rs.getString("address"),
                            rs.getString("operating_hours"), rs.getString("break_time"), rs.getFloat("rating"),
                            rs.getString("location"));
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

    /* Print Function */
    public static void displayAllRestaurants() {
        List<DB2024TEAM07_Restaurant> restaurants = restaurantDAO.getAllRestaurants();

        // Print header
        System.out.println("---------------------------------------------------");
        System.out.printf("%-15s %-30s%n", "Restaurant ID", "Restaurant Name");
        System.out.println("---------------------------------------------------");

        // Print each restaurant (ID and Name)
        for (DB2024TEAM07_Restaurant restaurant : restaurants) {
            System.out.printf("%-15d %-30s%n", restaurant.getRes_id(), restaurant.getRes_name());
        }

        // Footer
        System.out.println("---------------------------------------------------");
    }
}
