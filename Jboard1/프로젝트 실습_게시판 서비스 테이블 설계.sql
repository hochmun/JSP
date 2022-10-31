#날짜 : 2022/10/19
#이름 : 심규영
#내용 : 프로젝트 실습_게시판 서비스 테이블 설계

#데이터베이스 생성
CREATE DATABASE `java2_board`;java2_board
CREATE USER 'java2_board'@'%' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON `java2_board`.* TO 'java2_board'@'%';
FLUSH PRIVILEGES;
#회원 테이블 생성
CREATE TABLE `board_user` (
	`uid`		VARCHAR(20) PRIMARY key,
	`pass`	VARCHAR(255) NOT NULL,
	`name`	VARCHAR(20) NOT NULL,
	`nick`	VARCHAR(20) UNIQUE NOT NULL,
	`email`	VARCHAR(20) UNIQUE NOT NULL,
	`hp`		CHAR(13) UNIQUE NOT NULL,
	`grade`	TINYINT DEFAULT 2,
	`zip`		CHAR(5),
	`addr1`	VARCHAR(255),
	`addr2`	VARCHAR(255),
	`regip`	VARCHAR(100) NOT NULL,
	`rdate`	DATETIME NOT NULL
);
#약관 테이블 생성
CREATE TABLE `board_terms` (
	`terms`		text,
	`privacy`	text
);
#게시물 테이블 생성
CREATE TABLE `board_article` (
	`no`			INT AUTO_INCREMENT PRIMARY KEY,
	`parent`		INT DEFAULT 0,
	`comment`	INT DEFAULT 0,
	`cate`		VARCHAR(20) DEFAULT 'free',
	`title`		VARCHAR(255),
	`content`	TEXT NOT NULL,
	`file`		TINYINT DEFAULT 0,
	`hit`			INT DEFAULT 0,
	`uid`			VARCHAR(20) NOT NULL,
	`regip`		VARCHAR(100) NOT NULL,
	`rdate`		DATETIME NOT NULL
);
#파일 테이블 생성
CREATE TABLE `board_file` (
	`fno`			INT AUTO_INCREMENT PRIMARY KEY,
	`parent`		INT NOT NULL,
	`newName`	VARCHAR(255) NOT NULL,
	`oriName`	VARCHAR(255) NOT NULL,
	`download` 	INT DEFAULT 0
);

#댓글 테이블 생성
CREATE TABLE `board_comment` (
	`cno`			INT AUTO_INCREMENT PRIMARY KEY,
	`parent`		INT NOT NULL,
	`uid`			VARCHAR(20) NOT NULL,
	`nick`		VARCHAR(20) NOT NULL,
	`content` 	TEXT NOT NULL,
	`regip`	VARCHAR(100) NOT NULL,
	`rdate`		DATETIME NOT NULL
)

SELECT MD5('1234');
SELECT SHA1('1234');java2db
SELECT SHA2('1234', 256);

INSERT INTO `board_article` (`title`, `content`, `uid`, `regip`, `rdate`)
SELECT `title`, `content`, `uid`, `regip`, `rdate` FROM `board_article`;

# 조회수 증가 구문
UPDATE `board_article` set hit = hit+1 WHERE `no`=?

SELECT COUNT(`no`) FROM `board_article`;

SELECT 
	a.`no`,
	a.title,
	a.content,
	f.oriName,
	f.download,
	
FROM 
	`board_article` AS a
	LEFT JOIN `board_file` AS f ON a.`no` = f.parent 
	LEFT JOIN `board_comment` AS c ON a.`no` = c.parent
	WHERE a.`no` = 2;
	
SELECT MAX(`no`) FROM `board_article`

UPDATE `board_article` SET `comment` = `comment`+1 WHERE `no`=?;