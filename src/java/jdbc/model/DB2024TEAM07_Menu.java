package jdbc.model;

public class DB2024TEAM07_Menu{
    int menu_id;
    String menu_name;
    int res_id;
    int price;

    //생성자
    public DB2024TEAM07_Menu(int menu_id, String menu_name, int res_id, int price) {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.res_id = res_id;
        this.price = price;
    }

    //getter,setter
    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    //toString 메소드 오버라이딩. 원하는 형식으로 출력.

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