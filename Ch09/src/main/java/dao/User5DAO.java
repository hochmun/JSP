package dao;

import java.util.ArrayList;
import java.util.List;

import db.DBCP;
import vo.User5VO;

public class User5DAO extends DBCP {
	private static User5DAO instance = new User5DAO();
	public static User5DAO getInstance() {
		return instance;
	}
	private User5DAO() {}
	
	// create
	public void insertUser5(User5VO uvo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("insert into `user5` values (?,?,?,?,?,?)");
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
	public User5VO selectUser5(String uid) {
		User5VO vo = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("select * from `user5` where `uid`=?");
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new User5VO();
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
	
	public List<User5VO> selectUsers5() {
		List<User5VO> users = new ArrayList<>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `user5`");
			
			while(rs.next()) {
				User5VO vo = new User5VO();
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
	public void updateUser5(User5VO vo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement
					("update `user5` set `name`=?, `birth`=?, `age`=?, `address`=?, `hp`=? where `uid`=?");
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
	public void deleteUser5(String uid) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("delete from `user5` where `uid`=?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
