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
        boolean running = true;

        while (running) {
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("███████╗    ███╗   ███╗ █████╗ ████████╗███████╗ █████╗ ███████╗██╗   ██╗");
            System.out.println("██╔════╝    ████╗ ████║██╔══██╗╚══██╔══╝██╔════╝██╔══██╗██╔════╝╚██╗ ██╔╝");
            System.out.println("█████╗█████╗██╔████╔██║███████║   ██║   █████╗  ███████║███████╗ ╚████╔╝ ");
            System.out.println("██╔══╝╚════╝██║╚██╔╝██║██╔══██║   ██║   ██╔══╝  ██╔══██║╚════██║  ╚██╔╝  ");
            System.out.println("███████╗    ██║ ╚═╝ ██║██║  ██║   ██║   ███████╗██║  ██║███████║   ██║   ");
            System.out.println("╚══════╝    ╚═╝     ╚═╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝   ╚═╝   ");
            System.out.println(" ");
            System.out.println(" =================== ");
            System.out.println(" ");
            System.out.println("1. User Login");
            System.out.println("2. User Register");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.println(" ");
            System.out.println(" =================== ");
            System.out.println(" ");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // 회원 로그인
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    if (userManager.login(username, password)) {
                        System.out.println("Login successful!");
                        DB2024TEAM07_UserMain.showMenu(userManager);
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case 2:
                    // 회원 가입
                    System.out.print("New username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("New password: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Student ID: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Location: ");
                    String location = scanner.nextLine();

                    DB2024TEAM07_User newUser = new DB2024TEAM07_User(newUsername, newPassword, name, studentId, email, location);
                    if (userManager.addUser(newUser)) {
                        System.out.println("Registration successful!");
                    } else {
                        System.out.println("Registration failed.");
                    }
                    break;
                case 3:
                    // 관리자 로그인
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
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
