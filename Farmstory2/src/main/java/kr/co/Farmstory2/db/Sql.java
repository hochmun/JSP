package kr.co.Farmstory2.db;

public class Sql {
	// user
	public static final String INSERT_USER = "INSERT INTO `board_user` SET "
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
			"SELECT * FROM `board_user` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	public static final String SELECT_USER_BY_SESSID = 
			"SELECT * FROM `board_user` WHERE `sessId`=? AND `sesslimitdate` > NOW()";
	public static final String SELECT_USER_EMAIL = 
			"SELECT "
			+ "`uid`, "
			+ "`pass`, "
			+ "`name`, "
			+ "`nick`, "
			+ "`email`, "
			+ "`hp`, "
			+ "`rdate` "
			+ "FROM `board_user` WHERE `email`=?";
	public static final String SELECT_COUNT_UID = 
			"SELECT COUNT(`uid`) FROM `board_user` WHERE `uid`=?";
	public static final String SELECT_COUNT_NICK = 
			"SELECT COUNT(`nick`) FROM `board_user` WHERE `nick`=?";
	public static final String SELECT_COUNT_USER_NAME_EMAIL = 
			"SELECT COUNT(`name`) FROM `board_user` WHERE `name`=? AND `email`=?";
	public static final String SELECT_COUNT_USER_UID_EMAIL =
			"SELECT COUNT(`uid`) FROM `board_user` WHERE `uid`=? AND `email`=?";
	public static final String SELECT_TERMS = "SELECT * FROM `board_terms`";
	
	public static final String UPDATE_USER_FOR_SESSION = 
			"UPDATE `board_user` SET "
			+ "`sessId`=?, "
			+ "`sessLimitDate` = DATE_ADD(NOW(), INTERVAL 3 DAY) "
			+ "WHERE `uid`=?";
	public static final String UPDATE_USER_FOR_SESS_LIMIT_DATE = 
			"UPDATE `board_user` SET "
			+ "`sessLimitDate` = DATE_ADD(NOW(), INTERVAL 3 DAY) "
			+ "WHERE `sessId`=?";
	public static final String UPDATE_USER_FOR_SESSION_OUT=
			"UPDATE `board_user` SET "
			+ "`sessId`=null, "
			+ "`sessLimitDate`=null "
			+ "WHERE `uid`=?";
	public static final String UPDATE_USER_PASS =
			"UPDATE `board_user` SET `pass` = SHA2(?, 256) WHERE `uid`=?";
	
	// board
	
	public static final String SELECT_ARTICLES = 
			"SELECT "
			+ "a.*, "
			+ "b.`nick` "
			+ "FROM `board_article` AS a "
			+ "JOIN `board_user` AS b ON a.`uid` = b.`uid` "
			+ "WHERE "
			+ "a.`cate` = ? "
			+ "AND (a.`title` LIKE ? "
			+ "OR b.`nick` LIKE ?) "
			+ "ORDER BY a.`no` DESC "
			+ "LIMIT ?, 10";
	public static final String SELECT_COUNT_ARTICLES = 
			"SELECT COUNT(a.`no`) "
			+ "FROM `board_article` AS a "
			+ "JOIN `board_user` AS b ON a.`uid` = b.`uid` "
			+ "WHERE "
			+ "a.`cate` = ? "
			+ "AND (a.`title` LIKE ? "
			+ "OR b.`nick` LIKE ?)";
	
}
