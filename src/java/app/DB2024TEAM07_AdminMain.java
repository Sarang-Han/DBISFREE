package app;

import java.util.Scanner;
import manager.DB2024TEAM07_RestaurantManager;
import manager.DB2024TEAM07_MenuManager;

public class DB2024TEAM07_AdminMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println(" ");
            System.out.println("====== Restaurant ======");
            System.out.println("1. Add Restaurant");
            System.out.println("2. Update Restaurant");
            System.out.println("3. Search Restaurant");
            System.out.println("4. Delete Restaurant");
            System.out.println(" ");
            System.out.println("========= Menu =========");
            System.out.println("5. Add Menu");
            System.out.println("6. Update Menu");
            System.out.println("7. Search Menu");
            System.out.println("8. Delete Menu");
            System.out.println(" ");
            System.out.println("9. Exit");
            System.out.println(" ");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    DB2024TEAM07_RestaurantManager.addRestaurant(scanner);
                    break;
                case 2:
                    DB2024TEAM07_RestaurantManager.updateRestaurant(scanner);
                    break;
                case 3:
                    DB2024TEAM07_RestaurantManager.searchRestaurant(scanner);
                    break;
                case 4:
                    DB2024TEAM07_RestaurantManager.deleteRestaurant(scanner);
                    break;
                case 5:
                    DB2024TEAM07_MenuManager.addMenu(scanner);
                    break;
                case 6:
                    DB2024TEAM07_MenuManager.updateMenu(scanner);
                    break;
                case 7:
                    DB2024TEAM07_MenuManager.searchMenu(scanner);
                    break;
                case 8:
                    DB2024TEAM07_MenuManager.deleteMenu(scanner);
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
