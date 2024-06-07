package com.app;

import com.manager.DB2024TEAM07_MenuManager;
import com.manager.DB2024TEAM07_RestaurantManager;
import com.manager.DB2024TEAM07_ReviewManager;
import com.manager.DB2024TEAM07_UserManager;

import java.util.Scanner;

/**
 * User interface for logged-in users.
 *
 * This class provides functionalities such as searching for restaurants,
 * getting random restaurant recommendations, writing reviews, managing user information,
 * and logging out.
 */
public class DB2024TEAM07_UserMain {
    /**
     * Displays the user menu after successful login.
     *
     * @param userManager  a reference to the UserManager object for user-related operations
     * @param loggedInUsername the username of the currently logged-in user
     */
    public static void showMenu(DB2024TEAM07_UserManager userManager, String loggedInUsername) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println(" ");
            System.out.println("Welcome to E-MATEASY, " + loggedInUsername + "!");
            System.out.println("===================\n");
            System.out.println("1. Search for restaurants");
            System.out.println("2. Get Random Restaurant");
            System.out.println("3. Posting a new review");
            System.out.println("4. My Page");
            System.out.println("5. Logout");
            System.out.println("\n===================");

            boolean validChoice = false;
            int choice = 0;

            while (!validChoice) {
                System.out.print("Choose an option: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice >= 1 && choice <= 5) {
                        validChoice = true;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    showSearchMenu();
                    break;
                case 2:
                    DB2024TEAM07_RestaurantManager.displayRandomRestaurant();
                    break;
                case 3:
                    DB2024TEAM07_RestaurantManager.displayAllRestaurants();
                    System.out.println("\n===== Search menu =====");
                    DB2024TEAM07_MenuManager.searchMenuByRestaurant(scanner);
                    System.out.println("\n===== Add review =====");
                    DB2024TEAM07_ReviewManager.addReview(scanner);
                    break;
                case 4:
                    showUserPage();
                    break;
                case 5:
                    userManager.logout();
                    System.out.println("Logout successful!");
                    running = false;
                    break;
            }
        }
    }

    /**
     * Displays the search menu for finding restaurants.
     */
    private static void showSearchMenu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n======================\n");
        System.out.println("1. Search by Restaurant Name, Cuisine Type, Location, Minimum Rating");
        System.out.println("2. Search by Cuisine Type");
        System.out.println("3. Exit");
        System.out.println("\n======================");
        System.out.print("Choose an option: ");

        int choice = 0;
        boolean validChoice = false;

        while (!validChoice) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice >= 1 && choice <= 3) {
                    validChoice = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                    System.out.print("Choose an option: ");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Choose an option: ");
                scanner.nextLine();
            }
        }

        switch (choice) {
            case 1:
                DB2024TEAM07_RestaurantManager.searchRestaurant(scanner);
                showResDetail();
                break;
            case 2:
                DB2024TEAM07_RestaurantManager.searchRestaurantByCategory(scanner);
                showResDetail();
                break;
            case 3:
                break;
        }
    }

    /**
     * Displays detailed options after a restaurant search.
     */
    private static void showResDetail() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n==== Search Detail ====\n");
        System.out.println("1. Search Restaurant Menu");
        System.out.println("2. Search Restaurant Reviews");
        System.out.println("3. Exit");
        System.out.println("\n======================");
        System.out.print("Choose an option: ");

        int choice = 0;
        boolean validChoice = false;

        while (!validChoice) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice >= 1 && choice <= 3) {
                    validChoice = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                    System.out.print("Choose an option: ");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Choose an option: ");
                scanner.nextLine();
            }
        }

        switch (choice) {
            case 1:
                DB2024TEAM07_MenuManager.searchMenuByRestaurant(scanner);
                showResDetail();
                break;
            case 2:
                DB2024TEAM07_ReviewManager.getResReview(scanner);
                showResDetail();
                break;
            case 3:
                break;
        }
    }

    /**
     * Displays the user page menu for managing user information.
     */
    private static void showUserPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n====== My Page ======\n");
            System.out.println("1. My information");
            System.out.println("2. Update My information");
            System.out.println("3. Search for Other Users");
            System.out.println("4. Delete My Account");
            System.out.println("5. Exit");
            System.out.println("\n======================");
            System.out.print("Choose an option: ");

            int choice = 0;
            boolean validChoice = false;

            while (!validChoice) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice >= 1 && choice <= 5) {
                        validChoice = true;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                        System.out.print("Choose an option: ");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    System.out.print("Choose an option: ");
                    scanner.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    DB2024TEAM07_UserManager.showMyInfo();
                    break;
                case 2:
                    DB2024TEAM07_UserManager.update(scanner);
                    break;
                case 3:
                    DB2024TEAM07_UserManager.searchOtherUser(scanner);
                    break;
                case 4:
                    DB2024TEAM07_UserManager.deleteAccount(scanner);
                    running = false;
                    break;
                case 5:
                    running = false;
                    break;
            }
        }
    }
}
