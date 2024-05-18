-- 기존에 존재하는 테이블 삭제 -----------------------------------------------------------------------
DROP DATABASE IF EXISTS  DB2024TEAM07;

-- 데이터베이스 생성 -----------------------------------------------------------------------
CREATE DATABASE DB2024TEAM07 CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE DB2024TEAM07;

-- 테이블 생성 -----------------------------------------------------------------------
-- DB2024_User: 유저 정보에 관한 테이블
CREATE TABLE DB2024_User(
    -- 유저 아이디, 비밀번호, 이름, 학번, 이메일, 장소 속성
	user_id VARCHAR(50),
    user_pw VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    -- 학번, 이메일, 장소는 필수로 작성하지 않아도 되게끔 설정되었다.
    student_id INT,
    email VARCHAR(50) CHECK (email LIKE '%@%'),
    -- 주의: Restaurant 테이블의 location 속성과 이름만 같고 다른 의미 가짐
    location VARCHAR(100), 

    -- 유일한 값을 가지는 유저 아이디가 각각의 투플을 구분한다.
    -- > 회원가입 과정(유저 테이블에 투플 삽입)에서 아이디 중복 체크 필수
    PRIMARY KEY (user_id)
);

/*
-- 카테고리 테이블(뷰로 대체)
CREATE TABLE DB2024_Category(
cuisine_type VARCHAR(50), 
    PRIMARY KEY (cuisine_type)
);
*/

-- 조서정
-- DB2024_Restaurant: 식당 정보에 관한 테이블
CREATE TABLE DB2024_Restaurant(
	res_name VARCHAR(200),
	res_id INT NOT NULL AUTO_INCREMENT, 
	phone_num VARCHAR(20) DEFAULT NULL,
	address VARCHAR(200) DEFAULT NULL,
	operating_hours VARCHAR(100) DEFAULT NULL,
	break_time VARCHAR(100) DEFAULT NULL,
	rating decimal(2,1) DEFAULT NULL, 
	cuisine_type VARCHAR(50) DEFAULT NULL,
	location VARCHAR(50) DEFAULT NULL,

	PRIMARY KEY(res_id)
);

-- DB2024_Menu: DB2024_Restaurant 테이블의 식당에 대한 메뉴 테이블
-- 레스토랑 -> 메뉴 -> 리뷰가 제3정규형으로 변환된 것
CREATE TABLE DB2024_Menu(
    -- 메뉴 이름, 식당 이름, 메뉴 가격, 메뉴 설명 속성
	menu_name VARCHAR(50),
    res_id INT,
    price INT,
    menu_comment VARCHAR(100),

    -- 특정 식당의 특정 메뉴라는 점이 각각의 투플을 구별한다
    PRIMARY KEY(menu_name, res_id),
    -- 참조하고 있던 식당이 사라지면 메뉴들도 전부 사라지는 정책 선택
    FOREIGN KEY(res_id) REFERENCES DB2024_Restaurant(res_id) ON DELETE CASCADE
);

-- 고은서
-- DB2024_Review: DB2024_Menu 테이블의 메뉴에 대한 리뷰 테이블
-- 레스토랑 -> 메뉴 -> 리뷰가 제3정규형으로 변환된 것
CREATE TABLE DB2024_Review (
	review_id INT AUTO_INCREMENT,
	user_id VARCHAR(50) NOT NULL, 
	menu_name VARCHAR(50) NOT NULL,
    -- 0, 1, 2, 3, 4, 5점만 입력할 수 있게 설정
	rating INT NOT NULL CHECK(rating>-1 AND rating<6),
	review_content VARCHAR(500),
  
	PRIMARY KEY (review_id),
	FOREIGN KEY (user_id) REFERENCES DB2024_User(user_id),
    -- 참조하고 있던 메뉴가 사라지면 리뷰들도 전부 사라지는 정책 선택?
    -- > 메뉴 삭제 전에 안내 메세지를 띄우는 것이 필요해 보인다
	FOREIGN KEY (menu_name) REFERENCES DB2024_Menu(menu_name) ON DELETE CASCADE
);

-- DB2024_Rating: DB2024_Restaurant과 DB2024_Review간의 관계*를 mapping한 테이블
-- *: DB2024_Review 테이블에서의 GROUP BY(resid) AVG(rating) 값 -> DB2024_Restaurant의 rating 값
-- DB2024_Review 테이블에 투플이 삽입될 때마다 DB2024_Rating 테이블에도 투플을 삽입하는 연산 필수
CREATE TABLE DB2024_Rating (
    review_id INT,
    res_id INT NOT NULL,

    -- 유일한 값을 가지는 리뷰 아이디가 각각의 투플을 구별한다
    PRIMARY KEY (review_id),
    -- 이 테이블의 투플은 이 테이블이 참조하는 '리뷰', 그 리뷰가 참조하는 '메뉴', 그 리뷰가 참조하는 메뉴가 참조하는 '식당'이 사라질 때 같이 삭제된다.
    -- 리뷰가 참조하는 '메뉴', 리뷰가 참조하는 메뉴가 참조하는 '식당'이 사라지는 경우 연쇄적으로 '리뷰' 또한 삭제되므로,
    -- 참조 중인 '리뷰'가 삭제되는 경우에만 이 테이블의 투플도 같이 삭제되도록 설정되었다.
    FOREIGN KEY(review_id) REFERENCES DB2024_Review(review_id) ON DELETE CASCADE,
    FOREIGN KEY(res_id) REFERENCES DB2024_Restaurant(res_id)
);

-- 릴레이션 확인 -----------------------------------------------------------------------
-- 제대로 생겼는지 확인 용도. 필수적인 코드 아님
SHOW TABLES;
DESCRIBE DB2024_User;
DESCRIBE DB2024_Restaurant;
DESCRIBE DB2024_Menu;
DESCRIBE DB2024_Review;
DESCRIBE DB2024_Rating;


-- 테이블 삭제 (맨 윗줄 코드로 대체) -----------------------------------------------------------------------
-- DROP DATABASE DB2024TEAM07;