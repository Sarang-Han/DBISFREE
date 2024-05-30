-- 기존에 존재하는 테이블 삭제 -----------------------------------------------------------------------
DROP DATABASE IF EXISTS  DB2024TEAM07;

-- 데이터베이스 생성 -----------------------------------------------------------------------
CREATE DATABASE DB2024TEAM07 CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE DB2024TEAM07;

-- 테이블 생성 -----------------------------------------------------------------------
-- 1. DB2024_User: 유저 정보에 관한 테이블
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

-- 2. DB2024_Restaurant: 식당 정보에 관한 테이블
CREATE TABLE DB2024_Restaurant(
  res_name VARCHAR(200) NOT NULL,
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

-- 3. DB2024_Menu: DB2024_Restaurant 테이블의 식당 메뉴 테이블
-- 레스토랑 -> 메뉴 -> 리뷰가 제3정규형으로 변환된 것
CREATE TABLE DB2024_Menu(
-- 메뉴 id, 메뉴 이름, 식당 이름, 메뉴 가격, 메뉴 설명 속성
    menu_id INT,
    menu_name VARCHAR(50),
    res_id INT,
    price INT,
    menu_comment VARCHAR(100),

-- 특정 식당의 특정 메뉴라는 점이 각각의 투플을 구별한다
    PRIMARY KEY(menu_id, res_id),
-- 참조하고 있던 식당이 사라지면 메뉴들도 전부 사라지는 정책 선택
    FOREIGN KEY(res_id) REFERENCES DB2024_Restaurant(res_id) ON DELETE CASCADE
);

-- 4. DB2024_Review: DB2024_Menu 테이블의 메뉴에 대한 리뷰 테이블
-- 레스토랑 -> 메뉴 -> 리뷰가 제3정규형으로 변환된 것
CREATE TABLE DB2024_Review (
   review_id INT AUTO_INCREMENT,
   user_id VARCHAR(50) NOT NULL,
   menu_id INT,
-- 0, 1, 2, 3, 4, 5점만 입력할 수 있게 설정
   rating INT NOT NULL CHECK(rating>-1 AND rating<6),
   review_content VARCHAR(500),

   PRIMARY KEY (review_id),
   FOREIGN KEY (user_id) REFERENCES DB2024_User(user_id),
   FOREIGN KEY (menu_id) REFERENCES DB2024_Menu(menu_id) ON DELETE SET NULL
);

-- 5. DB2024_Rating: DB2024_Restaurant과 DB2024_Review간의 관계*를 mapping한 테이블
-- *: DB2024_Review 테이블에서의 GROUP BY(resid) AVG(rating) 값 -> DB2024_Restaurant의 rating 값
-- DB2024_Review 테이블에 투플이 삽입될 때마다 DB2024_Rating 테이블에도 투플을 삽입하는 연산 필수
CREATE TABLE DB2024_Rating (
   review_id INT,
   res_id INT NOT NULL,
   rating INT,

-- 유일한 값을 가지는 리뷰 아이디가 각각의 투플을 구별한다
   PRIMARY KEY (review_id),
-- 이 테이블의 투플은 이 테이블이 참조하는 '리뷰', '식당'이 사라질 때 같이 삭제된다.
   FOREIGN KEY(review_id) REFERENCES DB2024_Review(review_id) ON DELETE CASCADE,
   FOREIGN KEY(rating) REFERENCES DB2024_Review(review_id),
   FOREIGN KEY(res_id) REFERENCES DB2024_Restaurant(res_id) ON DELETE CASCADE
);

-- 릴레이션 확인 -----------------------------------------------------------------------
-- 제대로 생겼는지 확인 용도.
/*
SHOW TABLES;
DESCRIBE DB2024_User;
DESCRIBE DB2024_Restaurant;
DESCRIBE DB2024_Menu;
DESCRIBE DB2024_Review;
DESCRIBE DB2024_Rating;
*/

-- 데이터 삽입 --------------------------------------------------------------------------
-- 평점이 없는 식당은 NULL 값 저장 -----------------------------------------------------------
INSERT INTO DB2024_Restaurant VALUES
      ('모미지식당', 1, '070-4154-2000', '서울 서대문구 이화여대7길 24 2층', '월~금 11:00~20:30, 토 11:30~20:30', '월~토 15:00~17:00', '4', '일식', '정문'),
      ('낭만식탁', 2, '02-312-1238', '서울 서대문구 이화여대5길 6 1층', '월~토 11:00~20:00', '월~토 15:00~17:00', NULL, '일식', '정문'),
      ('원즈오운', 3, '02-313-3190', '서울특별시 서대문구 이화여대길 20 1층', '월~토 10:00~21:00', NULL, '3', '베이커리', '정문'),
      ('심플리스트', 4, '010-2583-3190', '서울 서대문구 이화여대길 24 2층', '월~토 11:30~21:30', '월~토 15:00~17:00', '4', '양식', '정문'),
      ('초식곳간', 5, '02-365-5679', '서울 서대문구 이화여대2가길 19 1층', '월~금 11:00~19:30', NULL, '4', '샐러드', '정문'),
      ('유아케도쿄', 6, '02-6401-7991', '서울 서대문구 이화여대3길 28 101호', '월~토 11:00~21:00', NULL, '3', '일식', '정문'),
      ('우미마루', 7, NULL, '서울특별시 서대문구 신촌역로 18 1층', '월~일 11:00~21:00', '월~일 15:00~17:00', '3', '일식', '정문'),
      ('아콘스톨', 8, '02-364-1301', '서울특별시 서대문구 신촌역로 17 1층 110호', '월~금, 일 11:00~21:00', NULL, '5', '도시락', '정문'),
      ('진돈부리', 9, '010-4726-7604', '서울 서대문구 신촌로 149 신촌 자이엘라 B104', '월~토 17:00~20:20', NULL, NULL, '일식', '정문'),
      ('어바웃샤브', 10, '02-6402-4949', '서울 서대문구 이화여대8길 2 201호', NULL, NULL, '4', '샤브샤브', '정문'),
      ('소오밥집', 11, '02-6397-8917', '서울 서대문구 이화여대길 50-10 1층', '월~토, 일 10:30~20:00', '월~토, 일 15:00~17:00', '3', '일식', '정문'),
      ('까이식당', 12, '070-7779-8899', '서울 서대문구 이화여대2가길 24 1층', '월~금 11:00~20:00', '월~금 15:00~17:00', '4', '아시안', '정문'),
      ('아민 이화', 13, '02-363-0113', '서울 서대문구 이화여대길 52-31 1층', '월~일 11:00~21:00', NULL, '4', '양식', '정문'),
      ('유소바', 14, '070-8224-7956', '서울 서대문구 이화여대2길 4 1층', '월~일 11:30~21:30', '월~일 15:00~17:00', '2', '일식', '정문'),
      ('포히엔베트남쌀국수 이대점', 15, '02-365-1985', '서울 서대문구 이화여대3길 2 1층', '월~일 11:00~21:00', NULL, '4', '아시안', '정문'),
      ('마더락 신촌점', 16, '02-365-1757', '서울특별시 서대문구 이화여대3길 29 101호', '월~일 9:00~18:00', NULL, '2', '도시락', '정문'),
      ('티아라 파스타', 17, '0507-1413-4268', '서울 서대문구 이화여대7길 24 2층', '월~금 10:30~20:00', NULL, '4', '양식', '정문'),
      ('스탠바이키친', 18, '02-365-6353', '서울 서대문구 연대동문길 49 1층 101호', '월~금 10:30~20:00', '월~금 15:00~16:00', '4', '양식', '후문'),
      ('이로운 밥상', 19, '02-365-1245', '서울 서대문구 연대동문길 27-6 2층', '월~토 11:00~21:00', '월~토 15:00~17:00', '3', '한식', '후문'),
      ('화가와요리사 이대후문점', 20, '02-364-1970', '서울 서대문구 성산로 539', '월~금 11:00~20:00, 토 11:00~14:00', NULL, '3', '일식', '후문'),
      ('헐리우드', 21, '02-723-4888', '서울 서대문구 성산로 551 1층', '월~일 10:00~21:00', NULL, '3', '양식', '후문'),
      ('식탁 하늬솔점', 22, '02-362-0777', '서울 서대문구 성산로 527 하늬솔빌딩', '월~일 11:00~20:00', NULL, '1', '양식', '후문'),
      ('불밥', 23, '02-362-9833', '서울 서대문구 이화여대8길 11 2층', '월~토 11:00~22:00', '월~토 15:00~16:30', '3', '한식', '정문'),
      ('수라 이대점', 24, '02-392-9333', '서울 서대문구 이화여대2가길 20 1층', '월~금 11:00~21:00', NULL, '3', '한식', '정문'),
      ('의원상', 25, NULL, '서울 서대문구 이화여대길 72-5 1층', '월,화,목,금,토,일 10:30~21:30', NULL, '3', '중식', '정문'),
      ('화라라마라탕', 26, '02-313-0158', '서울 서대문구 이화여대길 76 1층', '월~일 10:00~22:00', NULL, '3', '중식', '정문'),
      ('마더린더베이글', 27, '070-7758-3030', '서울 서대문구 이화여대5길 5 지상 1층', '월~금 8:30~16:30, 토 10:00~16:30', '', NULL, '베이커리', '정문'),
      ('이화성', 28, '02-393-8511', '서울 서대문구 이화여대길 50-8 지하 1층', '화~일 10:30~20:00', NULL, '2', '중식', '정문'),
      ('청년밥상문간 이화여자대학교점', 29, '0507-1344-6031', '서울 서대문구 이화여대길 52-39 지하 1층', '월~금 11:00~20:00', '월~금 16:00~17:00', '4', '한식', '정문'),
      ('등촌샤브칼국수 신촌점', 31, '0507-1398-7850', '서울 서대문구 신촌역로 43 1층', '월~일 11:00~22:00', '월~일 15:00~17:00', '5', '샤브샤브', '정문'),
      ('슬로우캘리 이대점', 32, '0507-1391-7188', '서울 서대문구 이화여대길 78 가동 1층', '월~일 11:00~21:00', '월~일 15:00~17:00', NULL, '샐러드', '정문');

INSERT INTO DB2024_User VALUES
	("s2eojeong", "s2eojeong", "조서정", 2276305, "s2eojeong@gmail.com", "후문"),
    ("astralfinance", "astralfinance", "한사랑", 2271064, "astralfinance", "후문"),
    ("cannes7", "cannes7", "고은서", 2122004, "cannes7@ewhain.net", "정문"),
    ("meanwest", "meanwest", "김민서", 2276046, "meanwestk@gmail.com", "후문"),
    ("chacha091", "chacha091", "차현주", 2276321, "chacha09@ewhain.net", "정문");


-- 뷰 생성 -----------------------------------------------------------------------
/*
-- 카테고리 테이블(뷰로 대체)
CREATE TABLE DB2024_Category(
cuisine_type VARCHAR(50),
    PRIMARY KEY (cuisine_type)
);
*/
-- restaurant dao의 구성 상 꼭 필요할 지 의문이 들기는 하다
-- > 식당 페이지에 처음 접속했을 땐 상단에 cuisine_type 별로 버튼을 만들고 그 아래에 식당 리스트가 쫙 있고
--  cuisine_type 버튼을 누르면 type 별로 식당 리스트가 바뀌는걸로 해도 좋을 것 같아요.
-- 메인 페이지에서 보여주는 용도로 사용해도 괜찮을듯(예: 배민)
/* pStmt로  사용하거나, 아님 다른 방식으로 사용하거나 해야할 것 같아요
CREATE VIEW DB2024_Category AS
	(SELECT res_id, res_name
	FROM DB2024_Restaurant
	WHERE cuisine_type=?);
*/

-- 보안용 유저정보 확인 뷰(다른 유저의 이름과 이메일만 확인 가능)
CREATE VIEW DB2024_OtherUser AS
(SELECT user_id, `name`, email
FROM DB2024_User);
-- SELECT * FROM DB2024_OtherUser;

-- 사용자가 한 삭당 안에 있는 메뉴들을 검색할 때 res_name 을 이용하므로 DB2024_Restaurant 와 DB2024_Menu 의 조인을 통해 res_name 을 받아와야함.
CREATE VIEW DB2024_MenuView AS
(SELECT r.res_name, m.menu_name, m.price, m.menu_comment
FROM DB2024_Menu m JOIN DB2024_Restaurant r
                        ON m.res_id = r.res_id);
-- SELECT * FROM DB2024_MenuView;

-- 인덱스 생성 -----------------------------------------------------------------------
-- DB2024_Rating.res_id: 특정 가게의 평점 평균을 구할 때 DB2024_Rating 테이블의 res_id가 자주 사용됨
CREATE INDEX DB2024_idx_AvgRating
    ON DB2024_Rating (res_id);

-- DB2024_Review.user_id: 특정 유저의 리뷰를 몰아볼 때 DB2024_Review 테이블의 user_id가 자주 사용됨
CREATE INDEX DB2024_idx_Review
    ON DB2024_Review (user_id);

-- DB2024_Menu.res_id: Restaurant별로 메뉴를 검색
CREATE INDEX DB2024_idx_Menu
    ON DB2024_Menu(res_id);

-- DB2024_Restaurant.cuisine_type: cuisine_type별로 Restaurant를 검색
CREATE INDEX DB2024_idx_Restaurant
    ON DB2024_Restaurant(cuisine_type);


-- 테이블 삭제 (맨 윗줄 코드로 대체) -----------------------------------------------------------------------
-- DROP DATABASE DB2024TEAM07;