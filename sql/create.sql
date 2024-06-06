-- 기존에 존재하는 테이블 삭제 -----------------------------------------------------------------------
DROP DATABASE IF EXISTS  DB2024TEAM07;

-- 데이터베이스 생성 -----------------------------------------------------------------------
CREATE DATABASE DB2024TEAM07 CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE DB2024TEAM07;

-- 테이블 생성 -----------------------------------------------------------------------
/*
 1. DB2024_User: 유저 정보에 관한 테이블
 user_id: PK, 유저 아이디 정보
 user_pw: 유저 비밀번호 정보
 name: 유저 이름(닉네임) 정보
 student_id: 유저 학번 정보
 email: 유저 이메일 정보
 location: 유저 장소 정보(주의! Restaurant 테이블의 location 속성과 이름만 같고 다른 의미 가짐)
 */
CREATE TABLE DB2024_User(
-- 유저 아이디, 비밀번호, 이름, 학번, 이메일, 장소 속성
    user_id VARCHAR(50),
    user_pw VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    student_id INT,
    email VARCHAR(50) CHECK (email LIKE '%@%'),
    location VARCHAR(100),

-- 유일한 값을 가지는 유저 아이디가 각각의 투플을 구분한다.
-- > 회원가입 과정(유저 테이블에 투플 삽입)에서 아이디 중복 체크 필수
    PRIMARY KEY (user_id)
);

/*
 2. DB2024_Restaurant: 식당 정보에 관한 테이블
     res_name: 식당 이름 정보
     res_id: PK. 투플이 추가될 때마다 자동적으로 1씩 추가되어 입력된다.
     phone_num: 식당 전화번호 정보
     address: 식당 주소 정보
     operating_hours: 식당 운영시간 정보
     break_time: 식당 휴식시간 정보
     rating: 식당 전체(평균) 평점
     cuisine_type: 식당 요리 종류 정보
     location: 식당 장소 정보
 */
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

/*
 3. DB2024_Menu: DB2024_Restaurant 테이블의 식당* 메뉴 테이블
    menu_id: 각각의 메뉴 투플을 구별하는 식별자
    menu_name: 메뉴의 이름 정보값. 50자까지 작성이 가능하다.
    res_id: DB2024_Restaurant 테이블의 PK를 FK로 받아옴
    price: 메뉴의 가격 정보값. INT 타입의 입력만 가능하다.
 */
-- *: Menu는 약한 개체 타입으로, Restaurant에 의존한다. (DB2024_Menu PK: menu_id, res_id)
-- 레스토랑 -> 메뉴 -> 리뷰가 제3정규형으로 변환된 것
CREATE TABLE DB2024_Menu(
    menu_id INT NOT NULL,
    menu_name VARCHAR(50) NOT NULL,
    res_id INT NOT NULL,
    price INT NOT NULL,

    PRIMARY KEY(menu_id, res_id),
    FOREIGN KEY(res_id) REFERENCES DB2024_Restaurant(res_id) ON DELETE CASCADE
);
CREATE TABLE DB2024_Review (
   review_id INT AUTO_INCREMENT,
   user_id VARCHAR(50) NOT NULL,
   rating INT NOT NULL CHECK(rating>-1 AND rating<6),
   review_content VARCHAR(500),

   PRIMARY KEY (review_id),
   FOREIGN KEY (user_id) REFERENCES DB2024_User(user_id) ON DELETE CASCADE
);

CREATE TABLE DB2024_Rating (
   review_id INT,
   res_id INT NOT NULL,
   PRIMARY KEY (review_id, res_id),
   FOREIGN KEY(review_id) REFERENCES DB2024_Review(review_id) ON DELETE CASCADE,
   FOREIGN KEY(res_id) REFERENCES DB2024_Restaurant(res_id) ON DELETE CASCADE
);

 --  DB2024_Review_Menu_Res_Mapping : addReview를 위한 매핑 테이블
CREATE TABLE DB2024_Review_Menu_Res_Mapping (
    mapping_id INT AUTO_INCREMENT PRIMARY KEY,
    review_id INT,
    menu_id INT,
    res_id INT,
    FOREIGN KEY (review_id) REFERENCES DB2024_Review(review_id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES DB2024_Menu(menu_id) ON DELETE CASCADE,
    FOREIGN KEY (res_id) REFERENCES DB2024_Restaurant(res_id) ON DELETE CASCADE
);


-- 릴레이션 확인 -----------------------------------------------------------------------
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
      ('등촌샤브칼국수 신촌점', 30, '0507-1398-7850', '서울 서대문구 신촌역로 43 1층', '월~일 11:00~22:00', '월~일 15:00~17:00', '5', '샤브샤브', '정문'),
      ('슬로우캘리 이대점', 31, '0507-1391-7188', '서울 서대문구 이화여대길 78 가동 1층', '월~일 11:00~21:00', '월~일 15:00~17:00', NULL, '샐러드', '정문');

INSERT INTO DB2024_User VALUES
	('s2eojeong', 's2eojeong', '조서정', 2276305, 's2eojeong@gmail.com', '후문'),
    ('astralfinance', 'astralfinance', '한사랑', 2271064, 'astralfinance@ewhain.net', '후문'),
    ('cannes7', 'cannes7', '고은서', 2122004, 'cannes7@ewhain.net', '정문'),
    ('meanwest', 'meanwest', '김민서', 2276046, 'meanwestk@gmail.com', '후문'),
    ('chacha091', 'chacha091', '차현주', 2276321, 'chacha09@ewhain.net', '정문');
    
INSERT INTO DB2024_Menu VALUES
	(1, '육회덮밥', 1, 11000),
	(2, '소고기 가지덮밥', 1, 15000),
	(1, '사케동', 2, 14000),
	(2, '간장새우계란밥', 2, 12000),
	(1, '잠봉뵈르', 3, 11000),
	(2, '허머스 라페 치아바타', 3, 9000),
	(3, '스모크드 베이컨 할리피뇨 바게트', 3, 11000),
	(1, '전복 관자 파스타', 4, 23000),
	(2, '홍새우 파스타', 4, 23000),
	(3, '흑돼지 핑크안심', 4, 17000),
	(4, 'E.C.C 샐러드', 4, 16000),
	(1, '(스페인에서 온 올리브) 감바스새우', 5, 9400),
	(2, '(노르웨이 불곰도 반한) 생연어', 5, 9900),
	(3, '리코타치즈 샌드', 5, 7500),
	(1, '등심돈까스', 6, 9500),
	(2, '안심돈까스', 6, 11000),
	(3, '치즈돈까스', 6, 11000),
	(1, '붓가게', 7, 7000),
	(2, '기츠네', 7, 7500),
	(3, '가케', 7, 6500),
	(1, '아콘스톨김밥', 8, 3000),
	(2, '순대떡볶음', 8, 3900),
	(1, '사케동', 9, 15000),
	(2, '아부리사케동', 9, 15000),
	(1, '삼겹 샤브세트', 10, 12000),
	(2, '버섯 샤브세트', 10, 12000),
	(1, '제육덮밥', 11, 8500),
	(2, '가라아게동', 11, 9000),
	(3, '가츠동', 11, 9000),
	(1, '치킨라이스 보통', 12, 10000),
	(2, '치킨라이스 특', 12, 14000),
	(1, '모로코 잘룩 플레이트', 13, 25000),
	(2, '터키 츨브르 플레이트', 13, 23000),
	(1, '우삼겹정식', 14, 16000),
	(2, '마구로정식', 14, 16000),
	(1, '후띠우 쌀국수', 15, 9000),
	(2, '분짜', 15, 10000),
	(1, '떡갈비 도시락', 16, 12000),
	(2, '소불고기 도시락', 16, 15000),
	(1, '매콤명란크림파스타', 17, 9500),
	(2, '알리오올리오', 17, 8500),
	(1, '베트남치킨반미', 18, 7300),
	(2, '베이컨 치즈 오믈렛', 18, 7800),
	(1, '연잎밥 정식', 19, 18000),
	(2, '곤드레밥 정식', 19, 16000),
	(1, '청년찌개', 20, 8000),
	(2, '궁극의베이컨볶음밥', 20, 8500),
	(1, '파마산치킨파스타', 21, 12000),
	(2, '토마토 해산물 파스타', 21, 12000),
	(1, '베이징 가지덮밥', 22, 13500),
	(2, '쉬림프 크림 파스타', 22, 16500),
	(1, '불돼지', 23, 10000),
	(2, '불오징어', 23, 12000),
	(1, '불고기정식', 24, 12000),
	(2, '둘깨미역국', 24, 9500),
	(1, '러우지아모', 25, 4000),
	(2, '비법량피', 25, 6000),
	(3, '쏸라펀', 25, 7000),
	(1, '꿔바로우', 26, 12900),
	(2, '마라탕', 26, 7000),
	(3, '마라샹궈', 26, 15000),
	(1, '훈제연어 샌드위치', 27, 8900),
	(2, '낫소', 27, 2800),
	(1, '짜장면', 28, 5500),
	(2, '짬뽕', 28, 6500),
	(1, '김치찌개', 29, 3000),
	(2, '라면사리', 29, 1000),
	(3, '고기추가', 29, 2000),
	(4, '어묵사리', 29, 1000),
	(1, '얼큰 버섯 칼국수', 30, 10000),
	(2, '맑은 버섯 칼국수', 30, 10000),
	(3, '샤브소고기', 30, 10000),
	(1, '스파이스 연어 포케', 31, 12500),
	(2, '클래식 참치 포케', 31, 11500),
	(3, '부채살 스테이크 보울', 31, 13500);

INSERT INTO DB2024_Review VALUES
    (1, 's2eojeong', 5, '육회덮밥 너무 고소하고 맛있어용!!'),
    (2, 'cannes7', 4, '소고기 가지덮밥 맛있긴한데 약간 비싸요ㅠㅠ'),
    (3, 'meanwest', 5, '여기 육회덮밥 진짜 너무 맛있어요.. 모미지 절대 지켜.'),
    (4, 'chacha091', 5, '사케동 정말 신선하고 맛있어요!'),
    (5, 'astralfinance', 5, '간장새우계란밥이 진짜 최고에요.. 눈물 훌리면서 먹었어요'),
    (6, 'cannes7', 4, '가격대가 좀 있긴해도 양이 많아서 먹고나면 배불러요ㅎㅎ'),
    (7, 's2eojeong', 0, '스모크드 베이컨 할리피뇨 바게트 매콤하긴한데 맛있게 잘 먹었어요 추천!!'),
    (8, 'meanwest', 4, '잠봉뵈르 독특하고 맛있어요!'),
    (9, 'astralfinance', 3, '그냥 잠봉뵈르에요. 무난합니다.'),
    (10, 'meanwest', 5, '홍새우 파스타 바다 향 솔솔나고 맛있어요!'),
    (11, 'chacha091', 4, 'E.C.C 샐러드 완전 신선하고 무난하게 맛있어요!'),
    (12, 's2eojeong', 3, '홍새우 처음 먹어보는데 나쁘지 않아요.'),
    (13, 'meanwest', 5, '리코타치즈 들어간 샌드위치 꼭 드세요. 신세계입니다'),
    (14, 'cannes7', 5, '생연어 신선하고 촉촉하고 부드러워요ㅠㅠ 추천!!'),
    (15, 'chacha091', 4, '정말 불곰이 반할만하네요.'),
    (16, 'astralfinance', 4, '등심돈까스 무난하게 맛있어요~'),
    (17, 'cannes7', 5, '등심보다 부드러워서 먹기 좋아요.'),
    (18, 'meanwest', 3, '튀김 옷이 약간 눅눅하네요ㅠㅠ'),
    (19, 'astralfinance', 5, '붓가케 진짜 맛있어요… 꼭 드셔보세요 다들!'),
    (20, 'cannes7', 4, '기츠네 처음 들어보는데 맛있고 사장님이 친절하세요.'),
    (21, 'meanwest', 5, '가케를 시켜보았는데 역시 이 집은 다 맛있네요!'),
    (22, 'chacha091', 5, '아콘스톨김밥만큼 가성비도 좋고 맛있는 곳은 없어요.. 학부생들에게 한줄기의 빛.'),
    (23, 'cannes7', 5, '가끔 분식 땡길때 순대떡볶음 먹는데 여전히 맛있네요.'),
    (24, 'meanwest', 4, '바빠서 시간 없을때 간단하게 먹기 좋네요.'),
    (25, 'chacha091', 5, '사케동 위에 연어 너무 신선하고 부드러워요!'),
    (26, 's2eojeong', 4, '학교 주변에 사케동 파는 곳 많은데 여기도 나쁘지 않은 것 같아요.'),
    (27, 'chacha091', 5, '아부리사케동도 맛있네요. 불향이 베어져서 좋아요.'),
    (28, 'cannes7', 3, '연어가 너무 구워져서 안 촉촉했어요ㅠㅠ'),
    (29, 'chacha091', 5, '비오는 날 마다 삼겹샤브 먹으러 오는데 참 맛있네요.'),
    (30, 'astralfinance', 5, '버섯 샤브세트 삼겹샤브만큼 맛있어요! '),
    (31, 'meanwest', 4, '제육덮밥 무난하게 맛있어요.'),
    (32, 'chacha091', 3, '튀김이 안 바삭거려서 아쉬워요..'),
    (33, 's2eojeong', 4, '양이 약간 적긴해도 나쁘지 않네요.'),
    (34, 'meanwest', 5, '평소에도 자주 먹었는데 오늘은 특으로 시켰어요! '),
    (35, 'chacha091', 4, '생소한 이름인데 이국적인 맛이 나서 특이해요.'),
    (36, 'astralfinance', 5, '타코 비슷한 맛도 나는 것 같아요. 나쁘지않아요!'),
    (37, 'chacha091', 3, '고기냄새가 약간 나는 것 같아요..'),
    (38, 'cannes7', 5, '위에 노른자 톡 터뜨려서 먹으면 맛있어요!'),
    (39, 's2eojeong', 5, '둘이 먹다 하나 죽어도 모를 맛이에요.. 강추'),
    (40, 'cannes7', 4, '싱싱하니 맛있네요.'),
    (41, 's2eojeong', 5, '잠시동안 베트남에 있다왔네요.'),
    (42, 'cannes7', 4, '국물이 깔끔하고 좋아요.'),
    (43, 'meanwest', 3, '고기가 좀 퍽퍽해요.. 가격에 비해 고기양이 적은것도 같네요.'),
    (44, 'chacha091', 4, '한식 땡길 때 먹으면 좋아요.'),
    (45, 'astralfinance', 3, '아무래도 소불고기가 가격대가 좀 있어요.. 맛있긴한데 아쉽 ㅠㅠ'),
    (46, 'meanwest', 4, '크림이면 보통 꾸덕하기 마련인데 이건 매콤해서 느끼하지도 않고 좋아요. 강추!!'),
    (47, 'cannes7', 5, '너무 맛있어요!!!! 단짠단짠의 대명사.'),
    (48, 's2eojeong', 5, '파스타 중에서 오일 파스타를 제일 좋아하는데 맛있네요 ㅎㅎ'),
    (49, 'cannes7', 4, '아침에 브런치로 먹으니까 맛있고 적당히 배부르네요.'),
    (50, 'meanwest', 4, '건강한 맛이라 간은 약하지만 그래도 먹을만해요. '),
    (51, 'cannes7', 4, 'msg 하나 없이 자연친화적인 맛이에요. 속세를 벗어나고 싶으신 분들께 추천드려요.'),
    (52, 's2eojeong', 3, '나물이 약간 질겨서 씹는데 불편했어요ㅠㅠ'),
    (53, 'meanwest', 4, '엄마가 해주는 찌개 맛 나요. 얼른 종강했으면'),
    (54, 'cannes7', 4, '집에서 해먹는 볶음밥 맛이에요. 나쁘지 않아요.'),
    (55, 'meanwest', 5, '나쁘진 않았는데 다음엔 안 시킬 것 같아요..!'),
    (56, 'chacha091', 5, '여기 소스가 진짜 특이하고 맛있어요!!'),
    (57, 'astralfinance', 4, '꾸덕하니 맛있네요!!'),
    (58, 'meanwest', 4, '가지 껍질이 약간 질기긴 했지만 소스가 맛있어서 괜찮았어요!!'),
    (59, 'chacha091', 4, '소스가 특이한데 묘하게 중독성 있어요..'),
    (60, 'cannes7', 4, '평소에 미역국 되게 좋아하는데 들깨 미역국이래서 시켜보았어요. 나쁘진 않지만 역시 그냥 미역국이 더 나은 것 같아요.'),
    (61, 'meanwest', 5, '국물이 꾸-덕하고 고소해요. 간만에 맛있게 식사했어요.'),
    (62, 'astralfinance', 3, '양념이 너무 달아요ㅠㅠ');

INSERT INTO DB2024_Rating VALUES
                              (1, 1),
                              (2, 1),
                              (3, 1),
                              (4, 2),
                              (5, 2),
                              (6, 2),
                              (7, 3),
                              (8, 3),
                              (9, 3),
                              (10, 4),
                              (11, 4),
                              (12, 4),
                              (13, 5),
                              (14, 5),
                              (15, 5),
                              (16, 6),
                              (17, 6),
                              (18, 6),
                              (19, 7),
                              (20, 7),
                              (21, 7),
                              (22, 8),
                              (23, 8),
                              (24, 8),
                              (25, 9),
                              (26, 9),
                              (27, 9),
                              (28, 9),
                              (29, 10),
                              (30, 10),
                              (31, 11),
                              (32, 11),
                              (33, 12),
                              (34, 12),
                              (35, 13),
                              (36, 13),
                              (37, 14),
                              (38, 14),
                              (39, 14),
                              (40, 14),
                              (41, 15),
                              (42, 15),
                              (43, 15),
                              (44, 16),
                              (45, 16),
                              (46, 17),
                              (47, 17),
                              (48, 17),
                              (49, 18),
                              (50, 18),
                              (51, 19),
                              (52, 19),
                              (53, 20),
                              (54, 20),
                              (55, 21),
                              (56, 21),
                              (57, 22),
                              (58, 22),
                              (59, 22),
                              (60, 23),
                              (61, 23),
                              (62, 24);

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

-- 리뷰를 유저이름, 평점, 리뷰내용의 형태로 보기 위한 뷰
CREATE VIEW DB2024_UserReview AS
SELECT review_id, DB2024_User.name, rating, review_content
FROM DB2024_User, DB2024_Review
WHERE DB2024_User.user_id = DB2024_Review.user_id;
-- SELECT * FROM DB2024_UserReview;

-- 리뷰를 식당이름, 평점, 리뷰내용의 형태로 보기 위한 뷰
CREATE VIEW DB2024_ResReview AS
SELECT r.review_id, user_id, DB2024_Restaurant.res_name, r.rating, review_content
FROM DB2024_Restaurant, DB2024_Review r, DB2024_Rating
WHERE DB2024_Restaurant.res_id = DB2024_Rating.res_id
  AND r.review_id = DB2024_Rating.review_id;
-- SELECT * FROM DB2024_ResReview;

CREATE VIEW DB2024_MenuView AS
SELECT r.res_name, m.menu_name, m.price
FROM DB2024_Restaurant r JOIN DB2024_Menu m ON r.res_id = m.res_id;
-- SELECT * FROM DB2024_MenuView;

-- 인덱스 생성 -----------------------------------------------------------------------
-- DB2024_Rating.res_id: 특정 가게의 평점 평을 구할 때 DB2024_Rating 테이블의 res_id가 자주 사용됨
CREATE INDEX DB2024_idx_AvgRating
    ON DB2024_Rating (res_id);

-- DB2024_Review.user_id: 특정 유저의 리뷰를 몰아볼 때 DB2024_Review 테이블의 user_id가 자주 사용됨
CREATE INDEX DB2024_idx_Review
    ON DB2024_Review (user_id);

-- DB2024_Menu.res_id: Restaurant별로 menu 검색
CREATE INDEX DB2024_idx_Menu
    ON DB2024_Menu(res_id);
    
-- DB2024_Restaurant.cuisine_type: cuisine_type별로 Restaurant를 검색
CREATE INDEX DB2024_idx_Restaurant
    ON DB2024_Restaurant(cuisine_type);

-- 테이블 삭제 (맨 윗줄 코드로 대체) -----------------------------------------------------------------------
-- DROP DATABASE DB2024TEAM07;