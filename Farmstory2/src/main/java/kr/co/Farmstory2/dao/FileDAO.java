package kr.co.Farmstory2.dao;

import kr.co.Farmstory2.db.DBCP;
import kr.co.Farmstory2.db.Sql;

public class FileDAO extends DBCP {
	// create
	/**
	 * 글작성 - 파일 저장
	 * @param parent
	 * @param newName
	 * @param fileName
	 */
	public void insertFile(int parent, String newName, String fileName) {
		try {
			logger.info("insertFile...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_FILE);
			psmt.setInt(1, parent);
			psmt.setString(2, newName);
			psmt.setString(3, fileName);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	// read
	
	// upload
	
	// delete
}
