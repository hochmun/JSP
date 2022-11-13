package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBCP {
	protected Connection conn = null;
	protected Statement stmt = null;
	protected PreparedStatement psmt = null;
	protected ResultSet rs = null;
	
	private static DataSource ds = null;
	public static Connection getConnection() throws NamingException, SQLException {
		if (ds == null) {
			ds = (DataSource) new InitialContext()
					.lookup("java:comp/env/dbcp_java2db");
		}
		return ds.getConnection();
	}
	
	/**
	 * 클래스 종료
	 */
	public void close() {
		try {
			// null 채크 && closed 체크 후 close
			if(rs!=null) if(!rs.isClosed()) rs.close();
			if(psmt!=null) if(!psmt.isClosed()) psmt.close();
			if(stmt!=null) if(!stmt.isClosed()) stmt.close();
			if(conn!=null) if(!conn.isClosed()) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// close 후 null로 재생성
		conn = null;
		stmt = null;
		psmt = null;
		rs = null;
	}
}
