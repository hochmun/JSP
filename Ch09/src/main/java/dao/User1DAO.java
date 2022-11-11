package dao;

import java.util.ArrayList;
import java.util.List;

import db.DBCP;
import vo.User1VO;

public class User1DAO extends DBCP {
	private static User1DAO instance = new User1DAO();
	public static User1DAO getInstance() {
		return instance;
	}
	private User1DAO() {}
	
	// create
	public void insertUser1(User1VO uvo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("insert into `user1` values (?,?,?,?)");
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
	public User1VO selectUser1(String uid) {
		User1VO vo = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("select * from `user1` where `uid`=?");
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new User1VO();
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
	
	public List<User1VO> selectUsers1() {
		List<User1VO> users = new ArrayList<>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `user1`");
			
			while(rs.next()) {
				User1VO vo = new User1VO();
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
	public void updateUser1(User1VO vo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("update `user1` set `name`=?, `hp`=?, `age`=? where `uid`=?");
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
	public void deleteUser1(String uid) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("delete from `user1` where `uid`=?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
