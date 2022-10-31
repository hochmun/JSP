package kr.co.Jboard1.DAO;

import kr.co.Jboard1.bean.BoardFileBean;
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
	 * 해당 게시물의 파일정보 가져오기
	 * @author 심규영
	 * @since 2022/10/28
	 * @param no - BoardArticle의 no
	 * @return BoardFileBean
	 */
	public BoardFileBean ReadFileData(String no) {
		BoardFileBean bfb = new BoardFileBean();
		try {
			psmt = conn.prepareStatement(Sql.READ_FILE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			if (rs.next()) {
				bfb.setFno(rs.getInt(1));
				bfb.setParent(rs.getInt(2));
				bfb.setNewName(rs.getString(3));
				bfb.setOriName(rs.getString(4));
				bfb.setDownload(rs.getInt(5));
			}
		} catch (Exception e) {
			System.out.println("해당 게시물의 파일 불러오기 오류");
			e.printStackTrace();
		}
		return bfb;
	}
	
	/**
	 * 파일 다운로드 횟수 증가
	 * @author 심규영
	 * @date 2022/10/28
	 * @param fno
	 */
	public void UpdateDownloadCount(int fno) {
		try {
			psmt = conn.prepareStatement(Sql.UPDATE_FILE_DOWNLOAD);
			psmt.setInt(1, fno);
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("파일 다운로드 카운트 업데이트 오류");
			e.printStackTrace();
		}
	}
	
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
