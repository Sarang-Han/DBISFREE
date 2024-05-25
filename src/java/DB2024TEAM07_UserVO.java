//뷰를 위한 VO

public class DB2024TEAM07_UserVO{
    private int user_id;
    private String name;
    private String email;

    public DB2024TEAM07_UserVO() {
    }

    public DB2024TEAM07_UserVO(int user_id, String name, String email) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
    }

    public int getUser_id(){
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}