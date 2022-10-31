package kr.co.Jboard1.DAO;

import kr.co.Jboard1.bean.BoardCommentBean;
import kr.co.Jboard1.config.DBCP;
import kr.co.Jboard1.config.Sql;

public class BoardCommentDAO extends DBCP {
	private static BoardCommentDAO instance = new BoardCommentDAO();
	public static BoardCommentDAO getInstance() {
		try {
			conn = getConnection();
		} catch (Exception e) {
			System.out.println("댓글 데이터 베이스 연결 오류");
			e.printStackTrace();
		}
		return instance;
	}
	private BoardCommentDAO() {}
	
	/**
	 * 댓글 작성
	 * @author 심규영
	 * @date 2022/10/31
	 * @since 1.0
	 * @param parent
	 * @param uid
	 * @param content
	 */
	public void insertComment(BoardCommentBean bcb) {
		try {
			psmt = conn.prepareStatement(Sql.INSERT_COMMENT);
			psmt.setInt(1, bcb.getParent());
			psmt.setString(2, bcb.getUid());
			psmt.setString(3, bcb.getNick());
			psmt.setString(4, bcb.getContent());
			psmt.setString(5, bcb.getRegip());
			psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("댓글 등록 오류");
			e.printStackTrace();
		}
	}
	
	public void updateCommentNumber(String no) {
		try {
			psmt = conn.prepareStatement(Sql.UPDATE_COMMENT_NUMBER);
			psmt.setString(1, no);
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("댓글 갯수 업데이트 오류");
			e.printStackTrace();
		}
	}
}
