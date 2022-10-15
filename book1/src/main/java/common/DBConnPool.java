package common;
/*
 * 날짜 : 2022/10/16
 * 이름 : 심규영
 * 내용 : 커넥션 풀을 이용하는 연결 클래스
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
	public Connection conn;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	// 기본 생성자
	public DBConnPool() {
		try {
			// 커넥션 풀(DataSource) 얻기
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			DataSource source = (DataSource)ctx.lookup("dbcp_mysql");
			
			// 커넥션 풀을 통해 연결 얻기
			conn = source.getConnection();
			
			System.out.println("DB 커넥션 풀 연결 성공");
		} catch (Exception e) {
			System.out.println("DB 커넥션 풀 연결 실패");
			e.printStackTrace();
		}
	}
	
	// 연결 해제(자원 반납)
	public void close() {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (psmt != null) psmt.close();
			if (conn != null) conn.close();
			
			System.out.println("DB 커넥션 풀 자원 반납");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
