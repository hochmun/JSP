package kr.co.Farmstory_1.DAO;

import kr.co.Farmstory_1.DTO.BoardFileDTO;
import kr.co.Farmstory_1.config.DBCP;
import kr.co.Farmstory_1.config.Sql;

public class BoardFileDAO extends DBCP {
	private static BoardFileDAO instance = new BoardFileDAO();
	public static BoardFileDAO getInstance() {
		return instance;
	}
	private BoardFileDAO() {}
	
	// create
	/**
	 * 2022/11/16<br/>파일첨부
	 * @author 심규영
	 * @param bfdto
	 */
	public void insertFile(BoardFileDTO bfdto) {
		try {
			logger.info("insertFile...");
			
			conn = getConnection();
			
			psmt = conn.prepareStatement(Sql.INSERT_FILE);
			psmt.setInt(1, bfdto.getParent());
			psmt.setString(2, bfdto.getNewName());
			psmt.setString(3, bfdto.getOriName());
			
			psmt.executeUpdate();
			
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	// read
	
	// upload
	
	// delete
}
