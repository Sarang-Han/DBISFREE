//Database 파일의 커넥션과의 연결 필요
//error라고 적힌 부분은 조치 필요(고민)-롤백/무시/등

//구현됨:
//구현해야 됨: 리뷰 작성, 리뷰값 반환, 리뷰 리스트(페이지 단위로 확인을 위한 리스트 반환), 다음 리뷰 반환, 리뷰 수정, 리뷰 삭제

public class ReviewDAO {
    private Connection conn;
    private PreparedStatement pStmt;
    private Statement stmt;
    private ResultSet rs;

    public ReviewDAO() {
        //Database conn, pstmt, rs 받아오기
        this.conn = Database.getInstance().getConnection();
    }
}