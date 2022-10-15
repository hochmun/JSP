package common;
/*
 * 날짜 : 2022/10/15
 * 이름 : 심규영
 * 내용 : 기본적인 DB 연결 관리 클래스
 * 		두 번째 생성자 추가, p187
 * 		세 번째 생성자 추가, p188
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class JDBConnect {
	public Connection conn;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	// 기본 생성자
	public JDBConnect() {
		try {
			// JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// DB에 연결
			String url = "jdbc:mysql://localhost:3306/jsptest";
			String id = "root";
			String pwd = "1234";
			conn = DriverManager.getConnection(url, id, pwd);

			System.out.println("DB 연결 성공(기본 생성자)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 두 번째 생성자
	public JDBConnect(String driver, String url, String id, String pwd) {
		try {
			// JDBC 드라이버 로드
			Class.forName(driver);
			
			// DB에 연결
			conn = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(인수 생성자 1)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 세 번째 생성자
	public JDBConnect(ServletContext application) {
		try {
			// JDBC 드라이버 로드
			String driver = application.getInitParameter("MysqlDriver");
			Class.forName(driver);
			
			// DB에 연결
			String url = application.getInitParameter("MysqlURL");
			String id = application.getInitParameter("MysqlId");
			String pwd = application.getInitParameter("MysqlPwd");
			conn = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(인수 생성자 2)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (psmt != null) psmt.close();
			if (conn != null) conn.close();
			
			System.out.println("JDBC 자원 해제");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
