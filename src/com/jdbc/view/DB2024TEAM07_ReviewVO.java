package com.jdbc.view;

//뷰(DB2024_viewReview)를 위한 VO

public class DB2024TEAM07_ReviewVO{
    private int review_id;
    private String name;
    private String menu_name;
    private int rating;
    private String review_content;

    public DB2024TEAM07_ReviewVO() {
    }

    public DB2024TEAM07_ReviewVO(int review_id, String name, String menu_name, int rating, String review_content) {
        this.review_id = review_id;
        this.name = name;
        this.menu_name = menu_name;
        this.rating = rating;
        this.review_content = review_content;
    }

    public int getReview_id() {
        return review_id;
    }

    public String getName() {
        return name;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public int getRating() {
        return rating;
    }

    public String getReview_content() {
        return review_content;
    }
}