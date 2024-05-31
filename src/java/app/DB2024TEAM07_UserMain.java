package app;

import java.util.Scanner;
import manager.DB2024TEAM07_RestaurantManager;
import manager.DB2024TEAM07_MenuManager;

public class DB2024TEAM07_UserMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println(" ");
            System.out.println("====== Restaurant ======");
            System.out.println("1. Search Restaurant");
            System.out.println(" ");
            System.out.println("========= Menu =========");
            System.out.println("2. Search Menu");
            System.out.println(" ");
            System.out.println("3. Exit");
            System.out.println(" ");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    DB2024TEAM07_RestaurantManager.searchRestaurant(scanner);
                    break;
                case 2:
                    DB2024TEAM07_MenuManager.searchMenu(scanner);
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
