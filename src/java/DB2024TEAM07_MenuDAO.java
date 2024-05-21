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
        String Q = "INSERT INTO DB2024_Menu (menu_id, menu_name, res_id, price, menu_comment) VALUES (?, ?, ?, ?, ?)";
        try (
                conn =Database.getConnection() ;
                pStmt = conn.prepareStatement(Q);
            ){
            try {
                conn.setAutoCommit(false);

                pStmt.setString(1, menu.getMenu_id());
                pStmt.setString(2, menu.getMenu_name());
                pStmt.setInt(3, menu.getRes_id());
                pStmt.setInt(4, menu.getPrice());
                pStmt.setString(5, menu.getMenu_comment());

                rs = pStmt.executeUpdate();
                conn.commit();
                System.out.println("transaction 성공");

                return rs;
            } catch (SQLException e1) {
                e1.printStackTrace();
                try {
                    if (conn != null) {
                        conn.rollback();
                        System.out.println("롤백 성공");
                    }
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            conn.setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    //메뉴 조회 - res_id로 조회해서 그 식당에 있는 메뉴 전부 조회.
    public int search(int res_id) {
        String Q = "SELECT * FROM DB2024TEAM07_Menu WHERE res_id=?"
        try (
                conn =Database.getConnection() ;
                pStmt = conn.prepareStatement(Q);
            ){
            try {
                conn.setAutoCommit(false);

                pStmt.setInt(1, res_id);

                rs = pStmt.executeUpdate();
                conn.commit();
                System.out.println("transaction 성공");

                return rs;
            } catch (SQLException e1) {
                e1.printStackTrace();
                try {
                    if (conn != null) {
                        conn.rollback();
                        System.out.println("롤백 성공");
                    }
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            conn.setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    //메뉴 수정
    public int update(DB2024TEAM07_Menu menu, int pRes_id, int pMenu_id){
        String Q = "UPDATE DB2024TEAM07_Menu menu SET menu_id=?, menu_name=?, res_id=?, price=?, menu_comment=? WHERE res_id=? AND menu_id=?"
        try (
                conn =Database.getConnection() ;
                pStmt = conn.prepareStatement(Q);
            ){
            try {
                conn.setAutoCommit(false);

                pStmt.setString(1, menu.getMenu_id());
                pStmt.setString(2, menu.getMenu_name());
                pStmt.setInt(3, menu.getRes_id());
                pStmt.setInt(4, menu.getPrice());
                pStmt.setString(5, menu.getMenu_comment());
                pStmt.setString(6, pRes_id);
                pStmt.setString(7, pMenu_id);

                rs = pStmt.executeUpdate();
                conn.commit();
                System.out.println("transaction 성공");

                return rs;
            } catch (SQLException e1) {
                e1.printStackTrace();
                try {
                    if (conn != null) {
                        conn.rollback();
                        System.out.println("롤백 성공");
                    }
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            conn.setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    //메뉴 삭제
    public int delete(int res_id, int menu_id){
        try (
                conn =Database.getConnection() ;
        pStmt = conn.prepareStatement(Q);
            ){
            try {
                conn.setAutoCommit(false);

                pStmt.setInt(1, res_id);
                pStmt.setString(2, menu_id);

                rs = pStmt.executeUpdate();
                conn.commit();
                System.out.println("transaction 성공");

                return rs;
            } catch (SQLException e1) {
                e1.printStackTrace();
                try {
                    if (conn != null) {
                        conn.rollback();
                        System.out.println("롤백 성공");
                    }
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            conn.setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}