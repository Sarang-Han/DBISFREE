// category 뷰

public class DB2024TEAM07_CategoryVO {
    private String cuisine_type;
    private String res_name;

    // 생성자
    public DB2024TEAM07_CategoryVO(String cuisine_type, String res_name) {
        this.cuisine_type = cuisine_type;
        this.res_name = res_name;
    }

    // getter
    public String getCuisine_type() {
        return cuisine_type;
    }

    public String getRes_name() {
        return res_name;
    }
}