```

  ███████╗    ███╗   ███╗ █████╗ ████████╗███████╗ █████╗ ███████╗██╗   ██╗
  ██╔════╝    ████╗ ████║██╔══██╗╚══██╔══╝██╔════╝██╔══██╗██╔════╝╚██╗ ██╔╝
  █████╗█████╗██╔████╔██║███████║   ██║   █████╗  ███████║███████╗ ╚████╔╝
  ██╔══╝╚════╝██║╚██╔╝██║██╔══██║   ██║   ██╔══╝  ██╔══██║╚════██║  ╚██╔╝
  ███████╗    ██║ ╚═╝ ██║██║  ██║   ██║   ███████╗██║  ██║███████║   ██║
  ╚══════╝    ╚═╝     ╚═╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝   ╚═╝

  2024-1 Database TEAM07                 
```

## 🍽️ E-MATEASY 🍽️
<br>

**이**대생을 위한 **맛**집 **EASY**하게 찾기!

콘솔로 구현한 이대 맛집 데이터베이스 응용 프로그램입니다.

<br>

### ✏️  Project Outline

<table>
  <tr>
    <th>프로젝트 명</th>
    <th>개발기간</th>
    <th>프로젝트 성격</th>
    <th>개발인원</th>
  </tr>
  <tr>
    <td>E-MATEASY</td>
    <td>04.30 ~ 06.07</td>
    <td>JAVA 콘솔 프로젝트</td>
    <td>5명</td>
  </tr>
</table>

### 🔑  Key Feature

<table>
  <tr>
    <th>역할</th>
    <th>기능</th>
  </tr>
  <tr>
    <td rowspan="3"><strong>User</strong></td>
    <td>유저 로그인, 회원가입</td>
  </tr>
  <tr>
    <td>맛집, 메뉴 데이터 검색/조회</td>
  </tr>
  <tr>
    <td>리뷰 추가 및 조회</td>
  </tr>
  <tr>
    <td rowspan="4"><strong>Admin</strong></td>
    <td>어드민 로그인</td>
  </tr>
  <tr>
    <td>맛집, 메뉴, 리뷰, 유저 데이터 조회</td>
  </tr>
  <tr>
    <td>맛집, 메뉴, 리뷰, 유저 데이터 추가</td>
  </tr>
  <tr>
    <td>맛집, 메뉴, 리뷰, 유저 데이터 수정, 삭제</td>
  </tr>
</table>

### 🛠️  Tools

<table>
  <tr>
    <th>개발언어</th>
    <th>버전관리</th>
    <th>개발도구</th>
  </tr>
  <tr>
    <td>Java</td>
    <td>Github, Git</td>
    <td>IntelliJ IDE</td>
  </tr>
</table>

<br>

## 🎵 How to Run

### Prerequisites

1. **Java 개발 키트 (JDK)**: 시스템에 JDK가 설치되어 있는지 확인하세요.
2. **JDBC 드라이버**: Mysql JDBC 드라이버를 다운로드하세요. (MySQL JDBC 드라이버)
3. **데이터베이스 관리 시스템 (DBMS)**: 사용 중인 DBMS가 실행 중인지 확인하세요. (MySQL DBMS)

### Steps to Run the Project

1. **데이터베이스 생성:**
   - sql 폴더로 이동하세요.
   - Mysql DBMS를 실행하고 `create.sql`을 사용하여 데이터베이스를 생성하세요.
   - DBMS에서 사용자(DB2024TEAM07) 생성 쿼리를 실행하세요.
   ```
      CREATE USER DB2024TEAM07 IDENTIFIED BY 'DB2024TEAM07';
   ```


2. **개발 환경 설정:**
   - 원하는 Java IDE를 열어주세요 (예: IntelliJ IDE, Eclipse). 
   - 프로젝트를 가져오세요.


3. **JDBC 연결 설정:**
   - 프로젝트 의존성에 JDBC 드라이버를 추가하세요.
   - JDBC 연결을 설정하세요 (데이터베이스 URL, 사용자명, 비밀번호).
   ```
      static final String DB_URL = "jdbc:mysql://localhost:3306/DB2024TEAM07";
      static final String USER = "DB2024TEAM07";
      static final String PASS = "DB2024TEAM07";
   ```


4. **Main 클래스 실행:**
   - 프로젝트의 src 폴더에서 `DB2024TEAM07_Main`을 찾으세요.
   - 이 클래스의 main 메서드를 실행하여 애플리케이션을 시작하세요.


## 📂 Project Structure

```
📂 src
└── com
    ├── app
    │   ├── DB2024TEAM07_Main                # 프로그램 실행 진입점
    │   ├── DB2024TEAM07_UserMain            # 유저 메인 화면
    │   └── DB2024TEAM07_AdminMain           # 관리자 메인 화면
    ├── jdbc
    │   ├── database          
    │   │   ├── DB2024TEAM07_Database        # 데이터베이스 연결 및 공통 작업 처리
    │   │   ├── DB2024TEAM07_UserDAO         # User 테이블 CRUD 작업 처리
    │   │   ├── DB2024TEAM07_RestaurantDAO   # Restaurant 테이블 CRUD 작업 처리
    │   │   ├── DB2024TEAM07_ReviewDAO       # Review 테이블 CRUD 작업 처리
    │   │   └── DB2024TEAM07_MenuDAO         # Menu 테이블 CRUD 작업 처리
    │   ├── model
    │   │   ├── DB2024TEAM07_User            # 모델 클래스 (DTO)
    │   │   ├── DB2024TEAM07_Restaurant      # 모델 클래스 (DTO)
    │   │   ├── DB2024TEAM07_Review          # 모델 클래스 (DTO)
    │   │   └── DB2024TEAM07_Menu            # 모델 클래스 (DTO)
    │   └── view         
    │       ├── DB2024TEAM07_ResReviewVO     # Restaurant, Review 뷰 클래스
    │       └── DB2024TEAM07_UserVO          # User 뷰 클래스
    └── manager
         ├── DB2024TEAM07_RestaurantManager  # 식당 관련 비즈니스 로직 처리
         ├── DB2024TEAM07_MenuManager        # 메뉴 관련 비즈니스 로직 처리
         ├── DB2024TEAM07_ReviewManager      # 리뷰 관련 비즈니스 로직 처리
         └── DB2024TEAM07_UserManager        # 유저 관련 비즈니스 로직 처리
```


## 📋 Requirements
<img width="900" alt="요구명세수정" src="https://github.com/Sarang-Han/DBISFREE/assets/144914664/21c76478-b712-4e65-9f66-dd4c9fe3fd70">


## 🖼️ Diagrams

### ER Diagram
<img width="570" alt="ERD" src="https://github.com/Sarang-Han/DBISFREE/assets/144914664/6f097d95-ce81-41fa-bc8a-2859e483870a">

### Database schema Diagram
<img width="570" alt=db src="https://github.com/Sarang-Han/DBISFREE/assets/144914664/23397de0-6136-47c3-9993-d4a26abf508a">

## 🧩 Contributors
<img width="900" alt="역할분담" src="https://github.com/Sarang-Han/DBISFREE/assets/144914664/d3166e10-722b-4911-869f-b072e3dc5460">

<br>
<br>

<table>
  <tr>
    <th>고은서</th>
    <th>김민서</th>
    <th>조서정</th>
    <th>차현주</th>
    <th>한사랑</th>
  </tr>
  <tr>
    <td><a href="https://github.com/cannes7">@cannes7</a></td>
    <td><a href="https://github.com/Min354">@Min354</a></td>
    <td><a href="https://github.com/s2eojeong">@s2eojeong</a></td>
    <td><a href="https://github.com/chacha091">@chacha091</a></td>
    <td><a href="https://github.com/Sarang-Han">@Sarang-Han</a></td>
  </tr>
</table>

