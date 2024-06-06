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

Easy Way to Find Yummy Places for Ewha Students!

## 🎵 How to Run

### Prerequisites

1. **Java Development Kit (JDK):** Make sure you have JDK installed on your system.
   
2. **JDBC Driver:** Download the JDBC driver for your database (e.g., MySQL JDBC driver).
   
3. **Database Management System (DBMS):** Ensure you have a running instance of your DBMS (e.g., MySQL).

### Steps to Run the Project
1. **Create the Database:**
   - Navigate to the `sql` folder.
   - Use the provided SQL scripts to create and populate the database.

2. **Set Up Your Development Environment:**
   - Open your Java IDE (e.g., IntelliJ IDEA, Eclipse).
   - Import the project.

3. **Configure JDBC Connection:**
   - Add the JDBC driver to your project dependencies.
   - Configure the JDBC connection (database URL, username, password).

4. **Run the Main Class:**
   - Locate `DB2024TEAM07_Main` in your project.
   - Run the main method of this class to start the application.
   
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
<img width="750" alt="요구명세서 수정" src="https://github.com/Sarang-Han/DBISFREE/assets/144914664/d61c6fed-ace6-48f8-8209-fb6435938069">

## 🖼️ Diagrams

### ER Diagram
<img width="600" alt="ERD" src="https://github.com/Sarang-Han/DBISFREE/assets/144914664/6f097d95-ce81-41fa-bc8a-2859e483870a">

### Database schema Diagram
<img width="600" alt=db src="https://github.com/Sarang-Han/DBISFREE/assets/144914664/23397de0-6136-47c3-9993-d4a26abf508a">

## 🧩 Contributors
<img width="1000" alt=db src="https://github.com/Sarang-Han/DBISFREE/assets/144914664/99ecd4f3-f8af-46db-9496-6f7b142ebd41">

<br>
<br>