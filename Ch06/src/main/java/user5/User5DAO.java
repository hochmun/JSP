package user5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import bean.User5Bean;
import config.DBCP;
/**
 * 유저5DAO
 * @method GetUsers5DAO() - 모든 유저 정보 읽기
 * @author java2
 *
 */
public class User5DAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	// 생성
	/*
	private static User5DAO instance = new User5DAO();
	public static User5DAO getInstance() {
		try {
			conn = DBCP.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}
	private User5DAO() {}
	*/
	
	public User5DAO() {
		try {
			conn = DBCP.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  모든 유저 정보 읽기
	 * @return List<User5Bean>
	 */
	public List<User5Bean> GetUsers5DAO() {
		List<User5Bean> users5 = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `user5`");
			
			users5 = new ArrayList<>();
			while(rs.next()) {
				User5Bean ub = new User5Bean();
				ub.setUid(rs.getString(1));
				ub.setName(rs.getString(2));
				ub.setBirth(rs.getString(3));
				ub.setAge(rs.getInt(4));
				ub.setAddress(rs.getString(5));
				ub.setHp(rs.getString(6));
				users5.add(ub);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users5;
	}
}
