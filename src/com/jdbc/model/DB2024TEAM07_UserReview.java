package com.jdbc.model;

public class DB2024TEAM07_UserReview extends DB2024TEAM07_Review{
    private int review_id;
    private String user_id;
    private int rating;
    private String review_content;
    private String name;
    private String email;

    public DB2024TEAM07_UserReview() {
        super();
    }

    public DB2024TEAM07_UserReview(int review_id, String user_id, int rating, String review_content, String name, String email) {
        this.review_id = review_id;
        this.user_id = user_id;
        this.rating = rating;
        this.review_content = review_content;
        this.name = name;
        this.email = email;
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
