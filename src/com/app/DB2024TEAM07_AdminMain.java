package com.app;

import com.manager.DB2024TEAM07_MenuManager;
import com.manager.DB2024TEAM07_RestaurantManager;
import com.manager.DB2024TEAM07_ReviewManager;

import java.util.Scanner;

public class DB2024TEAM07_AdminMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n====== Restaurant ======");
            System.out.println("1. Add Restaurant");
            System.out.println("2. Update Restaurant");
            System.out.println("3. Search Restaurant");
            System.out.println("4. Delete Restaurant");
            System.out.println("\n========= Menu =========");
            System.out.println("5. Add Menu");
            System.out.println("6. Update Menu");
            System.out.println("7. Search Menu");
            System.out.println("8. Delete Menu");
            System.out.println("\n========= Review =========");
            System.out.println("9. Add Review");
            System.out.println("10. Update Review");
            System.out.println("11. Get Review Count");
            System.out.println("12. Get Reviews");
            System.out.println("13. Get User Review Count");
            System.out.println("14. Get User Reviews");
            System.out.println("15. Get Restaurant Review Count");
            System.out.println("16. Get Restaurant Reviews");
            System.out.println("17. Delete Review");
            System.out.println("\n18. Exit");
            System.out.print("\nChoose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    DB2024TEAM07_RestaurantManager.addRestaurant(scanner);
                    break;
                case 2:
                    DB2024TEAM07_RestaurantManager.updateRestaurant(scanner);
                    break;
                case 3:
                    search_restaurant_options(scanner);
                    break;
                case 4:
                    DB2024TEAM07_RestaurantManager.deleteRestaurant(scanner);
                    break;
                case 5:
                    DB2024TEAM07_MenuManager.addMenu(scanner);
                    break;
                case 6:
                    DB2024TEAM07_MenuManager.updateMenu(scanner);
                    break;
                case 7:
                    search_menu_options(scanner);
                    break;
                case 8:
                    DB2024TEAM07_MenuManager.deleteMenu(scanner);
                    break;
                case 9:
                    DB2024TEAM07_ReviewManager.addReview(scanner);
                    break;
                case 10:
                    DB2024TEAM07_ReviewManager.updateReview(scanner);
                    break;
                case 11:
                    DB2024TEAM07_ReviewManager.getCount();
                    break;
                case 12:
                    DB2024TEAM07_ReviewManager.getReview(scanner);
                    break;
                case 13:
                    DB2024TEAM07_ReviewManager.getUserCount(scanner);
                    break;
                case 14:
                    DB2024TEAM07_ReviewManager.getUserReview(scanner);
                    break;
                case 15:
                    DB2024TEAM07_ReviewManager.getResCount(scanner);
                    break;
                case 16:
                    DB2024TEAM07_ReviewManager.getResReview(scanner);
                    break;
                case 17:
                    DB2024TEAM07_ReviewManager.deleteReview(scanner);
                    break;
                case 18:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }


    public static void search_restaurant_options(Scanner scanner){
        while (true) {
            System.out.println("\n1. Search by Restaurant Name, Cuisine Type, Location, Minimum Rating");
            System.out.println("2. Search by Cuisine Type");
            System.out.println("3. Exit");
            System.out.println("\nChoose an option:");
            int sub_choice = scanner.nextInt();
            scanner.nextLine();

            switch (sub_choice) {
                case 1:
                    DB2024TEAM07_RestaurantManager.searchRestaurant(scanner);
                    break;
                case 2:
                    DB2024TEAM07_RestaurantManager.searchRestaurantByCategory(scanner);
                    break;
                case 3:
                    return; // Exit the menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void search_menu_options(Scanner scanner) {
        System.out.println("\n1. Search Menu by Restaurant Name, Menu Name, and Price Range (User)");
        System.out.println("2. Search Menu by Restaurant ID (User)");
        System.out.println("3. Search Menu by Restaurant ID (Manager)");
        System.out.println("4. Exit");
        System.out.println("\nChoose an option:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                DB2024TEAM07_MenuManager.searchByUsers(scanner);
                break;
            case 2:
                DB2024TEAM07_MenuManager.searchMenuByRestaurant(scanner);
                break;
            case 3:
                DB2024TEAM07_MenuManager.searchByManager(scanner);
                break;
            case 4:
                return; // Exit the menu
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
