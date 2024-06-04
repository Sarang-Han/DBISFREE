package com.app;

import com.manager.DB2024TEAM07_UserManager;

import java.util.Scanner;

public class DB2024TEAM07_UserMain {
    public static void showMenu(DB2024TEAM07_UserManager userManager, String loggedInUsername) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println(" ");
            System.out.println(STR."Welcome to E-MATEASY, \{loggedInUsername}!");
            System.out.println(" =================== ");
            System.out.println(" ");
            System.out.println("1. 맛집 검색하기");
            System.out.println("2. 랜덤 식당 추천받기");
            System.out.println("3. 로그아웃");
            System.out.println(" ");
            System.out.println(" =================== ");
            System.out.println(" ");
            System.out.print("메뉴 선택: ");

            boolean validChoice = false;
            int choice = 0;

            while (!validChoice) {
                System.out.print("Choose an option: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice >= 1 && choice <= 3) {
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
                    userManager.logout();
                    System.out.println("Logout successful!");
                    running = false;
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
    }
}
