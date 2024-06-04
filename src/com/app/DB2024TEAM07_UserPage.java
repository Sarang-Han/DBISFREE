package com.app;

import com.jdbc.database.DB2024TEAM07_UserDAO;
import com.jdbc.model.DB2024TEAM07_User;
import com.jdbc.view.DB2024TEAM07_UserVO;

import java.util.Scanner;

public class DB2024TEAM07_UserPage {
    private DB2024TEAM07_UserDAO userDAO;
    private DB2024TEAM07_User loggedInUser;

    public DB2024TEAM07_UserPage(DB2024TEAM07_UserDAO userDAO, DB2024TEAM07_User loggedInUser) {
        this.userDAO = userDAO;
        this.loggedInUser = loggedInUser;
    }

    public void showUserPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n====== My Page ======\n");
            System.out.println("1. My information");
            System.out.println("2. Search for Other Users");
            System.out.println("3. Delete My Account");
            System.out.println("4. Exit");
            System.out.println("\n======================");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showMyInfo();
                    break;
                case 2:
                    searchOtherUser(scanner);
                    break;
                case 3:
                    deleteAccount(scanner);
                    running = false;
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Wrong choice. Please try again.");
            }
        }
    }

    private void showMyInfo() {
        DB2024TEAM07_User user = userDAO.getUser(loggedInUser.getUser_id());
        System.out.println("\n=== My Information ===\n");
        System.out.println("ID: " + user.getUser_id());
        System.out.println("Name: " + user.getName());
        System.out.println("Student ID: " + user.getStudent_id());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Location: " + user.getLocation());
        System.out.println("\n======================");
    }

    private void searchOtherUser(Scanner scanner) {
        System.out.print("Enter the user ID: ");
        String userId = scanner.nextLine();
        DB2024TEAM07_UserVO otherUser = userDAO.getOtherUser(userId);

        if (otherUser != null) {
            System.out.println("\n== User Information ==\n");
            System.out.println("ID: " + otherUser.getUser_id());
            System.out.println("Name: " + otherUser.getName());
            System.out.println("\n======================");
        } else {
            System.out.println("The user could not be found..");
        }
    }

    private void deleteAccount(Scanner scanner) {
        System.out.println("\n====== Good Bye ======\n");
        System.out.print("Please enter your PASSWORD: ");
        System.out.println("\n======================");

        String password = scanner.nextLine();
        int result = userDAO.delete(loggedInUser.getUser_id(), password);

        if (result > 0) {
            System.out.println("Membership withdrawal completed.");
        } else {
            System.out.println("The password does not match.");
        }

    }
}