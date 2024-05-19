import java.sql.*;

//구현해야할 기능: 메뉴 등록, 메뉴 조회, 메뉴 수정, 메뉴 삭제
//건의할거: menu 테이블에 menu_id 추가? 한 식당 내에 메뉴 여러개라 메뉴 id 속성도 추가하면 좋을거같음.
public clas DB2024TEAM07_MenuDAO{
    private Connection conn;
    private PreparedStatement pStmt;
    private ResultSet rs;

    public DB2024TEAM07_MenuDAO(){
        this.conn = Database.getInstance().getConnection();
    }

    //메뉴 등록
    public int add(DB2024TEAM07_Menu menu) {
        String Q = "INSERT INTO DB2024_Menu VALUES (?, ?, ?, ?)";
        try {
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, menu.getMenu_name());
            pStmt.setInt(2, menu.getRes_id());
            pStmt.setInt(3, menu.getPrice());
            pStmt.setString(4, menu.getMenu_comment());

            return pStmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return 0;
    }

    //메뉴 조회


    //메뉴 수정
    public int search(DB2024TEAM07_Menu menu){

    }

    //메뉴 삭제
    public int search(DB2024TEAM07_Menu menu){

    }

}