package com.jdbc.model;

public class DB2024TEAM07_Review{
    //review_id, user_id, menu_id, rating, review_content
    private int review_id;
    private String user_id;
    private String menu_id;
    private int rating;
    private String review_content;

    public DB2024TEAM07_Review(){}
    public DB2024TEAM07_Review(int review_id, String user_id, String menu_id, int rating, String review_content) {
        this.review_id = review_id;
        this.user_id = user_id;
        this.menu_id = menu_id;
        this.rating = rating;
        this.review_content = review_content;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setmMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }
}

