package dao;

import java.util.ArrayList;
import java.util.List;

import db.DBCP;
import vo.User2VO;

public class User2DAO extends DBCP {
	private static User2DAO instance = new User2DAO();
	public static User2DAO getInstance() {
		return instance;
	}
	private User2DAO() {}
	
	// create
	public void insertUser2(User2VO uvo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("insert into `user2` values (?,?,?,?)");
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
	public User2VO selectUser2(String uid) {
		User2VO vo = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("select * from `user2` where `uid`=?");
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new User2VO();
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
	
	public List<User2VO> selectUsers2() {
		List<User2VO> users = new ArrayList<>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `user2`");
			
			while(rs.next()) {
				User2VO vo = new User2VO();
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
	public void updateUser2(User2VO vo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("update `user2` set `name`=?, `hp`=?, `age`=? where `uid`=?");
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
	public void deleteUser2(String uid) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("delete from `user2` where `uid`=?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
