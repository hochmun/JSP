package kr.co.Jboard1.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.co.Jboard1.config.DBCP;
import kr.co.Jboard1.config.Sql;

public class BoardArticleDAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	/**
	 * 데이터 베이스 연결
	 */
	public BoardArticleDAO() {
		try {
			conn = DBCP.getConnection();
		} catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류");
			e.printStackTrace();
		}
	}
	/**
	 * 게시물 등록
	 * @author 심규영
	 * @since 2022/10/24
	 * @param title
	 * @param content
	 * @param uid
	 * @param regip
	 */
	public void InsertBoardArticleDAO(String title, String content
			,String uid, String regip) {
		try {
			psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, uid);
			psmt.setString(4, regip);
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("아티클 인설트 오류");
			e.printStackTrace();
		}
	}
	
	/**
	 * BADAO 클래스 종료
	 */
	public void close() {
		try {
			if(conn!=null) conn.close();
			if(stmt!=null) stmt.close();
			if(psmt!=null) psmt.close();
			if(rs!=null) rs.close();
		} catch (Exception e) {
			System.out.println("BoardArticleDAO 닫기 오류");
			e.printStackTrace();
		}
	}
}
