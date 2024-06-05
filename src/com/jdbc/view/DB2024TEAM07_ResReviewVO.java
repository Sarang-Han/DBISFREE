package com.jdbc.view;

/**
 * This class represents a Value Object (VO) for a restaurant review in the E-MATEASY application.
 * A VO is a plain data transfer object that encapsulates data related to a specific entity
 */
public class DB2024TEAM07_ResReviewVO { //뷰(DB2024_resReview)를 위한 VO
    private int review_id;
    private String user_id;
    private String res_name;
    private int rating;
    private String review_content;

    public DB2024TEAM07_ResReviewVO() {
    }

    /**
     * Constructor to initialize all fields of the VO.
     *
     * @param review_id unique identifier for the review
     * @param user_id username of the user who wrote the review
     * @param res_name name of the restaurant associated with the review
     * @param rating rating given to the restaurant by the user (1-5 stars)
     * @param review_content content of the review written by the user
     */
    public DB2024TEAM07_ResReviewVO(int review_id, String user_id, String res_name, int rating, String review_content) {
        this.review_id = review_id;
        this.user_id = user_id;
        this.res_name = res_name;
        this.rating = rating;
        this.review_content = review_content;
    }

    /**
     * Getter method for the review ID.
     *
     * @return the review ID
     */
    public int getReview_id() {
        return review_id;
    }

    /**
     * Getter method for the user ID.
     *
     * @return the user ID
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * Getter method for the restaurant name.
     *
     * @return the restaurant name
     */
    public String getRes_name() {
        return res_name;
    }

    /**
     * Getter method for the rating.
     *
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Getter method for the review content.
     *
     * @return the review content
     */
    public String getReview_content() {
        return review_content;
    }
}