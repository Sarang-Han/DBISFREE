package com.app;

import com.manager.DB2024TEAM07_RestaurantManager;
import com.manager.DB2024TEAM07_UserManager;
import java.util.List;
import com.jdbc.model.DB2024TEAM07_Restaurant;

import java.util.Scanner;

public class DB2024TEAM07_UserMain {
    public static void showMenu(DB2024TEAM07_UserManager userManager, String loggedInUsername) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println(" ");
            System.out.println("Welcome to E-MATEASY, " + loggedInUsername + "!");
            System.out.println("===================\n");
            System.out.println("1. Search for restaurants");
            System.out.println("2. Get Random Restaurant");
            System.out.println("3. My Page");
            System.out.println("4. Logout");
            System.out.println("\n===================");

            boolean validChoice = false;
            int choice = 0;

            while (!validChoice) {
                System.out.print("Choose an option: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice >= 1 && choice <= 4) {
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
                break;

                case 2:
                    DB2024TEAM07_RestaurantManager.displayRandomRestaurant();
                    break;
                case 3:
                    DB2024TEAM07_MyPage userPage = new DB2024TEAM07_MyPage(userManager.getUserDAO(), userManager.getLoggedInUser());
                    userPage.showUserPage();
                    break;
                case 4:
                    userManager.logout();
                    System.out.println("Logout successful!");
                    running = false;
                    break;
            }
        }
    }
}
