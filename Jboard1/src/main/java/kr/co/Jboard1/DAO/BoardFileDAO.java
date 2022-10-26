package kr.co.Jboard1.DAO;

import kr.co.Jboard1.config.DBCP;
import kr.co.Jboard1.config.Sql;

public class BoardFileDAO extends DBCP {
	public BoardFileDAO() {
		try {
			conn = getConnection();
		} catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류");
			e.printStackTrace();
		}
	}
	
	public void BoardFileInsert(int parent, String newName,
			String fname) {
		try {
			psmt = conn.prepareStatement(Sql.INSERT_FILE);
			psmt.setInt(1, parent);
			psmt.setString(2, newName);
			psmt.setString(3, fname);
			
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("파일 인서트 오류");
			e.printStackTrace();
		}
	}
}
