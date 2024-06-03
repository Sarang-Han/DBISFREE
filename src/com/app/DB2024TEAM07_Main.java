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
            System.out.println("1. 회원 로그인");
            System.out.println("2. 회원 가입");
            System.out.println("3. 관리자 로그인");
            System.out.println("4. 종료");
            System.out.println(" ");
            System.out.println(" =================== ");
            System.out.println(" ");
            System.out.print("메뉴 선택: ");
            System.out.println(" ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // 회원 로그인
                    System.out.print("아이디: ");
                    String username = scanner.nextLine();
                    System.out.print("비밀번호: ");
                    String password = scanner.nextLine();

                    if (userManager.login(username, password)) {
                        System.out.println("로그인 성공!");
                        DB2024TEAM07_UserMain.showMenu(userManager);
                    } else {
                        System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
                    }
                    break;
                case 2:
                    // 회원 가입
                    System.out.print("새 아이디: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("새 비밀번호: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("이름: ");
                    String name = scanner.nextLine();
                    System.out.print("학번: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("이메일: ");
                    String email = scanner.nextLine();
                    System.out.print("위치: ");
                    String location = scanner.nextLine();

                    DB2024TEAM07_User newUser = new DB2024TEAM07_User(newUsername, newPassword, name, studentId, email, location);
                    if (userManager.addUser(newUser)) {
                        System.out.println("회원 가입 성공!");
                    } else {
                        System.out.println("회원 가입 실패.");
                    }
                    break;
                case 3:
                    // 관리자 로그인
                    System.out.print("관리자 비밀번호: ");
                    String adminPassword = scanner.nextLine();

                    if (ADMIN_PASSWORD.equals(adminPassword)) {
                        System.out.println("관리자 로그인 성공!");
                        DB2024TEAM07_AdminMain.main(new String[]{});
                    } else {
                        System.out.println("잘못된 관리자 비밀번호입니다.");
                    }
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
        scanner.close();
    }
}
