package app;

import java.util.Scanner;

public class DB2024TEAM07_Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println(" ");
            System.out.println("===== E-MATEASY =====");
            System.out.println(" ");
            System.out.println("1. User Menu");
            System.out.println("2. Admin Menu");
            System.out.println("3. Exit");
            System.out.println(" ");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    DB2024TEAM07_UserMain.main(new String[]{});
                    running = false;
                    break;
                case 2:
                    DB2024TEAM07_AdminMain.main(new String[]{});
                    running = false;
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
