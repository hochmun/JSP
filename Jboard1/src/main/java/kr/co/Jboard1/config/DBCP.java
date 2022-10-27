package kr.co.Jboard1.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBCP {
	protected static Connection conn;
	protected Statement stmt;
	protected PreparedStatement psmt;
	protected ResultSet rs;
	
	private static DataSource ds = null;
	public static Connection getConnection() throws NamingException, SQLException {
		if (ds == null) {
			ds = (DataSource) new InitialContext()
					.lookup("java:comp/env/dbcp_java2_board");
		}
		return ds.getConnection();
	}
	
	/**
	 * 클래스 종료
	 */
	public void close() {
		try {
			if(conn!=null) conn.close();
			if(stmt!=null) stmt.close();
			if(psmt!=null) psmt.close();
			if(rs!=null) rs.close();
		} catch (Exception e) {
			System.out.println("클래스 닫기 오류");
			e.printStackTrace();
		}
	}
}
