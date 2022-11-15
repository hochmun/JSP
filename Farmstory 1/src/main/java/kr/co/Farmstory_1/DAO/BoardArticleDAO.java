package kr.co.Farmstory_1.DAO;

import kr.co.Farmstory_1.config.DBCP;

public class BoardArticleDAO extends DBCP {
	private static BoardArticleDAO instance = new BoardArticleDAO();
	public static BoardArticleDAO getInstance() {
		return instance;
	}
	private BoardArticleDAO() {}
	
	// create
	
	// read
	
	// upload
	
	// delete
}
