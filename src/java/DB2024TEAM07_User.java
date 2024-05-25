
public class DB2024TEAM07_User {
    //user_id, user_pw, name, student_id, email, location
    private String user_id;
    private String user_pw;
    private String name;
    private int student_id;
    private String email;
    private String location;

    public DB2024TEAM07_User(String user_id, String user_pw, String name, int student_id, String email, String location) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.name = name;
        this.student_id = student_id;
        this.email = email;
        this.location = location;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
