package com.app;

import com.jdbc.database.DB2024TEAM07_UserDAO;
import com.jdbc.model.DB2024TEAM07_User;
import com.jdbc.view.DB2024TEAM07_UserVO;

import java.util.Scanner;

public class DB2024TEAM07_MyPage {
    private DB2024TEAM07_UserDAO userDAO;
    private DB2024TEAM07_User loggedInUser;

    public DB2024TEAM07_MyPage(DB2024TEAM07_UserDAO userDAO, DB2024TEAM07_User loggedInUser) {
        this.userDAO = userDAO;
        this.loggedInUser = loggedInUser;
    }

    public void showUserPage() {
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
                    showMyInfo();
                    break;
                case 2:
                    update(scanner);
                    break;
                case 3:
                    searchOtherUser(scanner);
                    break;
                case 4:
                    deleteAccount(scanner);
                    running = false;
                    break;
                case 5:
                    running = false;
                    break;

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
        System.out.println("\n======================\n");

        String password = scanner.nextLine();
        int result = userDAO.delete(loggedInUser.getUser_id(), password);

        if (result > 0) {
            System.out.println("Membership withdrawal completed.");
            DB2024TEAM07_Main.main(null);
            System.exit(0);
        } else {
            System.out.println("The password does not match.");
        }

    }

    private void update(Scanner scanner) {
        System.out.println("\n===== Update My information =====\n");
        System.out.print("New ID (Press enter to skip): ");
        String newUserId = scanner.nextLine();
        String userId = newUserId.isEmpty() ? loggedInUser.getUser_id() : newUserId;

        System.out.print("New Password (Press enter to skip): ");
        String newUserPw = scanner.nextLine();
        String userPw = newUserPw.isEmpty() ? loggedInUser.getUser_pw() : newUserPw;

        System.out.print("New Name (Press enter to skip): ");
        String newName = scanner.nextLine();
        String name = newName.isEmpty() ? loggedInUser.getName() : newName;

        System.out.print("New Student ID (Press enter to skip): ");
        String newStudentIdStr = scanner.nextLine();
        int studentId = newStudentIdStr.isEmpty() ? loggedInUser.getStudent_id() : Integer.parseInt(newStudentIdStr);

        System.out.print("New Email (Press enter to skip): ");
        String newEmail = scanner.nextLine();
        String email = newEmail.isEmpty() ? loggedInUser.getEmail() : newEmail;

        System.out.print("New Location (Press enter to skip): ");
        String newLocation = scanner.nextLine();
        String location = newLocation.isEmpty() ? loggedInUser.getLocation() : newLocation;

        DB2024TEAM07_User updatedUser = new DB2024TEAM07_User(userId, userPw, name, studentId, email, location);
        int result = userDAO.update(updatedUser, loggedInUser.getUser_id());

        if (result > 0) {
            System.out.println("Update successful!");
            loggedInUser = updatedUser; // 업데이트된 사용자 정보로 loggedInUser 업데이트
        } else {
            System.out.println("Failed to update information.");
        }

    }
}