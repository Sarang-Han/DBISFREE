public class DB2024TEAM07_Menu{
    int menu_id;
    String menu_name;
    int res_id;
    int price;
    String menu_comment;

    //생성자
    public DB2024TEAM07_Menu(int menu_id, String menu_name, int res_id, int price, String menu_comment) {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.res_id = res_id;
        this.price = price;
        this.menu_comment = menu_comment;
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

    public String getMenu_comment() {
        return menu_comment;
    }

    public void setMenu_comment(String menu_comment) {
        this.menu_comment = menu_comment;
    }

    //toString 메소드 오버라이딩. 원하는 형식으로 출력.
    @java.lang.Override
    public java.lang.String toString() {
        return "Menu{" +
                "menu_id=" + menu_id +
                ", menu_name='" + menu_name + '\'' +
                ", res_id=" + res_id +
                ", price=" + price +
                ", menu_comment='" + menu_comment + '\'' +
                '}';
    }
}