package dao;

import java.util.ArrayList;
import java.util.List;

import db.DBCP;
import vo.User3VO;

public class User3DAO extends DBCP {
	private static User3DAO instance = new User3DAO();
	public static User3DAO getInstance() {
		return instance;
	}
	private User3DAO() {}
	
	// create
	public void insertUser3(User3VO uvo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("insert into `user3` values (?,?,?,?)");
			psmt.setString(1, uvo.getUid());
			psmt.setString(2, uvo.getName());
			psmt.setString(3, uvo.getHp());
			psmt.setInt(4, uvo.getAge());
			psmt.executeUpdate();
			close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// read
	public User3VO selectUser3(String uid) {
		User3VO vo = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("select * from `user3` where `uid`=?");
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new User3VO();
				vo.setUid(rs.getString("uid"));
				vo.setName(rs.getString("name"));
				vo.setHp(rs.getString("hp"));
				vo.setAge(rs.getInt("age"));
			}
			
			close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public List<User3VO> selectUsers3() {
		List<User3VO> users = new ArrayList<>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `user3`");
			
			while(rs.next()) {
				User3VO vo = new User3VO();
				vo.setUid(rs.getString("uid"));
				vo.setName(rs.getString("name"));
				vo.setHp(rs.getString("hp"));
				vo.setAge(rs.getInt("age"));
				users.add(vo);
			}
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	// upload
	public void updateUser3(User3VO vo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("update `user3` set `name`=?, `hp`=?, `age`=? where `uid`=?");
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getHp());
			psmt.setInt(3, vo.getAge());
			psmt.setString(4, vo.getUid());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// delete
	public void deleteUser3(String uid) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("delete from `user3` where `uid`=?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
