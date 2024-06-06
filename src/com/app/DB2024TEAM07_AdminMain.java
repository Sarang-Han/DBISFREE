package com.app;

import com.jdbc.database.DB2024TEAM07_MenuDAO;
import com.jdbc.database.DB2024TEAM07_RestaurantDAO;
import com.jdbc.database.DB2024TEAM07_UserDAO;
import com.manager.DB2024TEAM07_MenuManager;
import com.manager.DB2024TEAM07_RestaurantManager;
import com.manager.DB2024TEAM07_ReviewManager;
import com.manager.DB2024TEAM07_UserManager;

import java.util.Scanner;

import static com.manager.DB2024TEAM07_RestaurantManager.displayAllRestaurants;

/**
 * Main class for administrator functionalities in the E-MATEASY application.
 *
 * This class provides a menu-driven interface for managing restaurants, menus, reviews,
 * and users.
 */
public class DB2024TEAM07_AdminMain {

    /**
     * The main entry point of the application.
     *
     * @param args command line arguments (unused in this program)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DB2024TEAM07_UserDAO userDAO = new DB2024TEAM07_UserDAO();
        DB2024TEAM07_UserManager userManager = new DB2024TEAM07_UserManager(userDAO);

        while (true) {
            System.out.println("\n======= Restaurant ======== =========== Menu ==========\n");
            System.out.println(" 1. Add Restaurant         |    5. Add Menu");
            System.out.println(" 2. Update Restaurant      |    6. Update Menu");
            System.out.println(" 3. Search Restaurant      |    7. Search Menu");
            System.out.println(" 4. Delete Restaurant      |    8. Delete Menu");
            System.out.println("\n========= Review ========== =========== User ==========\n");
            System.out.println(" 9. Add Review             |    13. Add User");
            System.out.println(" 10. Update Review         |    14. Update User");
            System.out.println(" 11. Search Review         |    15. Search User");
            System.out.println(" 12. Delete Review         |    16. Delete User");
            System.out.println("\n=========================== ===========================\n");
            System.out.println("17. Exit\n");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    DB2024TEAM07_RestaurantManager.addRestaurant(scanner);
                    break;
                case 2:
                    displayAllRestaurants();
                    DB2024TEAM07_RestaurantManager.updateRestaurant(scanner, new DB2024TEAM07_RestaurantDAO());
                    break;
                case 3:
                    search_restaurant_options(scanner);
                    break;
                case 4:
                    displayAllRestaurants();
                    DB2024TEAM07_RestaurantManager.deleteRestaurant(scanner);
                    break;
                case 5:
                    DB2024TEAM07_MenuManager.addMenu(scanner);
                    break;
                case 6:
                    displayAllRestaurants();
                    DB2024TEAM07_MenuManager.updateMenu(scanner, new DB2024TEAM07_MenuDAO());
                    break;
                case 7:
                    DB2024TEAM07_MenuManager.searchByManager(scanner);
                    break;
                case 8:
                    displayAllRestaurants();
                    DB2024TEAM07_MenuManager.deleteMenu(scanner);
                    break;
                case 9:
                    DB2024TEAM07_ReviewManager.addReview(scanner);
                    break;
                case 10:
                    DB2024TEAM07_ReviewManager.updateReview(scanner);
                    break;
                case 11:
                    search_review_options(scanner);
                    break;
                case 12:
                    DB2024TEAM07_ReviewManager.deleteReview(scanner);
                    break;
                case 13:
                    userManager.addAccountByManager(scanner);
                    break;
                case 14:
                    userManager.displayAllUsers();
                    userManager.updateAccountByManager(scanner);
                    break;
                case 15:
                    userManager.searchAccountByManager(scanner);
                    break;
                case 16:
                    userManager.displayAllUsers();
                    userManager.deleteAccountByManager(scanner);
                    break;
                case 17:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Presents sub-menu options for searching restaurants.
     *
     * @param scanner the input scanner
     */
    public static void search_restaurant_options(Scanner scanner){
        while (true) {
            System.out.println("\n======================\n");
            System.out.println("1. Search by Restaurant Name, Cuisine Type, Location, Minimum Rating");
            System.out.println("2. Search by Cuisine Type");
            System.out.println("3. Exit");
            System.out.println("\n======================");
            System.out.print("Choose an option:");
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

    /**
     * Presents sub-menu options for review management.
     *
     * @param scanner the input scanner
     */
    private static void search_review_options(Scanner scanner) {
        while (true) {
            System.out.println("\n======================\n");
            System.out.println("1. Get Review Count");
            System.out.println("2. Get Reviews");
            System.out.println("3. Get User Review Count");
            System.out.println("4. Get User Reviews");
            System.out.println("5. Get Restaurant Review Count");
            System.out.println("6. Get Restaurant Reviews");
            System.out.println("7. Exit");
            System.out.println("\n======================");
            System.out.print("Choose an option: ");
            int sub_choice = scanner.nextInt();
            scanner.nextLine();

            switch (sub_choice) {
                case 1:
                    DB2024TEAM07_ReviewManager.getCount();
                    break;
                case 2:
                    DB2024TEAM07_ReviewManager.getReview(scanner);
                    break;
                case 3:
                    DB2024TEAM07_ReviewManager.getUserCount(scanner);
                    break;
                case 4:
                    DB2024TEAM07_ReviewManager.getUserReview(scanner);
                    break;
                case 5:
                    DB2024TEAM07_ReviewManager.getResCount(scanner);
                    break;
                case 6:
                    DB2024TEAM07_ReviewManager.getResReview(scanner);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
