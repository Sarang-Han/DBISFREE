package com.app;

import com.manager.DB2024TEAM07_UserManager;
import java.util.List;
import com.jdbc.model.DB2024TEAM07_Restaurant;

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
            System.out.println("3. 내 정보 수정하기");
            System.out.println("4. 로그아웃");
            System.out.println(" ");
            System.out.println(" =================== ");
            System.out.println(" ");

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
                    System.out.print("Restaurant Name (Can be omitted): ");
                    String restaurantName = scanner.nextLine();
                    if (restaurantName.trim().isEmpty()) restaurantName = null;

                    System.out.print("Cuisine Type (Can be omitted): ");
                    String cuisineType = scanner.nextLine();
                    if (cuisineType.trim().isEmpty()) cuisineType = null;

                    System.out.print("Location (Can be omitted): ");
                    String location = scanner.nextLine();
                    if (location.trim().isEmpty()) location = null;

                    System.out.print("Minimum Rating (Can be omitted): ");
                    String ratingInput = scanner.nextLine();
                    Float rating = null;
                    if (!ratingInput.trim().isEmpty()) {
                        try {
                            rating = Float.parseFloat(ratingInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid rating entered.");
                        }
                    }

                    List<DB2024TEAM07_Restaurant> restaurants = userManager.getRestaurantDAO().search(restaurantName, cuisineType, location, rating);

                    if (restaurants.isEmpty()) {
                        System.out.println("No search results.");
                    } else {
                        System.out.println("검색 결과:");
                        System.out.printf("%-30s%-15s%-20s%-40s%-25s%-20s%-10s%-20s%-20s%n", "이름", "ID", "전화번호", "주소", "운영시간", "브레이크 타임", "평점", "음식 종류", "지역");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        for (DB2024TEAM07_Restaurant restaurant : restaurants) {
                            System.out.printf("%-30s%-15d%-20s%-40s%-25s%-20s%-10.1f%-20s%-20s%n",
                                    restaurant.getRes_name(),
                                    restaurant.getRes_id(),
                                    restaurant.getPhone_num(),
                                    restaurant.getAddress(),
                                    restaurant.getOperating_hours(),
                                    restaurant.getBreak_time(),
                                    restaurant.getRating(),
                                    restaurant.getCuisine_type(),
                                    restaurant.getLocation());
                        }
                    }
                    break;
            }
        }
    }
}
