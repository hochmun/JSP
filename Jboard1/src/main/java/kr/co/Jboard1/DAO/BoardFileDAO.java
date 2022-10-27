package kr.co.Jboard1.DAO;

import kr.co.Jboard1.config.DBCP;
import kr.co.Jboard1.config.Sql;

public class BoardFileDAO extends DBCP {
	private static BoardFileDAO instance = new BoardFileDAO();
	public static BoardFileDAO getInstance () {
		try {
			conn = getConnection();
		} catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류");
			e.printStackTrace();
		}
		return instance;
	}
	private BoardFileDAO() {}
	
	/**
	 * 서버에 파일저장 기능
	 * @author 심규영
	 * @since 2022/10/26
	 * @param parent
	 * @param newName
	 * @param fname
	 */
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
