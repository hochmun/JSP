package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class java2db {
	private static java2db instance = new java2db();
	public static java2db getInstance() {
		return instance;
	}
	private java2db () {}
	
	// 데이터베이스 정보
	private final String HOST = "jdbc:mysql://127.0.0.1:3306/java2db";
	private final String USER = "root";
	private final String PASS = "1234";
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(HOST, USER, PASS);
	}
}
