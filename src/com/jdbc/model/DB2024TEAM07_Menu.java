package com.jdbc.model;

/**
 * DB 2024 team 07 menu.
 */
public class DB2024TEAM07_Menu{
    /**
     * The Menu id.
     */
    int menu_id;
    /**
     * The Menu name.
     */
    String menu_name;
    /**
     * The Res id.
     */
    int res_id;
    /**
     * The Price.
     */
    int price;

    /**
     * Instantiates a new Db 2024 team 07 menu.
     *
     * @param menu_id   the menu id
     * @param menu_name the menu name
     * @param res_id    the res id
     * @param price     the price
     */
    public DB2024TEAM07_Menu(int menu_id, String menu_name, int res_id, int price) {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.res_id = res_id;
        this.price = price;
    }

    /**
     * Gets menu id.
     *
     * @return the menu id
     */
//getter,setter
    public int getMenu_id() {
        return menu_id;
    }

    /**
     * Sets menu id.
     *
     * @param menu_id the menu id
     */
    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    /**
     * Gets menu name.
     *
     * @return the menu name
     */
    public String getMenu_name() {
        return menu_name;
    }

    /**
     * Sets menu name.
     *
     * @param menu_name the menu name
     */
    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
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
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(int price) {
        this.price = price;
    }


    /**
     * Overrides the `toString` method to provide a formatted representation of the DB2024TEAM07_Menu object.
     *
     * @return a string representation of the menu item
     */
    @Override
    public String toString() {
        return "DB2024TEAM07_Menu{" +
                "menu_id=" + menu_id +
                ", menu_name='" + menu_name + '\'' +
                ", res_id=" + res_id +
                ", price=" + price +
                '}';
    }
}