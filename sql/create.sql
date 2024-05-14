DROP DATABASE IF EXISTS  DB2024TEAM07;

-- 데이터베이스 생성
CREATE DATABASE DB2024TEAM07 CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE DB2024TEAM07;

-- 테이블 생성
CREATE TABLE DB2024_User(
	user_id VARCHAR(50),
    user_pw VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    student_id INT,
    email VARCHAR(50) CHECK (email LIKE '%@%'),
-- 주의: Restaurant 테이블의 location 속성과 이름만 같고 다른 의미 가짐 
    location VARCHAR(100), 
    
    PRIMARY KEY (user_id)
);

/*
-- 카테고리 테이블(뷰로 대체)
CREATE TABLE DB2024_Category(
cuisine_type VARCHAR(50), 
    PRIMARY KEY (cuisine_type)
);
*/

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
    -- ,FOREIGN KEY(cuisine_type) REFERENCES DB2024_Category(cuisine_type) ON DELETE SET NULL
);


CREATE TABLE DB2024_Menu(
	menu_name VARCHAR(50), 
    res_id INT NOT NULL,
    price INT, 
    menu_comment VARCHAR(100),
    
    PRIMARY KEY(menu_name), 
    FOREIGN KEY(res_id) REFERENCES DB2024_Restaurant(res_id) ON DELETE CASCADE
);

CREATE TABLE DB2024_Review (
	review_id INT AUTO_INCREMENT,
	user_id VARCHAR(50) NOT NULL, 
	menu_name VARCHAR(50) NOT NULL,
	rating INT NOT NULL CHECK(rating>-1 AND rating<6), -- 0, 1, 2, 3, 4, 5점만 입력할 수 있게 설정 
	review_content VARCHAR(500),
  
	PRIMARY KEY (review_id),
	FOREIGN KEY (user_id) REFERENCES DB2024_User(user_id),
	FOREIGN KEY(menu_name) REFERENCES DB2024_Menu(menu_name)
);

-- DB2024_Restaurant과 DB2024_Review간의 관계를 mapping한 테이블 
CREATE TABLE DB2024_Rating (
	res_id INT,
    review_id INT,
    
    PRIMARY KEY (res_id, review_id),
    FOREIGN KEY(res_id) REFERENCES DB2024_Restaurant(res_id) ON DELETE CASCADE,
    FOREIGN KEY(review_id) REFERENCES DB2024_Review(review_id) ON DELETE CASCADE
);

-- 릴레이션 확인
SHOW TABLES;
DESCRIBE DB2024_User;
DESCRIBE DB2024_Restaurant;
DESCRIBE DB2024_Menu;
DESCRIBE DB2024_Review;
DESCRIBE DB2024_Rating;


-- 테이블 삭제 
DROP DATABASE DB2024TEAM07;