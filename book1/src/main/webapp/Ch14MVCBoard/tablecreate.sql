CREATE TABLE mvcboard (
	`idx` int PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	`title` VARCHAR(200) NOT NULL,
	`content` VARCHAR(2000) NOT NULL,
	`postdate` DATE DEFAULT (CURRENT_DATE) NOT NULL,
	`oflie` VARCHAR(200),
	`sfile` VARCHAR(30),
	`downcount` INT DEFAULT 0 NOT NULL,
	`pass` VARCHAR(50) NOT NULL,
	`visitcount` INT DEFAULT 0 NOT NULL
);

INSERT INTO `mvcboard` (`name`, `title`, `content`, `pass`)
	VALUES ('김유신', '자료실 제목1 입니다.', '내용', '1234');
INSERT INTO `mvcboard` (`name`, `title`, `content`, `pass`)
	VALUES ('장보고', '자료실 제목2 입니다.', '내용', '1234');
INSERT INTO `mvcboard` (`name`, `title`, `content`, `pass`)
	VALUES ('이순신', '자료실 제목3 입니다.', '내용', '1234');
INSERT INTO `mvcboard` (`name`, `title`, `content`, `pass`)
	VALUES ('강감찬', '자료실 제목4 입니다.', '내용', '1234');
INSERT INTO `mvcboard` (`name`, `title`, `content`, `pass`)
	VALUES ('대조영', '자료실 제목5 입니다.', '내용', '1234');
	
SELECT * FROM (
	SELECT tb.*, @ROWNUM:=@ROWNUM+1 AS `rNum` FROM (
		SELECT * FROM `mvcboard`
		ORDER BY `idx` DESC) AS `Tb`, 
		(SELECT @ROWNUM:=0) R
		) A
		 WHERE `rNum` BETWEEN 1 AND 10;