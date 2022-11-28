package kr.co.Jboard2.db;

public class Sql {
	// user
	public static final String INSERT_USER =
			"insert into `board_user` set "
			+ "`uid`=?, "
			+ "`pass`=SHA2(?, 256), "
			+ "`name`=?, "
			+ "`nick`=?, "
			+ "`email`=?, "
			+ "`hp`=?, "
			+ "`zip`=?, "
			+ "`addr1`=?, "
			+ "`addr2`=?, "
			+ "`regip`=?, "
			+ "`rdate`=NOW()";
	
	public static final String SELECT_USER = 
			"select * from `board_user` "
			+ "where `uid`=? and `pass`=SHA2(?,256)";
	public static final String SELECT_USER_EMAIL = 
			"SELECT "
			+ " `uid`,"
			+ " `pass`,"
			+ " `name`,"
			+ " `nick`,"
			+ " `email`, "
			+ " `hp`, "
			+ " `rdate` "
			+ " FROM `board_user` WHERE `email`=?";
	public static final String SELECT_USER_BY_SESSID = "SELECT * FROM `board_user` WHERE `sessId`=? AND `sesslimitdate` > NOW()";
	public static final String SELECT_COUNT_USER_UID = 
			"select count(`uid`) from `board_user` where `uid`=?";
	public static final String SELECT_COUNT_USER_NICK = 
			"select count(`nick`) from `board_user` where `nick`=?";
	public static final String SELECT_COUNT_USER_NAME_EMAIL = 
			"SELECT COUNT(`name`) FROM `board_user` WHERE `name`=? AND `email`=?";
	public static final String SELECT_COUNT_USER_UID_EMAIL =
			"SELECT COUNT(`uid`) FROM `board_user` WHERE `uid`=? AND `email`=?";
	public static final String SELECT_TERMS =
			"select * from `board_terms`";
	
	public static final String UPDATE_USER_PASS = 
			"UPDATE `board_user` SET `pass` = SHA2(?, 256) WHERE `uid`=?";
	public static final String UPDATE_USER_FOR_SESSION =
			"UPDATE `board_user` SET `sessId` = ? , `sessLimitDate` = DATE_ADD(NOW(), INTERVAL 3 DAY) WHERE `uid` = ?";
	public static final String UPDATE_USER_FOR_SESS_LIMIT_DATE = "UPDATE `board_user` SET `sessLimitDate` = DATE_ADD(NOW(), INTERVAL 3 DAY) WHERE `sessId` = ?";
	public static final String UPDATE_USER_FOR_SESSION_OUT = "UPDATE `board_user` SET `sessId` = null , `sessLimitDate` = null WHERE `uid` = ?";
	
	// board
	public static final String INSERT_ARTICLE = 
			"insert into `board_article` set "
			+ "`title`=?, "
			+ "`content`=?, "
			+ "`file`=?, "
			+ "`uid`=?, "
			+ "`regip`=?, "
			+ "`rdate`=NOW()";
	public static final String SELECT_FILE =
			"SELECT * FROM `board_file` "
			+ " WHERE `parent`=?";
	public static final String INSERT_FILE = 
			"insert into `board_file` set"
			+ "`parent`=?, "
			+ "`newName`=?, "
			+ "`oriName`=?";
	public static final String UPDATE_FILE_DOWNLOAD =
			"UPDATE `board_file` set download = download+1"
			+ " WHERE `fno`=?";
	public static final String SELECT_MAX_NO =
			"SELECT MAX(`no`) FROM `board_article`";
	public static final String SELECT_ARTICLE_LIST =
			"SELECT "
			+ "	a.*, "
			+ "	b.`nick` "
			+ "	FROM `board_article` AS a"
			+ "	JOIN `board_user` AS b ON a.`uid` = b.`uid`"
			+ "	WHERE "
			+ "	a.`cate` = 'free' "
			+ " AND (a.`title` LIKE ?"
			+ "	OR b.`nick` LIKE ?)"
			+ "	ORDER BY a.`no` DESC"
			+ "	LIMIT ?, 10";
	public static final String SELECT_ARTICLE =
			"SELECT * "
			+ " FROM `board_article` AS a "
			+ " LEFT JOIN `board_file` AS b ON a.no = b.parent"
			+ " WHERE `no`=?";
	public static final String SELECT_COUNT_TOTAL_ARTICLE =
			"SELECT COUNT(a.`no`) "
			+ "	FROM `board_article` AS a"
			+ "	JOIN `board_user` AS b ON a.`uid` = b.`uid`"
			+ "	WHERE "
			+ "	a.`cate` = 'free' "
			+ " AND (a.`title` LIKE ?"
			+ "	OR b.`nick` LIKE ?)";
	public static final String UPDATE_ARTICLE_HIT =
			"UPDATE `board_article` set hit = hit+1"
			+ " WHERE `no`=?";
	public static final String UPDATE_ARTICLE =
			"UPDATE `board_article` SET "
			+ " `title`=?, "
			+ " `content`=?, "
			+ " `rdate`=NOW() "
			+ " WHERE `no`=?";
	public static final String DELETE_ARTICLE =
			"DELETE FROM a, f "
			+ " USING `board_article` AS a "
			+ " LEFT JOIN `board_file` AS f "
			+ " ON a.`no` = f.`parent`"
			+ " where a.`no`=? or a.`parent`=? or f.`parent`=?";
	public static final String INSERT_COMMENT = 
			"INSERT INTO `board_article` SET "
			+ "`parent`=?, "
			+ "`cate`='comment', "
			+ "`content`=?, "
			+ "`uid`=?, "
			+ "`regip`=?, "
			+ "`rdate`=NOW()";
	public static final String UPDATE_COMMENT_NUMBER = 
			"UPDATE `board_article` SET `comment` = `comment`+1 "
			+ "WHERE `no`=?";
	public static final String DELETE_COMMENT_NUMBER =
			"UPDATE `board_article` SET `comment` = `comment`-1 "
			+ " WHERE `no`=?";
	public static final String COMMENT_LIST = 
			"SELECT "
			+ "a.`no`, a.`parent`, a.`cate`, a.`content`, a.`uid`, a.`regip`, "
			+ "a.`rdate`, u.`nick` "
			+ " FROM `board_article` as a "
			+ " JOIN `board_user` as u ON a.uid = u.uid "
			+ " WHERE `parent`=? "
			+ " ORDER BY `no` ASC";
	public static final String LAST_COMMENT_TIME =
			"SELECT `rdate`, `no` FROM `board_article` ORDER BY `rdate` DESC LIMIT 1;";
	public static final String UPDATE_COMMENT = 
			"UPDATE `board_article` SET"
			+ " `content`=?, "
			+ " `rdate`=NOW() "
			+ " WHERE `no`=?";
	public static final String REMOVE_COMMENT = 
			"DELETE FROM `board_article` where `no`=?";
}
