package com.jdbc.model;

public class DB2024TEAM07_UserReview{
    private String user_id;
    private int review_id;
    private int rating;
    private String review_content;
    private String name;
    private String email;

    public DB2024TEAM07_UserReview() {
        super();
    }

    public DB2024TEAM07_UserReview(String user_id, int review_id, int rating, String review_content, String name, String email) {
        this.user_id = user_id;
        this.review_id = review_id;
        this.rating = rating;
        this.review_content = review_content;
        this.name = name;
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
