package kr.co.Jboard2.dao;

import kr.co.Jboard2.db.DBCP;
import kr.co.Jboard2.db.Sql;

public class FileDAO extends DBCP {
	// create
	/**
	 * 파일 저장
	 * @param parent
	 * @param newName
	 * @param fname
	 */
	public void insertFile(int parent, String newName, String fname) {
		try {
			logger.info("insertFile...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_FILE);
			psmt.setInt(1, parent);
			psmt.setString(2, newName);
			psmt.setString(3, fname);
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
