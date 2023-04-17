-- 회원 테이블
CREATE TABLE bt_member(
	id VARCHAR2(100) PRIMARY KEY,
	password VARCHAR2(100) NOT NULL,
	name VARCHAR2(100) NOT NULL,
	nick VARCHAR2(100) NOT NULL UNIQUE,
	tel VARCHAR2(100) NOT NULL UNIQUE,
	question VARCHAR2(100) NOT NULL,
	answer VARCHAR2(100) NOT NULL,
	member_type VARCHAR2(100) DEFAULT '새싹회원' NOT NULL,
	enabled NUMBER DEFAULT 1 NOT NULL
)
SELECT * FROM bt_member;
DROP TABLE bt_member;

-- 자유게시판
CREATE TABLE free_board(
	free_no NUMBER PRIMARY KEY,
	title VARCHAR2(100) NOT NULL,
	content CLOB NOT NULL,
	time_posted DATE DEFAULT SYSDATE,
	hits NUMBER DEFAULT 0,
	image CLOB,
	file_path VARCHAR2(100),
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT free_board_fk FOREIGN KEY(id) REFERENCES bt_member(id)
)
ALTER TABLE free_board modify image CLOB
CREATE SEQUENCE free_board_seq NOCACHE;
DROP SEQUENCE free_board_seq;
SELECT * FROM free_board;
DROP TABLE free_board;

-- 자유게시판 댓글
CREATE TABLE free_comment(
	free_comment_no NUMBER PRIMARY KEY,
	content CLOB NOT NULL,
	time_posted DATE DEFAULT SYSDATE,
	id VARCHAR2(100) NOT NULL,
	free_no NUMBER NOT NULL,
	CONSTRAINT free_comment_id_fk FOREIGN KEY(id) REFERENCES bt_member(id),
	CONSTRAINT free_comment_no_fk FOREIGN KEY(free_no) REFERENCES free_board(free_no) ON DELETE CASCADE
)
CREATE SEQUENCE free_comment_seq NOCACHE;
DROP SEQUENCE free_comment_seq;
SELECT * FROM free_comment;
DROP TABLE free_comment;

-- 브릭 리뷰
CREATE TABLE brick_review(
	review_no NUMBER PRIMARY KEY,
	title VARCHAR2(100) NOT NULL,
	content CLOB NOT NULL,
	time_posted DATE DEFAULT SYSDATE,
	hits NUMBER DEFAULT 0,
	image VARCHAR2(1000),
	file_path VARCHAR2(100),
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT brick_review_fk FOREIGN KEY(id) REFERENCES bt_member(id)
)
CREATE SEQUENCE brick_review_seq;
DROP SEQUENCE brick_review_seq;
SELECT * FROM brick_review;
DROP TABLE brick_review;

-- 브릭 리뷰 댓글
CREATE TABLE brick_review_comment(
	review_comment_no NUMBER PRIMARY KEY,
	content CLOB NOT NULL,
	time_posted DATE DEFAULT SYSDATE,
	id VARCHAR2(100) NOT NULL,
	review_no NUMBER NOT NULL,
	CONSTRAINT brick_review_comment_id_fk FOREIGN KEY(id) REFERENCES bt_member(id),
	CONSTRAINT brick_review_comment_no_fk FOREIGN KEY(review_no) REFERENCES brick_review(review_no) ON DELETE CASCADE
)
CREATE SEQUENCE brick_review_comment_seq;
DROP SEQUENCE brick_review_comment_seq;
SELECT * FROM brick_review_comment;
DROP TABLE brick_review_comment;

-- 브릭 리뷰 좋아요
CREATE TABLE review_like(
	id VARCHAR2(100) NOT NULL,
	review_no NUMBER NOT NULL,
	CONSTRAINT review_like_id_fk FOREIGN KEY(id) REFERENCES bt_member(id),
	CONSTRAINT review_like_no_fk FOREIGN KEY(review_no) REFERENCES brick_review(review_no) ON DELETE CASCADE,
	CONSTRAINT review_like_pk PRIMARY KEY(id,review_no)
)
SELECT * FROM review_like;
DROP TABLE review_like;

-- 브릭 정보
CREATE TABLE brick_info(
	info_no NUMBER PRIMARY KEY,
	title VARCHAR2(100) NOT NULL,
	content CLOB NOT NULL,
	time_posted DATE DEFAULT SYSDATE,
	hits NUMBER DEFAULT 0,
	image VARCHAR2(1000),
	file_path VARCHAR2(100),
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT brick_info_fk FOREIGN KEY(id) REFERENCES bt_member(id)
)
CREATE SEQUENCE brick_info_seq;
DROP SEQUENCE brick_info_seq;
SELECT * FROM brick_info;
DROP TABLE brick_info;

-- 브릭 정보 댓글
CREATE TABLE brick_info_comment(
	info_comment_no NUMBER PRIMARY KEY,
	content CLOB NOT NULL,
	time_posted DATE DEFAULT SYSDATE,
	id VARCHAR2(100) NOT NULL,
	info_no NUMBER NOT NULL,
	CONSTRAINT brick_info_comment_id_fk FOREIGN KEY(id) REFERENCES bt_member(id),
	CONSTRAINT brick_info_comment_no_fk FOREIGN KEY(info_no) REFERENCES brick_info(info_no) ON DELETE CASCADE
)
CREATE SEQUENCE brick_info_comment_seq;
DROP SEQUENCE brick_info_comment_seq;
SELECT * FROM brick_info_comment;
DROP TABLE brick_info_comment;

-- 공지사항
CREATE TABLE notice_board(
	notice_no NUMBER PRIMARY KEY,
	title VARCHAR2(100) NOT NULL,
	content CLOB NOT NULL,
	time_posted DATE DEFAULT SYSDATE,
	hits NUMBER DEFAULT 0,
	image VARCHAR2(1000),
	file_path VARCHAR2(100),
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT notice_fk FOREIGN KEY(id) REFERENCES bt_member(id)
)
CREATE SEQUENCE notice_board_seq;
DROP SEQUENCE notice_board_seq;
SELECT * FROM notice_board;
DROP TABLE notice_board; 

-- 스탭
CREATE TABLE staff(
	id VARCHAR2(100) PRIMARY KEY,
	info VARCHAR2(100) NOT NULL,
	status NUMBER DEFAULT 0,
	CONSTRAINT staff_fk FOREIGN KEY(id) REFERENCES bt_member(id)
)
SELECT * FROM staff;
DROP TABLE staff; 

-- 로그인
SELECT id,password,name,nick,tel,question,answer,member_type,enabled FROM bt_member WHERE id='test' AND password='a';

-- 아이디 찾기 
SELECT id FROM bt_member WHERE name='테스트' AND tel='01011111111';
SELECT enabled FROM bt_member WHERE name='테스트' AND tel='01011111111';

-- 비밀번호 찾기
SELECT password FROM bt_member WHERE id='test' AND question='첫 구매 레고는?' AND answer='10263' AND tel='01011111111';
SELECT enabled FROM bt_member WHERE id='test' AND question='첫 구매 레고는?' AND answer='10263' AND tel='01011111111';

-- 회원가입
INSERT INTO bt_member(id,password,name,nick,tel,question,answer) 
VALUES('test','a','테스트','테스트걸','01011111111','첫 구매 레고는?','10263');
DELETE FROM bt_member WHERE id='test'
COMMIT

-- 아이디 중복체크
SELECT id,password,name,nick,tel,question,answer,member_type,enabled FROM bt_member WHERE id='test'

-- 닉네임 중복체크
SELECT COUNT(*) FROM bt_member WHERE nick='테스트걸';

-- 연락처 중복체크
SELECT COUNT(*) FROM bt_member WHERE tel='01011111111';

-- 회원정보수정
UPDATE bt_member SET password='123123', name='박봄봄', tel='01098765432', question='첫 구매 레고는?', answer='10263' WHERE id='spring'

-- 회원탈퇴
UPDATE bt_member SET enabled='0' WHERE id='jinsoo' AND password='aaaaaa';

-------------------------------------------------------------------------------------
INSERT INTO free_board(free_no,title,content,time_posted,hits,image,file_path,id)
VALUES(free_board_seq.nextval,'제목7','내용',sysdate,0,'이미지','경로','spring');

DELETE FROM free_board

-- 자유게시판 리스트
SELECT row_number() over(ORDER BY free_no DESC) AS rnum,
free_no,title,to_char(time_posted,'YYYY.MM.DD') AS time_posted,hits,id
FROM free_board


SELECT rnum,free_no,title,btm.nick,time_posted,hits
FROM(
SELECT row_number() over(ORDER BY free_no DESC) AS rnum,
free_no,title,to_char(time_posted,'YYYY.MM.DD') AS time_posted,hits,id
FROM free_board
) fb
INNER JOIN bt_member btm ON fb.id=btm.id
WHERE rnum BETWEEN 1 AND 4

-- 게시글 총 수
SELECT COUNT(*) FROM free_board;

-- 자유게시판 상세 글 보기
SELECT title,content,btm.nick,btm.member_type,to_char(time_posted,'YYYY.MM.DD HH24:Mi') AS time_posted,hits,image,btm.id
FROM free_board fb
INNER JOIN bt_member btm ON fb.id=btm.id
WHERE free_no=1

-- 자유게시판 글쓰기
INSERT INTO free_board(free_no,title,content,time_posted,hits,image,file_path,id)
VALUES(free_board_seq.nextval,'제목7','내용',sysdate,0,'이미지','경로','spring');

-- 자유게시판 글삭제
DELETE FROM free_board WHERE free_no=14;

-- 자유게시판 글수정
UPDATE free_board SET title='test',content='test' WHERE free_no=8;

-- 자유게시판 검색
SELECT rnum,free_no,title,btm.nick,time_posted,hits
FROM(
SELECT row_number() over(ORDER BY free_no DESC) AS rnum,
free_no,title,to_char(time_posted,'YYYY.MM.DD') AS time_posted,hits,id
FROM free_board WHERE title LIKE '%제%'
) fb
INNER JOIN bt_member btm ON fb.id=btm.id
WHERE rnum BETWEEN 1 AND 4 

-- 자유게시판 검색 키워드 갯수
SELECT COUNT(*) FROM free_board WHERE title LIKE '%제%';

-- 자유게시판 조회수
UPDATE free_board SET hits=hits+1 WHERE free_no=24;

-------------------------------------------------------------------------------------
-- 자유게시판 댓글 목록 조회
SELECT row_number() over(ORDER BY free_comment_no DESC) AS rnum,
free_comment_no,content,to_char(time_posted,'YYYY.MM.DD HH24:Mi') AS time_posted,id,free_no
FROM free_comment

INSERT INTO free_comment(free_comment_no,content,time_posted,id,free_no)
VALUES(free_comment_seq.nextval,'댓글일걸용
헿
헿',sysdate,'java','24');

SELECT fc.rnum,fc.free_comment_no,fc.content,fc.time_posted,btm.nick,fb.free_no
FROM(
SELECT row_number() over(ORDER BY free_comment_no ASC) AS rnum,
free_comment_no,content,to_char(time_posted,'YYYY.MM.DD HH24:Mi') AS time_posted,id,free_no
FROM free_comment
)fc
INNER JOIN bt_member btm ON btm.id=fc.id
INNER JOIN free_board fb ON fb.free_no=fc.free_no
WHERE fb.free_no = 23;










