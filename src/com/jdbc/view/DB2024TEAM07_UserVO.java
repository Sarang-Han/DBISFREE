package com.jdbc.view;

/**
 * This class represents a Value Object (VO) for a user in the E-MATEASY application.
 * A VO is a plain data transfer object that encapsulates data related to a specific entity
 * (in this case, a user) without any business logic or behavior.
 */
public class DB2024TEAM07_UserVO{ //뷰(DB2024_OtherUser)를 위한 VO
    private String user_id;
    private String name;
    private String email;

    public DB2024TEAM07_UserVO() {
    }

    /**
     * Constructor to initialize all fields of the VO.
     *
     * @param user_id unique identifier for the user
     * @param name name of the user
     * @param email email address of the user
     */
    public DB2024TEAM07_UserVO(String user_id, String name, String email) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
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
     * Getter method for the user name.
     *
     * @return the username
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the user email.
     *
     * @return the user email
     */
    public String getEmail() {
        return email;
    }
}