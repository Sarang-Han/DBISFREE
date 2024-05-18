//Database 파일의 커넥션과의 연결 필요
//error라고 적힌 부분은 조치 필요(고민)-롤백/무시/등

//구현됨: 회원가입, 로그인, 회원정보 수정, 회원탈퇴 기능
//구현해야 됨: 유저정보 확인 기능

public class UserDAO{
    private Connection conn;
    private PreparedStatement pStmt;
    private Statement stmt;
    private ResultSet rs;

    public UserDAO(){
        //Database conn, pstmt, rs 받아오기
    }

    //회원가입 기능(DB2024_User 테이블에 투플 삽입)
    public int sighUp(User user){
        String Q = "INSERT INTO DB2024_User VALUES (?, ?, ?, ?, ?, ?)";
        try{
            pStmt = conn.preparedStatement(Q);
            pStmt.setString(1, user.getUser_id());
            pStmt.setString(2, user.getUser_pw());
            pStmt.setString(3, user.getName());
            pStmt.setInt(4, user.getStudent_id());
            pStmt.setString(5, user.getEmail());
            pStmt.setString(6, user.getLocation());
            return pStmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }

    //로그인 기능(DB2024_User 테이블 검색)
    public int signIn(String user_id, String user_pw){
        String Q = "SELECT user_id FROM DB2024_User WHERE user_id = ?";
        try{
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, user_id);
            rs = pStmt.executeQuery();
            if(rs.next()){
                if(rs.getString(1).equals(user_pw))
                    return 1;   //id: 일치 / pw: 일치
                else
                    return 0;   //id: 일치 / pw: 불일치
            }
            else
                return -1;      //id: 불일치(결과 없음)
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }

    //회원정보 수정(아이디/비밀번호 입력 필요)(DB2024_User 테이블 검색, 투플값 수정)
    //수정할 내용을 담은 User 객체와, 수정하기 이전의 user id를 전달받아 수정하는 방식
    public int update(User user, String pUser_id){
        //중첩쿼리 이용되므로 오류 가능성 있는지 확인 필요
        String Q = "UPDATE DB2024_User SET user_id=?, user_pw=?, name=?, student_id=?, email=?, location=? WHERE user_id=?";
        try{
            pStmt = conn.preparedStatement(Q);
            pStmt.setString(1, user.getUser_id());
            pStmt.setString(2, user.getUser_pw());
            pStmt.setString(3, user.getName());
            pStmt.setInt(4, user.getStudent_id());
            pStmt.setString(5, user.getEmail());
            pStmt.setString(6, user.getLocation());
            pStmt.setString(7, pUser_id);
            return pStmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }

    //탈퇴 기능(DB2024_User 테이블의 투플 삭제)
    //유저가 비밀번호를 작성해야 탈퇴할 수 있는 구조
    public int delete(String user_id, String user_pw){
        String Q = "DELETE  FROM DB2024_User WHERE user_id = ?";
        try{
            pStmt = conn.prepareStatement(Q);
            pStmt.setString(1, user_id);
            rs = pStmt.executeQuery();
            if(rs.next()){
                if(rs.getString(1).equals(user_pw))
                    return 1;   //id: 일치 / pw: 일치
                else
                    return 0;   //id: 일치 / pw: 불일치
            }
            else
                return -1;      //id: 불일치(결과 없음)
        }catch(SQLException se){
            se.printStackTrace();
        }
        return -2;  //error
    }
}