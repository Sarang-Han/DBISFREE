/**
 * This class represents the main entry point of the application.
 * It provides a menu-driven interface for users to interact with the system.
 *
 * @author Sarang
 */
package com.app;

import com.manager.DB2024TEAM07_UserManager;
import com.jdbc.database.DB2024TEAM07_UserDAO;
import com.jdbc.model.DB2024TEAM07_User;

import java.util.Scanner;

public class DB2024TEAM07_Main {
    private static final String ADMIN_PASSWORD = "admin123";

    public static void main(String[] args) {
        DB2024TEAM07_UserDAO userDAO = new DB2024TEAM07_UserDAO();
        DB2024TEAM07_UserManager userManager = new DB2024TEAM07_UserManager(userDAO);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("███████╗    ███╗   ███╗ █████╗ ████████╗███████╗ █████╗ ███████╗██╗   ██╗");
            System.out.println("██╔════╝    ████╗ ████║██╔══██╗╚══██╔══╝██╔════╝██╔══██╗██╔════╝╚██╗ ██╔╝");
            System.out.println("█████╗█████╗██╔████╔██║███████║   ██║   █████╗  ███████║███████╗ ╚████╔╝ ");
            System.out.println("██╔══╝╚════╝██║╚██╔╝██║██╔══██║   ██║   ██╔══╝  ██╔══██║╚════██║  ╚██╔╝  ");
            System.out.println("███████╗    ██║ ╚═╝ ██║██║  ██║   ██║   ███████╗██║  ██║███████║   ██║   ");
            System.out.println("╚══════╝    ╚═╝     ╚═╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝   ╚═╝   ");
            System.out.println("\n===================\n");
            System.out.println("1. User Login");
            System.out.println("2. User Register");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
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
                    // User Login
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    if (userManager.login(username, password)) {
                        System.out.println("Login successful!");
                        String loggedInUsername = username;
                        DB2024TEAM07_UserMain.showMenu(userManager, loggedInUsername);
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case 2:
                    // User Register
                    System.out.print("New username: ");
                    String newUsername = scanner.nextLine();

                    // Validate username format (maximum length of 50 characters)
                    if (newUsername.length() > 50) {
                        System.out.println("Username cannot exceed 50 characters.");
                        return;
                    }

                    System.out.print("New password: ");
                    String newPassword = scanner.nextLine();

                    // Validate password format (maximum length of 50 characters)
                    if (newPassword.length() > 50) {
                        System.out.println("Password cannot exceed 50 characters.");
                        return;
                    }

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    if (name.length() > 50) {
                        System.out.println("Password cannot exceed 50 characters.");
                        return;
                    }

                    System.out.print("Student ID: ");
                    String studentIdStr = scanner.nextLine();

                    while (!studentIdStr.matches("[0-9]{1,50}")) {
                        System.out.println("Invalid student ID. Please enter a numerical value between 1 and 50 characters long.");
                        studentIdStr = scanner.nextLine();
                    }

                    int studentId = Integer.parseInt(studentIdStr);

                    String email;
                    do {
                        System.out.print("Email (format: example@domain.com): ");
                        email = scanner.nextLine();

                        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$")) {
                            System.out.println("Please enter a valid email address (ex: example@domain.com).");
                        } else if (email.length() > 50) {
                            System.out.println("Email cannot exceed 50 characters.");
                            break;
                        }
                    } while (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$"));

                    System.out.println("1. 정문");
                    System.out.println("2. 후문");
                    System.out.print("Choose an Location (Enter 1 or 2): ");
                    String locationChoice = scanner.nextLine();

                    String location;
                    switch (locationChoice) {
                        case "1":
                            location = "정문";
                            break;
                        case "2":
                            location = "후문";
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter 1 for 정문 or 2 for 후문:");
                            return;
                    }

                    DB2024TEAM07_User newUser = new DB2024TEAM07_User(newUsername, newPassword, name, studentId, email, location);
                    if (userManager.addUser(newUser)) {
                        System.out.println("Registration successful!");
                    } else {
                        System.out.println("Registration failed.");
                    }
                    break;
                case 3:
                    // Admin login
                    System.out.print("Enter admin password: ");
                    String adminPassword = scanner.nextLine();

                    if (ADMIN_PASSWORD.equals(adminPassword)) {
                        System.out.println("Administrator login successful!");
                        DB2024TEAM07_AdminMain.main(new String[]{});
                    } else {
                        System.out.println("Incorrect administrator password.");
                    }
                    break;
                case 4:
                    return;
            }
        }
    }
}
