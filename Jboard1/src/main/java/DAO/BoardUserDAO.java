package DAO;
/* CRUD
 * 만들 기능 
 *  - 회원가입 기능
 *  - 아이디 중복 체크
 *  - 별명 중복 체크
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import config.DBCP;
/**
 * 유저 DAO
 * @date 2022/10/20
 * @author 심규영
 */
public class BoardUserDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private Statement stmt;
	private ResultSet rs;
	
	/**
	 * 데이터베이스 연결
	 */
	public BoardUserDAO() {
		try {
			conn = DBCP.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 회원가입
	 * @category create
	 */
	public void RegisterBoardUser(String uid, String pass, 
			String name, String nick, String email, String hp, String regip) {
		try {
			String sql = "insert into `board_user` set"
					+ "`uid`=?, `pass`=SHA2(?, 256), `name`=?, `nick`=?, "
					+ "`email`=?, `hp`=?, `regip`=?, `rdate`=NOW()";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			psmt.setString(3, name);
			psmt.setString(4, nick);
			psmt.setString(5, email);
			psmt.setString(6, hp);
			psmt.setString(7, regip);
			
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 아이디 체크
	 */
	
	/**
	 * 클래스 닫기
	 */
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(psmt!=null) psmt.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
