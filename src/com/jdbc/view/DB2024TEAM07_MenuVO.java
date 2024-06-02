package com.jdbc.view;

public class DB2024TEAM07_MenuVO {
    private String res_name;
    private String menu_name;
    private int price;

    public DB2024TEAM07_MenuVO(String res_name, String menu_name, int price) {
        this.res_name = res_name;
        this.menu_name = menu_name;
        this.price = price;
    }

    public DB2024TEAM07_MenuVO() {

    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
