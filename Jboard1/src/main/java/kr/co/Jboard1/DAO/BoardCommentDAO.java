package kr.co.Jboard1.DAO;

import java.util.ArrayList;
import java.util.List;

import kr.co.Jboard1.bean.BoardCommentBean;
import kr.co.Jboard1.config.DBCP;
import kr.co.Jboard1.config.Sql;

public class BoardCommentDAO extends DBCP {
	/*
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
	*/
	public BoardCommentDAO() {
		try {
			conn = getConnection();
		} catch (Exception e) {
			System.out.println("댓글 데이터 베이스 연결 오류");
			e.printStackTrace();
		}
	}
	
	/**
	 * 댓글 작성
	 * @author 심규영
	 * @date 2022/10/31
	 * @since 1.0
	 * @param parent
	 * @param uid
	 * @param content
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statementsor (2) 0 for SQL statements that return nothing
	 */
	public int insertComment(BoardCommentBean bcb) {
		int result = 0;
		try {
			psmt = conn.prepareStatement(Sql.INSERT_COMMENT);
			psmt.setInt(1, bcb.getParent());
			psmt.setString(2, bcb.getUid());
			psmt.setString(3, bcb.getNick());
			psmt.setString(4, bcb.getContent());
			psmt.setString(5, bcb.getRegip());
			result = psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("댓글 등록 오류");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 댓글 리스트 요청
	 * @author 심규영
	 * @date 2022/10/31
	 * @param parent
	 * @return List<BoardCommentBean>
	 */
	public  List<BoardCommentBean> CommentList(String parent) {
		List<BoardCommentBean> bcbs = new ArrayList<>();
		
		try {
			psmt = conn.prepareStatement(Sql.COMMENT_LIST);
			psmt.setString(1, parent);
			rs = psmt.executeQuery();
			while(rs.next()) {
				BoardCommentBean bcb = new BoardCommentBean();
				bcb.setCno(rs.getInt("cno"));
				bcb.setParent(rs.getInt("parent"));
				bcb.setUid(rs.getString("uid"));
				bcb.setNick(rs.getString("nick"));
				bcb.setContent(rs.getString("content"));
				bcb.setRegip(rs.getString("regip"));
				bcb.setRdate(rs.getString("rdate"));
				bcbs.add(bcb);
			}
		} catch (Exception e) {
			System.out.println("댓글 리스트 불러오기 오류");
			e.printStackTrace();
		}
		
		return bcbs;
	}
	
	/**
	 * 댓글 갯수 증가, 마지막 댓글 등록 시간
	 * @author 심규영
	 * @date 2022/10/31
	 * @param no
	 * @return date
	 */
	public String updateCommentNumber(String no) {
		String date = "";
		try {
			conn.setAutoCommit(false);
			psmt = conn.prepareStatement(Sql.UPDATE_COMMENT_NUMBER);
			psmt.setString(1, no);
			psmt.executeUpdate();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.LAST_COMMENT_TIME);
			if(rs.next()) {
				date = rs.getString(1);
			}
			conn.commit();
		} catch (Exception e) {
			System.out.println("댓글 갯수 업데이트, 댓글 등록 시간 리턴 오류");
			e.printStackTrace();
		}
		return date;
	}
}
