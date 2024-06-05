package com.jdbc.model;

/**
 * DB 2024 team 07 user.
 */
public class DB2024TEAM07_User {
    //user_id, user_pw, name, student_id, email, location
    private String user_id;
    private String user_pw;
    private String name;
    private int student_id;
    private String email;
    private String location;

    /**
     * Instantiates a new Db 2024 team 07 user.
     */
    public DB2024TEAM07_User(){}

    /**
     * Instantiates a new Db 2024 team 07 user.
     *
     * @param user_id    the user id
     * @param user_pw    the user pw
     * @param name       the name
     * @param student_id the student id
     * @param email      the email
     * @param location   the location
     */
    public DB2024TEAM07_User(String user_id, String user_pw, String name, int student_id, String email, String location) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.name = name;
        this.student_id = student_id;
        this.email = email;
        this.location = location;
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
     * Gets user pw.
     *
     * @return the user pw
     */
    public String getUser_pw() {
        return user_pw;
    }

    /**
     * Sets user pw.
     *
     * @param user_pw the user pw
     */
    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
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
     * Gets student id.
     *
     * @return the student id
     */
    public int getStudent_id() {
        return student_id;
    }

    /**
     * Sets student id.
     *
     * @param student_id the student id
     */
    public void setStudent_id(int student_id) {
        this.student_id = student_id;
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

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
