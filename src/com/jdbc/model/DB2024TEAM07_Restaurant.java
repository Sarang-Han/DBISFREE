/**
 * The com.jdbc.model package contains classes representing database models.
 */
package com.jdbc.model;

/**
 * DB 2024 team 07 restaurant.
 */
public class DB2024TEAM07_Restaurant{
    /**
     * The Res name.
     */
    String res_name;
    /**
     * The Res id.
     */
    int res_id;
    /**
     * The Phone num.
     */
    String phone_num;
    /**
     * The Address.
     */
    String address;
    /**
     * The Operating hours.
     */
    String operating_hours;
    /**
     * The Break time.
     */
    String break_time;
    /**
     * The Rating.
     */
    float rating;
    /**
     * The Cuisine type.
     */
    String cuisine_type;
    /**
     * The Location.
     */
    String location;

    /**
     * Instantiates a new Db 2024 team 07 restaurant.
     *
     * @param res_name        the res name
     * @param res_id          the res id
     * @param phone_num       the phone num
     * @param address         the address
     * @param operating_hours the operating hours
     * @param break_time      the break time
     * @param rating          the rating
     * @param cuisine_type    the cuisine type
     * @param location        the location
     */
    public DB2024TEAM07_Restaurant(String res_name, int res_id, String phone_num, String address, String operating_hours, String break_time, float rating, String cuisine_type, String location) {
        this.res_name = res_name;
        this.res_id = res_id;
        this.phone_num = phone_num;
        this.address = address;
        this.operating_hours = operating_hours;
        this.break_time = break_time;
        this.rating = rating;
        this.cuisine_type = cuisine_type;
        this.location = location;
    }

    //getter,setter

    /**
     * Gets res name.
     *
     * @return the res name
     */
    public String getRes_name() {
        return res_name;
    }

    /**
     * Sets res name.
     *
     * @param res_name the res name
     */
    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    /**
     * Gets res id.
     *
     * @return the res id
     */
    public int getRes_id() {
        return res_id;
    }

    /**
     * Sets res id.
     *
     * @param res_id the res id
     */
    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

    /**
     * Gets phone num.
     *
     * @return the phone num
     */
    public String getPhone_num() {
        return phone_num;
    }

    /**
     * Sets phone num.
     *
     * @param phone_num the phone num
     */
    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets operating hours.
     *
     * @return the operating hours
     */
    public String getOperating_hours() {
        return operating_hours;
    }

    /**
     * Sets operating hours.
     *
     * @param operating_hours the operating hours
     */
    public void setOperating_hours(String operating_hours) {
        this.operating_hours = operating_hours;
    }

    /**
     * Gets break time.
     *
     * @return the break time
     */
    public String getBreak_time() {
        return break_time;
    }

    /**
     * Sets break time.
     *
     * @param break_time the break time
     */
    public void setBreak_time(String break_time) {
        this.break_time = break_time;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    /**
     * Gets cuisine type.
     *
     * @return the cuisine type
     */
    public String getCuisine_type() {
        return cuisine_type;
    }

    /**
     * Sets cuisine type.
     *
     * @param cuisine_type the cuisine type
     */
    public void setCuisine_type(String cuisine_type) {
        this.cuisine_type = cuisine_type;
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

    /**
     * Overrides the `toString` method to provide a formatted representation of the DB2024TEAM07_Restaurant object.
     *
     * @return a string representation of the restaurant
     */
    @Override
    public java.lang.String toString() {
        return "Restaurant{" +
                "res_name='" + res_name + '\'' +
                ", res_id=" + res_id +
                ", phone_num='" + phone_num + '\'' +
                ", address='" + address + '\'' +
                ", operating_hours='" + operating_hours + '\'' +
                ", break_time='" + break_time + '\'' +
                ", rating=" + rating +
                ", cuisine_type='" + cuisine_type + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
