//뷰를 위한 VO

public class DB2024TEAM07_UserVO{
    private String user_id;
    private String name;
    private String email;

    public DB2024TEAM07_UserVO() {
    }

    public DB2024TEAM07_UserVO(String user_id, String name, String email) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}