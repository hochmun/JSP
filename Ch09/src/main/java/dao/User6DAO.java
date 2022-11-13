package dao;

import java.util.ArrayList;
import java.util.List;

import db.DBCP;
import vo.User6VO;

public class User6DAO extends DBCP {
	private static User6DAO instance = new User6DAO();
	public static User6DAO getInstance() {
		return instance;
	}
	private User6DAO() {}
	
	// create
	public void insertUser6(User6VO uvo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("insert into `user6` values (?,?,?,?,?,?)");
			psmt.setString(1, uvo.getUid());
			psmt.setString(2, uvo.getName());
			psmt.setString(3, uvo.getBirth());
			psmt.setInt(4, uvo.getAge());
			psmt.setString(5, uvo.getAddress());
			psmt.setString(6, uvo.getHp());
			psmt.executeUpdate();
			close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// read
	public User6VO selectUser6(String uid) {
		User6VO vo = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("select * from `user6` where `uid`=?");
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new User6VO();
				vo.setUid(rs.getString("uid"));
				vo.setName(rs.getString("name"));
				vo.setBirth(rs.getString("birth"));
				vo.setAge(rs.getInt("age"));
				vo.setAddress(rs.getString("address"));
				vo.setHp(rs.getString("hp"));
			}
			
			close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public List<User6VO> selectUsers6() {
		List<User6VO> users = new ArrayList<>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `user6`");
			
			while(rs.next()) {
				User6VO vo = new User6VO();
				vo.setUid(rs.getString("uid"));
				vo.setName(rs.getString("name"));
				vo.setBirth(rs.getString("birth"));
				vo.setAge(rs.getInt("age"));
				vo.setAddress(rs.getString("address"));
				vo.setHp(rs.getString("hp"));
				users.add(vo);
			}
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	// upload
	public void updateUser6(User6VO vo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement
					("update `user6` set `name`=?, `birth`=?, `age`=?, `address`=?, `hp`=? where `uid`=?");
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getBirth());
			psmt.setInt(3, vo.getAge());
			psmt.setString(4, vo.getAddress());
			psmt.setString(5, vo.getHp());
			psmt.setString(6, vo.getUid());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// delete
	public void deleteUser6(String uid) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("delete from `user6` where `uid`=?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
