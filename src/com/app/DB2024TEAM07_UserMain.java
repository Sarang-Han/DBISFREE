/**
 * User interface for logged-in users.
 * This class provides functionalities such as searching for restaurants,
 * getting random restaurant recommendations, writing reviews, managing user information,
 * and logging out.
 */
package com.app;

import com.manager.DB2024TEAM07_RestaurantManager;
import com.manager.DB2024TEAM07_ReviewManager;
import com.manager.DB2024TEAM07_UserManager;

import java.util.Scanner;


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
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
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
        System.out.println("1. Search By Category");
        System.out.println("2. Search By Other Information");
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
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
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
                DB2024TEAM07_RestaurantManager.searchRestaurantByCategory(scanner);
                break;
            case 2:
                DB2024TEAM07_RestaurantManager.searchRestaurant(scanner);
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
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
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
