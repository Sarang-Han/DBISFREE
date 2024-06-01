package app;

import manager.DB2024TEAM07_UserManager;

import java.util.Scanner;

public class DB2024TEAM07_UserMain {
    public static void showMenu(DB2024TEAM07_UserManager userManager) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println(" ");
            System.out.println("===== USER MENU =====");
            System.out.println(" ");
            System.out.println("1. 로그아웃");
            // 추가적인 사용자 메뉴 옵션
            System.out.print("메뉴 선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userManager.logout();
                    System.out.println("로그아웃 성공.");
                    running = false;
                    break;
                // 추가적인 옵션 처리
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
    }
}
