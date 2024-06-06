```

  ███████╗    ███╗   ███╗ █████╗ ████████╗███████╗ █████╗ ███████╗██╗   ██╗
  ██╔════╝    ████╗ ████║██╔══██╗╚══██╔══╝██╔════╝██╔══██╗██╔════╝╚██╗ ██╔╝
  █████╗█████╗██╔████╔██║███████║   ██║   █████╗  ███████║███████╗ ╚████╔╝
  ██╔══╝╚════╝██║╚██╔╝██║██╔══██║   ██║   ██╔══╝  ██╔══██║╚════██║  ╚██╔╝
  ███████╗    ██║ ╚═╝ ██║██║  ██║   ██║   ███████╗██║  ██║███████║   ██║
  ╚══════╝    ╚═╝     ╚═╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝   ╚═╝

  2024-1 Database
                                           
```

# E-MatEASY

## 🎵 How to Run

### Prerequisites
1. Java Development Kit (JDK): Make sure you have JDK installed on your system.
2. JDBC Driver: Download the Mysql JDBC driver for your database.
3. Database Management System (DBMS): Ensure you have a running instance of the DBMS that your project uses.

### Steps to Run the Project
1. Create the Database:
   - Navigate to the sql folder in the project directory. 
   - Use the provided SQL scripts to create and populate the database. 
   - You can execute these scripts using your database management tool (e.g., MySQL Workbench, pgAdmin, etc.).

2. Set Up Your Development Environment:
   - Open your Java IDE (e.g., IntelliJ IDEA, Eclipse).
   - Import the project into your IDE.

3. Configure JDBC Connection:
   - Add the JDBC driver to your project dependencies.
   - Configure the JDBC connection in your project. This typically involves setting up the database URL, username, and password in a configuration file or directly in the code.

4. Run the Main Class:
   - Locate the main class in your project, usually named something like DB2024TEAM07_UserMain. 
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
<img width="800" alt="요구명세서 수정" src="https://github.com/Sarang-Han/DBISFREE/assets/144914664/d61c6fed-ace6-48f8-8209-fb6435938069">

## 🖼️ Diagrams

### ER Diagram
<img width="600" alt="ER" src="https://github.com/Sarang-Han/DBISFREE/assets/144914664/e5f14eca-ec25-45f4-8e1a-4d1a04e856a7">

### Schema Diagram
<img width="800" alt="ER" src="https://github.com/Sarang-Han/DBISFREE/assets/144914664/e0d260d2-5fa9-469c-9e95-8ac978517666">

## 🧩 Contributors

