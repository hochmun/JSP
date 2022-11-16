package kr.co.Farmstory_1.config;

public class Sql {
	// article
	public static final String INSERT_ARTICLE =
		"INSERT INTO `board_article` SET "
		+ "`cate`=?, "
		+ "`title`=?, "
		+ "`content`=?, "
		+ "`file`=?, "
		+ "`uid`=?, "
		+ "`regip`=?, "
		+ "`rdate`=NOW()";
	
	public static final String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `board_article`";
	public static final String SELECT_ARTICLE = "SELECT * FROM `board_article` AS a LEFT JOIN `board_file` AS b ON a.no = b.parent WHERE `no`=?";
	public static final String SELECT_ARTICLES =
			"SELECT "
			+ "a.*,"
			+ "b.`nick`"
			+ " FROM `board_article` as a "
			+ "JOIN `board_user` as b ON a.`uid` = b.`uid` "
			+ "WHERE `parent`=0 AND `cate`=? "
			+ "ORDER BY a.`no` DESC "
			+ "LIMIT ?, 10";
	public static final String SELECT_COUNT_ARTICLE = "SELECT COUNT(`no`) FROM `board_article` WHERE `parent`=0 AND `cate`=?";
	
	public static final String UPDATE_ARTICLE = "UPDATE `board_article` SET `title`=?, `content`=?, `rdate`=NOW() WHERE `no`=?";
	public static final String UPDATE_HIT_ARTICLE = "UPDATE `board_article` SET `hit` = `hit`+1 WHERE `no`=?";
	
	public static final String DELETE_ARTICLE = 
			"DELETE FROM a, f "
			+ "USING `board_article` AS a "
			+ "LEFT JOIN `board_file` AS f "
			+ "ON a.`no` = f.`parent` "
			+ "WHERE a.`no`=? OR a.`parent`=? OR f.`parent`=?";
	// file
	public static final String INSERT_FILE = "INSERT INTO `board_file` SET `parent`=?, `newName`=?, `oriName`=?";
	public static final String SELECT_FILE_NEWNAME = "SELECT `newName` FROM `board_file` WHERE `parent`=?";
	
	// terms
	public static final String SELECT_TERMS = "SELECT `terms`, `privacy` FROM `board_terms`";
	
	// user
	public static final String INSERT_USER = 
			"INSERT INTO `board_user` SET"
			+ " `uid`=?,"
			+ " `pass`=SHA2(?, 256),"
			+ " `name`=?,"
			+ " `nick`=?,"
			+ " `email`=?,"
			+ " `hp`=?,"
			+ " `zip`=?,"
			+ " `addr1`=?,"
			+ " `addr2`=?,"
			+ " `regip`=?,"
			+ " `rdate`=NOW()";
	public static final String SELECT_USER = "SELECT * FROM `board_user` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	public static final String COUNT_UID = "SELECT COUNT(`uid`) FROM `board_user` WHERE `uid`=?";
	public static final String COUNT_NICK = "SELECT COUNT(`nick`) FROM `board_user` WHERE `nick`=?";
}
