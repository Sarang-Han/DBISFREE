package com.jdbc.view;

//뷰(DB2024_resReview)를 위한 VO

public class DB2024TEAM07_ResReviewVO {
    private int review_id;
    private String user_id;
    private String res_name;
    private int rating;
    private String review_content;

    public DB2024TEAM07_ResReviewVO() {
    }

    public DB2024TEAM07_ResReviewVO(int review_id, String user_id, String res_name, int rating, String review_content) {
        this.review_id = review_id;
        this.user_id = user_id;
        this.res_name = res_name;
        this.rating = rating;
        this.review_content = review_content;
    }

    public int getReview_id() {
        return review_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getRes_name() {
        return res_name;
    }

    public int getRating() {
        return rating;
    }

    public String getReview_content() {
        return review_content;
    }
}