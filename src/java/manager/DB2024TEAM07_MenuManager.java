package manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import jdbc.database.DB2024TEAM07_MenuDAO;
import jdbc.model.DB2024TEAM07_Menu;

public class DB2024TEAM07_MenuManager {

    private static DB2024TEAM07_MenuDAO menuDAO = new DB2024TEAM07_MenuDAO();

    /* Add Function */
    public static void addMenu(Scanner scanner) {
        System.out.print("Enter Menu ID: ");
        int menu_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Menu Name: ");
        String menu_name = scanner.nextLine();

        System.out.print("Enter Restaurant ID: ");
        int res_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Price: ");
        int price = scanner.nextInt();
        scanner.nextLine();

        DB2024TEAM07_Menu menu = new DB2024TEAM07_Menu(menu_id, menu_name, res_id, price);
        int result = menuDAO.add(menu);

        if (result > 0) {
            System.out.println("Menu added successfully.");
        } else {
            System.out.println("Error adding menu.");
        }
    }

    /* Update Function */
    public static void updateMenu(Scanner scanner) {
        System.out.print("Enter Restaurant ID: ");
        int res_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Menu ID: ");
        int menu_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Menu Name: ");
        String newMenuName = scanner.nextLine();

        System.out.print("Enter New Price: ");
        int newPrice = scanner.nextInt();
        scanner.nextLine();

        DB2024TEAM07_Menu updatedMenu = new DB2024TEAM07_Menu(menu_id, newMenuName, res_id, newPrice);
        int result = menuDAO.update(updatedMenu, res_id, menu_id);

        if (result > 0) {
            System.out.println("Menu updated successfully.");
        } else {
            System.out.println("Error updating menu.");
        }
    }

    /* Search Function */
    public static void searchMenu(Scanner scanner) {
        System.out.print("Enter Restaurant Name: ");
        String restaurantName = scanner.nextLine();

        System.out.print("Enter Menu Name: ");
        String menuName = scanner.nextLine();

        System.out.print("Enter Minimum Price: ");
        int minPrice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Maximum Price: ");
        int maxPrice = scanner.nextInt();
        scanner.nextLine();

        try (ResultSet result = menuDAO.searchByUsers(restaurantName, menuName, minPrice, maxPrice)) {
            if (result != null && result.next()) {
                System.out.println("Menu found by search:");
                do {
                    System.out.println("Restaurant Name: " + result.getString("res_name"));
                    System.out.println("Menu Name: " + result.getString("menu_name"));
                    System.out.println("Price: " + result.getInt("price"));
                } while (result.next());
            } else {
                System.out.println("No menu found matching user search criteria.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //searchByRestaurant는 menu_id(해당 식당 내에 있는 메뉴 나열 표시), menu_name, price만 출력되도록 하면 좋을 것 같아용

    /* Delete Function */
    public static void deleteMenu(Scanner scanner) {
        System.out.print("Enter Restaurant ID: ");
        int res_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Menu ID: ");
        int menu_id = scanner.nextInt();
        scanner.nextLine();

        int result = menuDAO.delete(res_id, menu_id);

        if (result > 0) {
            System.out.println("Menu deleted successfully.");
        } else {
            System.out.println("Error deleting menu.");
        }
    }
}
