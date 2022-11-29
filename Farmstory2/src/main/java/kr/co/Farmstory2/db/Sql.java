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
	
	public static final String SELECT_USER = "";
	public static final String SELECT_COUNT_UID = 
			"SELECT COUNT(`uid`) FROM `board_user` WHERE `uid`=?";
	public static final String SELECT_COUNT_NICK = 
			"SELECT COUNT(`nick`) FROM `board_user` WHERE `nick`=?";
	public static final String SELECT_COUNT_USER_NAME_EMAIL = 
			"SELECT COUNT(`name`) FROM `board_user` WHERE `name`=? AND `email`=?";
	public static final String SELECT_COUNT_USER_UID_EMAIL =
			"SELECT COUNT(`uid`) FROM `board_user` WHERE `uid`=? AND `email`=?";
	public static final String SELECT_TERMS = "SELECT * FROM `board_terms`";
	
	// board
}
