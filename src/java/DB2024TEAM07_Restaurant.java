public class Restaurant{
    String res_name;
    int res_id;
    String phone_num
    String address;
    String operating_hours;
    String break_time;
    float rating;
    String cuisine_type;
    String location;

    //생성자
    public Restaurant(String res_name, int res_id, String phone_num, String address, String operating_hours, String break_time, float rating, String cuisine_type, String location) {
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

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperating_hours() {
        return operating_hours;
    }

    public void setOperating_hours(String operating_hours) {
        this.operating_hours = operating_hours;
    }

    public String getBreak_time() {
        return break_time;
    }

    public void setBreak_time(String break_time) {
        this.break_time = break_time;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getCuisine_type() {
        return cuisine_type;
    }

    public void setCuisine_type(String cuisine_type) {
        this.cuisine_type = cuisine_type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //toString
    @java.lang.Override
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
