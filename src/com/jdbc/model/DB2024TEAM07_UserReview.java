package com.jdbc.model;

/**
 * DB 2024 team 07 user review.
 */
public class DB2024TEAM07_UserReview{
    private String user_id;
    private int review_id;
    private int rating;
    private String review_content;
    private String name;
    private String email;

    /**
     * Instantiates a new Db 2024 team 07 user review.
     */
    public DB2024TEAM07_UserReview() {
        super();
    }

    /**
     * Instantiates a new Db 2024 team 07 user review.
     *
     * @param user_id        the user id
     * @param review_id      the review id
     * @param rating         the rating
     * @param review_content the review content
     * @param name           the name
     * @param email          the email
     */
    public DB2024TEAM07_UserReview(String user_id, int review_id, int rating, String review_content, String name, String email) {
        this.user_id = user_id;
        this.review_id = review_id;
        this.rating = rating;
        this.review_content = review_content;
        this.name = name;
        this.email = email;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * Sets user id.
     *
     * @param user_id the user id
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * Gets review id.
     *
     * @return the review id
     */
    public int getReview_id() {
        return review_id;
    }

    /**
     * Sets review id.
     *
     * @param review_id the review id
     */
    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Gets review content.
     *
     * @return the review content
     */
    public String getReview_content() {
        return review_content;
    }

    /**
     * Sets review content.
     *
     * @param review_content the review content
     */
    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
