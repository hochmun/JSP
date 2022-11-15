package kr.co.Farmstory_1.config;

public class Sql {
	// article
	
	// file
	
	// terms
	public static final String SELECT_TERMS = "SELECT `terms`, `privacy` FROM `board_terms`";
	
	// user
	public static final String SELECT_USER = "SELECT * FROM `board_user` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	public static final String CHECK_UID = "SELECT * FROM `board_user` WHERE `uid`=?";
}
